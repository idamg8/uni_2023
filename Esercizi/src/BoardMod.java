import java.util.List;

/**
 *   BoardMod b = new BoardMod(n);
 *
 *   b.size()           : int
 *   b.queensOn()       : int
 *   b.underAttack(i,j) : boolean
 *   b.addQueen(i,j)    : void
 *   b.arrangement()    : String
 */

public class BoardMod {
    private static final String ROWS = " 123456789ABCDEF";
    private static final String COLS = " abcdefghijklmno";

    private final int size;  // rimane final, dimensione scacchiera non cambia

    private int queens;
    private String config;

    /**
     * array di interi che indicano quante regine minacciano righe/colonne/diagonali corrispondenti all'indice
     * es. rowUnderAttack[] = [1, 2, 0] indica che la prima riga è minacciata da 1 regina, la seconda da 2, la terza non è minacciata
     */
    // numero righe e colonne = n
    private int[] rowUnderAttack;
    private int[] colUnderAttack;
    // numero diagonali = 2n-1
    private int[] dg1UnderAttack;  // diagonali ascendenti a dx (i-j invariante)
    private int[] dg2UnderAttack;  // diagonali discendenti a dx (i+j invariante)


    public BoardMod (int n) {  // inizializzazione di una nuova BoardMod vuota
        size = n;
        queens = 0;
        config = " ";

        rowUnderAttack = new int[n];      // array di n righe
        colUnderAttack = new int[n];      // array di n colonne
        dg1UnderAttack = new int[2*n-1];  // array di 2*n-1 diagonali
        dg2UnderAttack = new int[2*n-1];

        for (int i=0; i<n; i = i+1) {  // i<n perché indici array da 0 a n-1
            rowUnderAttack[i] = 0;
            colUnderAttack[i] = 0;
        }

        for (int i=0; i<2*n-1; i = i+1) {
            dg1UnderAttack[i] = 0;
            dg2UnderAttack[i] = 0;
        }
    }


    public int size() {
        return size;
    }

    public int queensOn() {
        return queens;
    }

    public boolean underAttack(int i, int j) {
        int n = size;

        return ( (rowUnderAttack[i-1] > 0) || (colUnderAttack[j-1] > 0) ||          // i = 1...n ma posizioni array 0...n-1 
                (dg1UnderAttack[i-j+n-1] > 0) || (dg2UnderAttack[i+j-2] > 0) );    // (indice array x corrisponde a coordinata schacchiera x+1)
    }

    public boolean isFreeRow(int i) {

        for (int j = 0; j < size(); j++) {
            if (underAttack(i, j)) {
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

    // necessario aggiungere la procedura removeQueen per mantenere la trasparenza referenziale 
    // (ovvero non modificare la scacchiera che viene data in input aggiungendoci nuove regine -> il risultato sarà falsato)
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
    }

    public String arrangement() {
        return config;
    }

    public String toString() {
        return "[" + arrangement() + "]";
    }


}  // class BoardMod
