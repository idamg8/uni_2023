import java.util.Stack;

public class JavaProgramming {

    // es. 1
    /*La procedura llcs3 determina la lunghezza della sottosequenza comune più lunga (LLCS) di tre stringhe:
            (define llcs3
                (lambda (t u v)
                    (cond ((or (string=? t "") (string=? u "") (string=? v ""))
                            0)
                          ((char=? (string-ref t 0) (string-ref u 0) (string-ref v 0))
                            (+ 1 (llcs3 (substring t 1) (substring u 1) (substring v 1))))
                          (else
                            (max (llcs3 (substring t 1) u v)
                                 (llcs3 t (substring u 1) v)
                                 (llcs3 t u (substring v 1))))
                    )))
    Trasforma la procedura llcs3 in un programma Java che applica opportunamente la tecnica top-down di memoization.*/

    /*public static int llcs3(String t, String u, String v) {
        int m = t.length();
        int n = u.length();
        int p = v.length();

        if ((m == 0) || (n == 0) || (p == 0)) {
            return 0;
        } else if (t.charAt(0) == u.charAt(0) && u.charAt(0) == v.charAt(0)) {
            return 1 + llcs3(t.substring(1), u.substring(1), v.substring(1));
        } else {
            return Math.max(llcs3(t.substring(1), u, v), llcs3(t, u.substring(1), v), llcs3(t, u, v.substring(1)));
        }
    }*/

    /*private static final int UNKNOWN = -1;
    public static int llcs3Mem (String t, String u, String v) {

        int[][][] matrix = new int[t.length()+1][u.length()+1][v.length()+1];
        for (int i = 0; i < t.length(); i++) {
            for (int j = 0; j < u.length(); j++) {
                for (int k = 0; k < v.length(); k++) {
                    matrix[i][j][k] = UNKNOWN;
                }
            }
        }

        return llcs3Rec(t, u, v, matrix);
    }*/

    private static int llcs3Rec (String t, String u, String v, int[][][] matrix) {

        int m = t.length();
        int n = u.length();
        int p = v.length();

        if ((m == 0) || (n == 0) || (p == 0)) {

            matrix[m][n][p] = 0;

        } else if (t.charAt(0) == u.charAt(0) && u.charAt(0) == v.charAt(0)) {

            matrix[m][n][p] = 1 + llcs3Rec(t.substring(1), u.substring(1), v.substring(1), matrix);

        } else {

            int recT = llcs3Rec(t.substring(1), u, v, matrix);
            int recU = llcs3Rec(t, u.substring(1), v, matrix);
            int recV = llcs3Rec(t, u, v.substring(1), matrix);

            matrix[m][n][p] = Math.max(Math.max(recT, recU), recV);

        }

        return matrix[m][n][p];
    }


    // es. 2
    /*Come è noto dall’algebra lineare, una matrice quadrata Q si dice simmetrica se coincide con la propria trasposta,
    ovvero se Qij = Qji per tutte le coppie di indici della matrice. Scrivi un metodo statico in Java per verificare se
    l’argomento è una matrice simmetrica — assumendo che tale argomento rappresenti una matrice quadrata.
    Per quanto possibile, struttura il codice in modo tale da ridurre al minimo il numero di confronti effettuati dal
    programma nei casi in cui la matrice sia effettivamente simmetrica.*/
    public static boolean isSimmetrico(int[][] matrix) {

        for (int i=0; i< matrix.length; i++) {
            for (int j=0; j< matrix.length; j++) {

                if (j>i) {
                    if (matrix[i][j] != matrix[j][i]) {
                        return false;
                    }
                }

            }
        }

        return true;
    }

    // CON STRAZIONE SULLO STATO, LE PROCEDURE NON POSSONO ESSERE STATIC!!!!!
    // ALTRIMENTI NON POSSO ACCEDERE ALLE VARIABILI D'ISTANZA (DICHIARATE COME PRIVATE [TYPE] [NAME])

