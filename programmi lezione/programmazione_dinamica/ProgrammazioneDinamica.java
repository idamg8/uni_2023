
public class ProgrammazioneDinamica
{
    /**
     * FIBONACCI
     *
     * numero ricorsioni in funzione dell'argomento:
     * R(n) ≥ (3/2)^(n-1)
     * 
     * 1 ric: 1 nsc
     * 
     * R(100) ≥ 1.5^99
     * 
     * 1 = R(0) ≥ (3/2)^(-1) = 2/3
     * 1 = R(1) ≥ (3/2)^0 = 1
     * 
     * n ≥ 2: R(n) = 1 + R(n-2) + R(n-1) > (3/2)^(n-3) + (3/2)^(n-2) = (3/2)^(n-2) 5/3 > (3/2)^(n-2) 3/2 = (3/2)^(n-1)
     */
    
    public static long fib(int n) {  // n ≥ 0
        if (n == 0) {
            return 0;
        }
        else if (n <= 2) {
            return 1;
        } else {
            return fib(n-2) + fib(n-1);
        }
    }
    
    // soluzione top-down
    public static long fibMem(int n) {  // n ≥ 0
        long[] h = new long[n+1];
        
        for (int i=0; i<=n; i++) {
            h[i] = UNKNOWN;
        }
        return fibRec(n, h);
    }
    
    private static long fibRec(int n, long[] h) {  // n ≥ 0
        if (h[n] == UNKNOWN) {
            if (n == 0) {
                h[n] = 0;
            }
            else if (n == 1 || n== 2) {
                h[n] = 1;
            } else {
                h[n] = fibRec(n-2, h) + fibRec(n-1, h);
            }
        }
        return h[n];
    }
    
    private static final int UNKNOWN = -1;  
    // -1 non è una risposta possibile, lo utilizzo per sapere se nella casella è già rappresentato o meno un risultato o se la casella è "vuota"
    
    
    /**
     * MANHATTAN
     *
     * (5,5) -> (4,5)(5,4) -> (3,5)(4,4)(4,4)(5,3) -> (2,5)(3,4)(3,4)(4,3)(3,4)(4,3)(4,3)(5,2)
     */
    
    public static long paths(int i, int j) {
        if ( i == 0 || j == 0) {
           return 1; 
        } else {
            return paths(i-1,j) + paths(i,j-1);
        }
    }
    
    // soluzione top-down
    public static long pathsMem(int i, int j) {
        long[][] h = new long[i+1][j+1];
        
        for (int x=0; x<=i; x++) {
            for (int y=0; y<=j; y++) {
                h[x][y] = UNKNOWN;
            }
        }
        return pathsRec(i,j,h);
    }
    
    private static long pathsRec(int i, int j, long[][] h) {  // h è di tipo matrice
        if (h[i][j] == UNKNOWN) {
            if ( i == 0 || j == 0) {
               h[i][j] = 1; 
            } else {
                h[i][j] = pathsRec(i-1,j,h) + pathsRec(i,j-1,h);
            }
        }
        return h[i][j];
    }
    
    // soluzione bottom-up
    public static long pathsDp(int i, int j) {  // dp = dynamic programming
        long[][] h = new long[i+1][j+1];
        
        for (int y=0; y<=j; y++) {
            h[0][y] = 1;
        }
        for (int x=0; x<=i; x++) {
            h[x][0] = 1;
        }
        for (int x=1; x<=i; x++) {
            for (int y=1; y<=j; y++) {
                h[x][y] = h[x-1][y] + h[x][y-1];
            }
        }
        return pathsRec(i,j,h);
    }
    
    
    /**
     * LENGTH OF LONGEST COMMON SUBSEQUENCE
     */
    
    public static int llcs(String u, String v) {
        int m = u.length();
        int n = v.length();
        
        if ((m == 0) || (n == 0)) {
                return 0;
            } else if (u.charAt(0) == v.charAt(0)) {
                return 1 + llcs(u.substring(1), v.substring(1));
            } else {
                return Math.max(llcs(u.substring(1), v), llcs(u, v.substring(1)));
            }
    }
    
    // soluzione top-down
    public static int llcsMem(String u, String v) {
        int m = u.length();
        int n = v.length();
        
        int[][] h = new int[m+1][n+1];
        
        for (int x=0; x<=m; x++) {
            for (int y=0; y<=n; y++) {
                h[x][y] = UNKNOWN;
            }
        }
        return llcsRec(u,v,h);
    }
    
    public static int llcsRec(String u, String v, int[][] h) {
        int i = u.length();
        int j = v.length();
        
        if (h[i][j] == UNKNOWN) {
            if ((i == 0) || (j == 0)) {
                h[i][j] = 0;
            } else if (u.charAt(0) == v.charAt(0)) {
                h[i][j] = 1 + llcsRec(u.substring(1), v.substring(1), h);
            } else {
                h[i][j] = Math.max(llcsRec(u.substring(1), v, h), llcsRec(u, v.substring(1), h));
            }
        }
        return h[i][j];
    }
    
