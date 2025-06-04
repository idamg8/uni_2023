import puzzleboard.*;
import java.util.Random;

public class FifteenGame {

    private final int size;
    private final int[][] tavoletta;
    private final int[] numList;

    //------------------------------------------------------------------------------------------------------------------

    public FifteenGame(int n) {
        size = n;

        // crea array contenente lista di numeri da 1 a n^2-1, ultimo valore 0 rappresenta il buco
        int num = 1;
        numList = new int[n*n];
        for (int i=0; i<numList.length-1; i++) { // length-1 perché non voglio che l'ultima posizione venga riempita (lascio 0 -> buco)
            numList[i] = num;
            num++;
        }

        // procedura che randomizza l'ordine dei numeri [1,n^2-1]+0
        Random rand = new Random();

        for (int i = 0; i < numList.length; i++) {
            // nextInt(n): ritorna un valore int (pseudo)casuale tra 0 incluso e n escluso
            int randomIndexToSwap = rand.nextInt(numList.length);
            int temp = numList[randomIndexToSwap];
            numList[randomIndexToSwap] = numList[i];
            numList[i] = temp;
        }

        // riempie tavoletta con i numeri da 1 a n^2-1, mettendo il buco (0) in ultima posizione
        tavoletta = fillMatrix();

    }  // public FifteenGame

    private int[][] fillMatrix() {
        int[][] matrix = new int[size][size];
        for (int i=0; i<size; i++) {
            for (int j=0; j<size; j++) {
                // formula i*n+j ritorna posizione nell'array di una casella di una matrice
                matrix[i][j] = numList[i*size+j];
            }
        }
        return matrix;
    }
    /*
    // metodo alternativo per riempire la matrice
        for (int[] i : tavoletta) {
            for (int j : i) {
                i[j] = index;
            }
        }
    */

    // procedura che ritorna se i numeri dentro alla tavoletta sono ordinati
    public boolean isOrdered() {

        int num = 1;
        for (int i=0; i<numList.length-1; i++) {  // mi basta sapere che i numeri sono ordinati fino alla penultima posizione, in ultima ci sarà 0
            if (numList[i] != num) {
                return false;
            }
            num++;
        }
        return true;

    }  // isOrdered

    public String arrangement() {
        String config = "" + numList[0];
        for (int i=1; i<this.numList.length; i++) {
            config = config + " " + numList[i];
        }
        return config;
    }

    // procedura che cerca le coordinate (i,j) di un tassello dato il suo valore n
    private int[] findCoords(int n) {
        int[] coords = new int[2];

        for (int x=0; x<tavoletta.length; x++) {
            for (int y=0; y<tavoletta.length; y++) {
                if (n == tavoletta[x][y]) {
                    coords[0] = x;
                    coords[1] = y;
                    break;  // break per uscire dal loop una volta che ho trovato le coordinate di n
                }
            }
        }
        return coords;

    }  // findCoords