    // es. 3
    /*Il modello della scacchiera realizzato dalla classe Board per affrontare il rompicapo delle n regine deve essere integrato
    introducendo due nuovi metodi: isFreeRow(int), che dato un indice di riga i compreso fra 1 e la dimensione n della
    scacchiera, restituirà true se e solo se nella riga i non c’è alcuna regina; addQueen(String), che svolge la stessa
    funzione di addQueen(int,int) ma ricevendo come argomento la codifica della posizione tramite una stringa di due
    caratteri, una lettera per la colonna e una cifra per la riga secondo le convenzioni consuete. Inoltre, addQueen e
    removeQueen non devono modificare lo stato della scacchiera se l’operazione è inconsistente perché due regine
    verrebbero a trovarsi sulla stessa casella oppure perché nella posizione data non c’è una regina da rimuovere. Per
    esempio, il metodo statico listOfCompletions, definito sotto a destra, stamperà tutte le soluzioni del rompicapo, se
    ve ne sono, compatibili con una disposizione iniziale di regine che non si minacciano reciprocamente.
    In base a quanto specificato sopra, modifica opportunamente la classe Board.*/

    /*public boolean isFreeRow(int i) {

        for (int j=0; j<b.size(); j++) {
            if (b.underAttack(i,j)) {
                return false;
            }
        }

        return true;

    }

    public void addQueen(String pos) {
        int n = size;

        boolean added = false;

        for (int a=0; a < config.length()-1; a++) {
            if (config.charAt(a) == pos.charAt(0) && config.charAt(a+1) == pos.charAt(1)) {
                added = true;
                break;
            }
        }

        if (added = false) {

            int j = 0;

            for (int x=0; x<COLS.length(); x++) {
                if (pos.charAt(0) == COLS.charAt(x)) {
                    j = x;
                }
            }

            queens = queens + 1;
            config = config + pos + " ";

            int i = pos.charAt(1);

            rowUnderAttack[i-1] = rowUnderAttack[i-1] + 1;
            colUnderAttack[j-1] = colUnderAttack[j-1] + 1;
            dg1UnderAttack[i-j+n-1] = dg1UnderAttack[i-j+n-1] + 1;
            dg2UnderAttack[i+j-2] = dg2UnderAttack[i+j-2] + 1;

        }

    }

    public void removeQueen(int i, int j) {
        int n = size;

        char cJ = COLS.charAt(j);
        String coordinates = "" + cJ + i;

        boolean canRemove = false;

        for (int a=0; a < config.length()-1; a++) {
            if (config.charAt(a) == coordinates.charAt(0) && config.charAt(a+1) == coordinates.charAt(1)) {
                canRemove = true;
                break;
            }
        }

        queens = queens-1;
        String pair = "" + COLS.charAt(j) + ROWS.charAt(i);  // coppia di lettere da rimuovere da config
        int k = config.indexOf(pair);  // indexOf predefinita per String (indice dove si trova la sottostringa)
        config = config.substring(0,k) + config.substring(k+3);  // elimino i 3 caratteri di pair + spazio bianco

        rowUnderAttack[i-1] = rowUnderAttack[i-1] - 1;
        colUnderAttack[j-1] = colUnderAttack[j-1] - 1;
        dg1UnderAttack[i-j+n-1] = dg1UnderAttack[i-j+n-1] - 1;
        dg2UnderAttack[i+j-2] = dg2UnderAttack[i+j-2] - 1;
    }*/


    // es. 4
    /*Dato un albero di Huffman, il metodo statico shortestCodeLength determina la lunghezza del più corto fra i codici
    di Huffman associati ai caratteri che compaiono in un documento di testo. Più specificamente, la visita dell’albero,
    finalizzata alla determinazione di tale lunghezza, è realizzata attraverso uno schema iterativo.
    Completa la definizione del metodo shortestCodeLength riportata nel riquadro.*/

