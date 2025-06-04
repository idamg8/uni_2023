public class ProgrammazioneImperativa
{
    // lezione 12.03.2024
    /*
     * Massimo comun divisore 
     * 
     * (define mcd
     *  (lambda (x y)
     *      (cond ((= x y)
     *              x)
     *            ((< x y)
     *              (mcd x (- y x)))
     *            (else
     *              (mcd (- x y) y))
     *      )
     * ))
     */
    
    public static int mcd(int x, int y) {  // x, y > 0
        while (x != y) {  // != diverso
            if (x < y) {
                y = y-x;  // istruzione che cambia stato variabili
            } else {
                x = x-y;  
            }
        }
        return x;  // appena x,y diventano uguali viene restituito il risultato
    }
    
    // Minimo comune multiplo
    
    public static int mcm(int x, int y) {  // x, y > 0
        int m = x;
        
        while (m % y > 0) {  // resto tra m e y maggiore di zero
            m = m + x;  // successivamente il valore diventa 2x, 3x, 4x...
        }
        
        return m;  // ritorno quando il resto tra x, y è = 0
    }
    
    // Test di primalità
    
    public static boolean isPrimo(int n) {  // n ≥ 2
        if (n % 2 == 0) {
            return (n == 2);
        } else if (n == 3) {
            return true;
        } else {
            int k = 3;  // più piccolo possibile divisore
            while ((n % k > 0) && (k <= Math.sqrt(n))) {
                k = k+2;
            }
            return (n % k > 0);
        }
    }
    
    // Lista di numeri primi
    
    public static void listaPrimi(int sup) {  // void: non viene restituito alcun valore
        for (int n = 2; n <= sup; n = n+1) {  // variabile: tipo, punto in cui concludo, modo in cui procedo
            if (isPrimo(n)) {
                System.out.print(" " + n);  // se uno degli argomenti è stringa, java interpreta + come concatenazione e converte l'altro argomento in stringa
            }
        }
        System.out.println();  // println = print line
    }
    
    // ha senso usare for solo nel caso in cui si sappia già a priori quante interazioni si vogliono fare: iterazione determinata
    
    
    //Lezione 28: 18.03.2024
    
    /*
     * UFO procedure
     * 
     * (define ufo   ; val: intero
     *  (lambda (x)  ; x: intero positivo
     *      (cond ((= x 1)
     *              1))
     *            ((even? x)
     *              (- (* 2 (ufo (quotient x 2))) 1))
     *            (else
     *              (+ (* 2 (ufo (quotient x 2))) 1))
     *  )))
     * 
     * array: | -| 1| 1| 3| 1| 3| 5| 7| 1| 3| 5| 7| 9|
     *          0  1  2  3  4  5  6  7  8  9 10 11 12
     */
    
    // struttura bottom-up: calcolo tutti i valori, poi restituisco quello desiderato (numero di iterazioni cresce linearmente con n)
    public static int ufo(int n) {  // n > 0
        int[] u = new int[n+1];  // inizializzazione di array con tipo e dimensioni (+1 per includere posizione 0)
        u[1] = 1;  // assegno valore del caso base x = 1
        for (int x = 2; x <= n; x = x+1) {
            if (x % 2 == 0) {  // x pari
                u[x] = 2 * u[x/2] - 1;
            } else {  // xi dispari
                u[x] = 2 * u[x/2] + 1;
            }
        }
        return u[n];
    }
    
    /* struttura top-down
     * array: |12| 6| 3| 1|  utilizzato come stack
     *                   1
     *                3
     *             5
     *          9
     */          
    
    public static int ufoTopDown(int x) {  // x > 0
        int dim = (int)(Math.log(x)/Math.log(2)) + 1;  // per ottenere log in base 2 di x; +1 per correggere l'arrotondamento dato da (int) (effettua un floor)
        int[] s = new int[dim];
        int t =  0;  // t=top, numero di el. inseriti / elemento più a destra (top of stack)
        while (x > 0) {
            s[t] = x;
            t = t + 1;
            x = x / 2;
        }
        t = t - 1;  // faccio riferimento all'ultimo valore inserito (torno indietro perché nell'ultimo cliclo t era aumentato senza scrivere nulla nella corrispondente posiz. in array)
        int y = 1;  // y = ufo(s[t])
        while (t > 0) {
            t = t - 1;
            if (s[t] % 2 == 0) {
                y = 2 * y - 1;
            } else {
                y = 2 * y + 1;
            }
        }
        return y;
    }
    
    
    public static int btrVal(String btr) {  // btr: stringa di +/-/.
        int k = btr.length();
        int v = 0;  // valore iniziale impostato a 0 
        for (int i=0; i<k; i=i+1) {
            v = 3 * v + btdVal(btr.charAt(i)); 
        }
        return v;
    }
    
    public static int btdVal(char btd) {  
        switch (btd) {
            case '-': 
                return -1;
            case '.':
                return 0;
            case '+':
                return 1;
            default:
                return 0;  // non dovrebbe verificarsi mai (funge da else)
        }
    }
} // class ProgrammazioneImperativa