    // soluzione bottom-up
    public static int llcsDp(String u, String v) {
        int m = u.length();
        int n = v.length();
        
        int[][] h = new int[m+1][n+1];
        
        for (int y=0; y<=n; y++) {  // iterazione per gli indici di colonna
                h[0][y] = 0;  // suffisso di lunghezza 0 ovviamente ha 0 caratteri in comune
            }
        for (int x=1; x<=m; x++) {
                h[x][0] = 0;  // suffisso di lunghezza 0 ovviamente ha 0 caratteri in comune
            }
        for (int x=1; x<=m; x++) {
            for (int y=1; y<=n; y++) {
                if (u.charAt(m-x) == v.charAt(n-y)) {
                  h[x][y] = 1 + h[x-1][y-1];  
                } else {
                    h[x][y] = Math.max(h[x-1][y], h[x][y-1]);
                }
            }
        }
        return h[m][n];
    }
    
    
    
    /**
     * LONGEST COMMON SUBSEQUENCE
     */
    public static String lcs(String u, String v) {
        int m = u.length();
        int n = v.length();
        
        if ((m == 0) || (n == 0)) {
                return "";
            } else if (u.charAt(0) == v.charAt(0)) {
                return u.charAt(0) + lcs(u.substring(1), v.substring(1));
            } else {
                return longer(lcs(u.substring(1), v), lcs(u, v.substring(1)));
            }
    }
    
    private static String longer(String u, String v) {
        int m = u.length();
        int n = v.length();
        
        if (m < n) {
            return v;
        } else if (m > n) {
            return u;
        } else if (Math.random() < 0.5) {  // random restituisce un valore 0<x<1
            return v;
        } else {
            return u;
        }
    }
    
    // soluzione top-down: memoization
    public static String lcsMem(String u, String v) {
        int m = u.length();
        int n = v.length();
        
        String[][] h = new String[m+1][n+1];
        
        for (int x=0; x<=m; x++) {
            for (int y=0; y<=n; y++) {
                h[x][y] = null;  // String è un oggetto, null è l'oggetto indefinito
            }
        }
        return lcsRec(u,v,h);
    }
    
    public static String lcsRec(String u, String v, String[][] h) {
        int i = u.length();
        int j = v.length();
        
        if (h[i][j] == null) {
            if ((i == 0) || (j == 0)) {
                h[i][j] = "";
            } else if (u.charAt(0) == v.charAt(0)) {
                h[i][j] = u.charAt(0) + lcsRec(u.substring(1), v.substring(1), h);
            } else {
                h[i][j] = longer(lcsRec(u.substring(1), v, h), lcsRec(u, v.substring(1), h));
            }
        }
        return h[i][j];
    }
    
    public static String lcsDp(String u, String v) {
        int m = u.length();
        int n = v.length();
        
        int[][] h = new int[m+1][n+1];
        
        for (int y=0; y<=n; y++) {  // iterazione per gli indici di colonna
                h[0][y] = 0;  // suffisso di lunghezza 0 ovviamente ha 0 caratteri in comune
            }
        for (int x=1; x<=m; x++) {
                h[x][0] = 0;  // suffisso di lunghezza 0 ovviamente ha 0 caratteri in comune
            }
        for (int x=1; x<=m; x++) {
            for (int y=1; y<=n; y++) {
                if (u.charAt(m-x) == v.charAt(n-y)) {
                  h[x][y] = 1 + h[x-1][y-1];  
                } else {
                    h[x][y] = Math.max(h[x-1][y], h[x][y-1]);
                }
            }
        }
        // int ls = h[m][n];  // lunghezza soluzione
        
        String s = "";  // accumulatore della soluzione
        
        // indici della posizione in cui ci troviamo all'interno della matrice
        int x = m;
        int y = n;
        
        while (h[x][y] > 0) {  // itero finché il contenuto della cella della matrice non è pari a 0
            if (u.charAt(m-x) == v.charAt(n-y)) {
                s = s + u.charAt(m-x);
                // spostamento in basso a destra nella matrice
                x = x-1;
                y = y-1;
            } else if (h[x-1][y] < h[x][y-1]) {  // mi sposto di casella in base a quale contiene il valore maggiore
                y = y-1;
            } else if (h[x-1][y] > h[x][y-1]) {
                x = x-1;
            } else if (Math.random() < 0.5) {  // random se i valori a destra e in basso sono uguali
                y = y-1;
            } else {
                x = x-1;
            }
        }
        return s;
    }
    
    
    /** 
     * PROCEDURA CHE RESTITUISCE TEMPO DI CALCOLO
     */
    public static long elapsedTime(int n) {
        long t = System.currentTimeMillis();
        
        long x = fibMem(n);
        
        return (System.currentTimeMillis() - t);
    }
    
    
}  // class ProgrammazioneDinamica