    public static int shortestCodeLength( Node root ) {
        int sc = root.weight();
        Stack<Node> stack = new Stack<Node>();
        Stack<Integer> depth = new Stack<Integer>();
        stack.push( root );
        depth.push( 0 );
        do {
            Node n = stack.pop();
            int d = depth.pop();
            if ( n.isLeaf() ) {
                sc = Math.min( sc, d );
            } else if ( d+1 < sc) {
                if (n.left().weight() < n.right().weight()) {
                    stack.push(n.left());
                } else {
                    stack.push(n.right());
                }
                depth.push(d+1);
            }
        } while (!stack.empty());
        return sc;
    }


    // es. 5
    /*Dato un array di numeri (double) con almeno due elementi, il metodo statico closestPair ne restituisce una
    coppia la cui differenza in valore assoluto è minima. La coppia è rappresentata da un array ordinato di due elementi.
            Esempio:
    closestPair( new double[] {0.3, 0.1, 0.6, 0.8, 0.5, 1.1} ) → {0.5, 0.6}
    Definisci in Java il metodo statico closestPair.*/

    public static double closestPair(double[] array) {

        double lowest = array[0] - array[1];
        double temp;

        for (int i=0; i<array.length; i++) {
            for (int j=0; j< array.length; j++) {

                if (array[j] != array[i]) {
                    temp = array[i] - array[j];
                    if (temp < 0) {
                        temp = temp*-1;
                    }
                    if (temp < lowest) {
                        lowest = temp;
                    }
                }
            }
        }

        return lowest;

    }


    // es. 6
    /*Gli argomenti del metodo statico commonStretches sono due stringhe u, v composte solo dai caratteri '0' e '1',
    entrambe con esattamente m occorrenze di '0' ed n occorrenze di '1'. Tali stringhe rappresentano due percorsi di
    Manhattan in un reticolo di m x n tratti di strada, dove '0' denota uno spostamento verticale e '1' orizzontale. Date le
    stringhe u, v, il metodo commonStretches calcola il numero di tratti di strada comuni ai due percorsi, numero che
    può variare da 0 a m+n, nel secondo caso quando i due percorsi (e quindi le due stringhe) coincidono. Per esempio:
    commonStretches( "1010110110", "1100011101" ) → 3 // m = 4, n = 6
    Affinché un certo tratto sia comune a due percorsi, questi devono passare per lo stesso nodo (incrocio) e lo spostamento
    successivo deve chiaramente procedere nella stessa direzione (orizzontale o verticale). I due percorsi passano per lo
    stesso nodo se una parte iniziale (prefisso) delle stringhe che li rappresentano è composta dallo stesso numero di '0' e
    dallo stesso numero di '1'; inoltre, lo spostamento che segue va nella stessa direzione se il carattere successivo delle
    due stringhe coincide.
    Nell’esempio questa situazione si verifica in corrispondenza ai caratteri in posizione 0, 3 e 7 come illustrato
    dalla figura qui a lato. Definisci in Java il metodo statico commonStretches in accordo con le specifiche indicate.*/

    public static int commonStretches(String u, String v) {

        int lengthU = u.length();

        int count = 0;
        int[] sum = new int[2];

        for (int i=0; i<lengthU; i++) {
            if (sum[0] == sum[1]) {
                if (u.charAt(i) == v.charAt(i)) {
                    count = count + 1;
                }
            }
            sum[0] = sum[0] + u.charAt(i);
            sum[1] = sum[1] + v.charAt(i);
        }

        return count;

    }