    // formula i*n+j ritorna posizione nell'array di una casella di una matrice
    // i*n+j+1 = casella a destra | i*n+j-1 = casella a sinistra
    // i*n+j-n = casella sopra    | i*n+j-n = casella sotto
    // (alternativamente (i-1)*n+j e (i+1)*n+j
    public boolean isMovable(int n) {

        // cerco le coordinate del tassello contenente n
        int[] coords = findCoords(n);
        int i = coords[0];
        int j = coords[1];

        // valuto separatamente i casi critici: lati e angoli (movimenti limitati)
        // restituisco true o false in base a che la casella considerata contenga 0

        if (i==0) {

            if (j==0) {                 // con i=0, j=0 posso muovermi solo a destra e in giù
                return numList[1] == 0 || numList[size] == 0;  // i*size=0 perché i=0, lo rimuovo

            } else if (j==size-1) {     // con i=0, j=n-1 posso muovermi solo a sx e in basso
                return numList[size-2] == 0 || numList[j + size] == 0;

            } else {                    // se i=0 non posso muovermi in su
                return numList[j + 1] == 0 || numList[j - 1] == 0 || numList[j+size] == 0;
            }

        } else if (i==size-1) {         // controllo i==size-1 e non size perché mi interessano gli indici (che vanno da 0 a n-1)

            if (j==size-1) {            // se i=n-1 e j=n-1, posso muovermi solo in su o a sinistra
                return numList[i * size + j - 1] == 0 || numList[i * size + j - size] == 0;

            } else if (j==0) {          // se i=n-1 e j=0 posso spostarmi solo a dx e in alto
                return numList[i * size + 1] == 0 || numList[i * size - size] == 0;

            } else {                    // se i=n-1 non posso muovermi in giù
                return numList[i * size + j + 1] == 0 || numList[i * size + j - 1] == 0 || numList[i * size + j - size] == 0;
            }

        } else if (j==0) {              // se j=0 non posso muovermi a sinistra; j==0 quindi lo rimuovo
            return numList[i * size + 1] == 0 || numList[i * size - size] == 0 || numList[i * size + size] == 0;

        } else if (j==size-1) {         // se j=n-1 non posso muovermi a destra
            return numList[i * size + j - 1] == 0 || numList[i * size + j - size] == 0 || numList[i * size + j + size] == 0;

        } else {                        // se non mi trovo lungo i bordi o negli angoli, posso muovermi in tutte e 4 le direzioni
            return numList[i * size + j + 1] == 0 || numList[i * size + j - 1] == 0
                    || numList[i * size + j - size] == 0 || numList[i * size + j + size] == 0;
        }

    }  // isMovable

    public void moveTile(int n) {  // d = destinazione

        int[] coordsN = findCoords(n);
        int i = coordsN[0];
        int j = coordsN[1];

        int[] coordsZ = findCoords(0);
        int x = coordsZ[0];
        int y = coordsZ[1];

        // sposto all'interno dell'array numList (usando i*n+j)
        numList[i*size+j] = 0;
        numList[x*size+y] = n;

        // modifico la matrice invertendo le coordinate
        tavoletta[i][j] = 0;
        tavoletta[x][y] = n;

    }  // moveTile

    // -----  INTEGRAZIONE GUI  ----------------------------------------------------------------------------------------

    public static void playGame(int n) {
        // creo nuova tavoletta con dimensione n (input utente)
        FifteenGame t = new FifteenGame(n);
        // creo interfaccia grafica
        PuzzleBoard gui = new PuzzleBoard(n);
        // riempio la tavoletta con i valori contenuti nella matrice
        for (int i=1; i<=n; i++) {
            for (int j=1; j<=n; j++) {
                gui.setNumber(i,j,t.tavoletta[i-1][j-1]);
            }
        }

        while (!t.isOrdered()) {

            // prendo numero che l'utente ha cliccato
            int k = gui.get();
            // prendo numero della casella su cui l'utente vuole spostare tassello
            //int h = gui.get();

            if (t.isMovable(k)) {

                // trovo coordinate di k
                int[] coordsK = t.findCoords(k);
                int i = coordsK[0];
                int j = coordsK[1];

                // trovo coordinate di h
                int[] coordsZ = t.findCoords(0);
                int x = coordsZ[0];
                int y = coordsZ[1];

                // muovo gli elementi nell'oggetto FifteenGame
                t.moveTile(k);

                // muovo gli elementi nella rappresentazione grafica
                // coordinate incrementate perché indici in PuzzleBoard iniziano da 1
                gui.clear(i+1,j+1);
                gui.setNumber(x+1,y+1,k);

                gui.display();
            }
        }
    }  // playGame



    // procedure per testing
    public int[][] getTavoletta() {
        return this.tavoletta;
    }
    public int[] getNumList() {
        return this.numList;
    }


}  // class FifteenGame