    // es. 7
    /*Dato l’albero di Huffman costruito sulla base di uno specifico documento, e completo dell’informazione sul numero di
    occorrenze dei caratteri utilizzarti, il seguente programma ricorsivo calcola il numero di byte che saranno richiesti per
    la codifica di Huffman di quel documento attraverso gli strumenti discussi a lezione.

    public static int huffmanCodeSize( Node root ) {
        long bits = recSize( root, 0 ); // visita dell'albero di Huffman
        return (int) ( bits / 7 ) + ( (bits%7 > 0) ? 1 : 0 );
    }

    private static long recSize( Node n, int depth ) {
        if ( n.isLeaf() ) {
            return depth * n.weight();
        } else {
            return recSize( n.left(), depth+1 ) + recSize( n.right(), depth+1 );
        }
    }

    Completa le definizioni della classe Frame e del metodo codeSizeIter per trasformare la ricorsione in iterazione
    applicando uno stack.*/

    public static int huffmanCodeSize( Node root ) {
        long bits = recSize( root, 0 ); // visita dell'albero di Huffman
        return (int) ( bits / 7 ) + ( (bits%7 > 0) ? 1 : 0 );
    }

    private static long recSize( Node n, int depth ) {
        if ( n.isLeaf() ) {
            return depth * n.weight();
        } else {
            return recSize( n.left(), depth+1 ) + recSize( n.right(), depth+1 );
        }
    }

    public static int codeSizeIter( Node root ) {

        long bits = 0;
        Stack<Frame> stack = new Stack<Frame>();
        stack.push( new Frame(root,0) );

        do {

            Frame current = stack.pop();
            Node n = current.node;
            int depth = current.depth;

            if (n.isLeaf()) {

                bits = bits + depth * n.weight();

            } else {

                stack.push(new Frame (n.left(), depth+1));
                stack.push(new Frame (n.right(), depth+1));

            }

        } while ( !stack.isEmpty() );

        return (int) ( bits / 7 ) + ( (bits % 7 > 0) ? 1 : 0 );
    }


    // es. 8
    /*Un array v di double rappresenta uno heap se e solo se vale la relazione v[i] ≤ v[j] per ogni coppia di indici
    positivi dell’array i, j ≥ 1 tali che j = 2i oppure j = 2i+1 — in altri termini quando l’indice più piccolo è il quoziente
    della divisione per due dell’altro indice.
    Definisci in Java un metodo statico heapCheck per verificare se un array di double rappresenta uno heap. Esempi:
    heapCheck( new double[] { 5.0, 3.1, 5.7, 3.1, 8.5, 6.0, 3.8, 4.2, 9.3 } ) → true
    heapCheck( new double[] { 5.0, 3.1, 5.7, 3.1, 8.5, 6.0, 3.0, 4.2, 9.3 } ) → false*/

    public static boolean heapCheck (double[] v) {

        for (int i=v.length-1; i>2; i--) {

            if (v[i] < v[i/2]) {
                return false;
            }
        }

        return true;
    }

    public static int[] heapTest (double[] v) {

        int[] out = null;

        for (int i=v.length-1; i>2; i--) {

            if (v[i] < v[i/2]) {
                out = new int[2];
                out[0] = i/2;
                out[1] = i;
            }
        }

        return out;
    }


    // es.9
    /*Una stringa è una palindrome, o palindromica, quando la sequenza dei caratteri risulta la stessa leggendola da sinistra a
    destra oppure da destra a sinistra. Data una stringa s, la procedura ricorsiva lps, riportata nella pagina seguente, ne
    determina la sottosequenza palindromica più lunga (LPS = Longest Palindromic Subsequence) — più precisamente,
    una delle sue sottosequenze palindromiche più lunghe. Il metodo statico longer è lo stesso introdotto a lezione per
    realizzare lcs e restituisce semplicemente la più lunga delle due stringhe passate come argomenti. Esempio:
    lps( "irradiare" ) → "radar"*/
    public static String lps( String s ) { // longest palindromic subsequence
        int n = s.length();
        if ( n < 2 ) { // stringa vuota o di un solo carattere: palindrome
            return s;
        } else if ( s.charAt(0) == s.charAt(n-1) ) { // caratteri estremi uguali: fanno parte del risultato
            return s.charAt(0) + lps( s.substring(1,n-1) ) + s.charAt(n-1);
        } else { // caratteri estremi diversi: almeno uno va scartato
            return longer( lps(s.substring(0,n-1)), lps(s.substring(1,n)) );
        }
    }
    /*Completa il programma impostato nel riquadro, basato sulla procedura lpsDP, che applica una tecnica bottom-up di
    programmazione dinamica per rendere più efficiente la computazione ricorsiva svolta da lps.*/

    public static String lpsDP( String s ) {

        int n = s.length();
        String[][] mem = new String[n+1][n+1];

        for ( int k=0; k<=n; k=k+1 ) {
            for ( int i=0; i<=n-k; i=i+1 ) {  // i=n-k -> n=i+k

                // k : lunghezza della sottostringa s* di s considerata;
                // i : posizione di s* in s:
                // s* corrisponde al potenziale argomento di una invocazione ricorsiva di lps.

                if ( k < 2 ) {  // se la lunghezza della stringa considerata è 0 oppure 1

                    mem[k][i] = ( k == 0 ) ? "" : "" + s.charAt(i);  // stringa di lunghezza 0: sempre "" in qualsiasi posizione
                                                                     // stringa di lunghezza 1: corrisponde al carattere in posizione i

                } else if ( s.charAt(i) == s.charAt(i+k-1) ) {  // confronta i caratteri in posizione estrema di una stringa lunga k
                                                                // es. k=4, i=5 -> confronta caratteri in pos. 5 e 8

                    mem[k][i] = s.charAt(i) + mem[k-2][i+1] + s.charAt(i+k-1);  // metto i due caratteri uguali all'inizio e alla fine
                                                                                // se avevo una stringa lunga k, dopo aver preso il primo
                                                                                // e ultimo carattere, mi rimangono k-2 caratteri in mezzo;
                                                                                // il punto di partenza della stringa si incrementa di 1

                } else {  // se i caratteri in posizione estrema sono diversi

                    mem[k][i] = longer(mem[k-1][i], mem[k-1][i+1]);  // considero quale opzione mi darà il risultato maggiore:
                                                                     // 1. tenendo fisso l'indice di inizio stringa e togliendo il char finale
                                                                     // 2. incrementando l'indice di inizio stringa e quindi togliendo il primo char
                                                                     // in entrambi i casi ho tolto 1 char da una stringa lunga k, quindi
                                                                     // la nuova stringa sarà lunga k-1

                }
            }}

        return mem[n][0];  // restituisco il risultato che corrisponde ad una stringa lunga n e con indice di inizio stringa a 0
                           // ciò significa che considero l'intera stringa data in input
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

    public static String[] UNKNOWN = new String[2];
    public static String[] diff(String u, String v) {

        int m = u.length();
        int n = v.length();

        String[][][] mem = new String[m+1][n+1][2];

        for (int i=0; i<=m+1; i++) {
            for (int j=0; j<=n+1; j++) {

                mem[i][j] = UNKNOWN;

            }
        }

        return diffRec(u,v,mem);

    }

    public static String[] diffRec(String u, String v, String[][][] mem) {

        int i = u.length();
        int j = v.length();

        if (mem[i][j] == UNKNOWN) {

            if (i==0 || j==0) {
                mem[i][j] = new String[]{u,v};
            } else if (u.charAt(0) == v.charAt(0)) {
                mem[i][j] = diffRec(u.substring(1), v.substring(1), mem);
            } else {
                String[] x = diffRec(u.substring(1), v, mem);
                String[] y = diffRec(u, v.substring(1), mem);
                if (x[0].length() < y[0].length()) {
                    mem[i][j] = new String[]{u.charAt(0)+x[0], x[1]};
                } else {
                    mem[i][j] = new String[]{y[0], v.charAt(0)+y[1]};
                }
            }
        }

        return mem[i][j];
    }

} // class JavaProgramming
