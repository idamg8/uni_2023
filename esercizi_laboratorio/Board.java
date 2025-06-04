import queens.*;

/**
 *   Board b = new Board(n);
 *   
 *   b.size()           : int
 *   b.queensOn()       : int
 *   b.underAttack(i,j) : boolean
 *   b.addQueen(i,j)    : void
 *   b.arrangement()    : String
 */

// Una rappresentazione interna alternativa delle istanze della classe Board si compone di 7 elementi (variabili di istanza):
// • la dimensione della scacchiera (int);
// • il numero di regine collocate nella scacchiera (int);
// • 4 liste di indici (IntSList oppure SList<Integer>), per rappresentare rispettivamente le codifiche
//   numeriche delle righe, delle colonne, delle diagonali ascendenti verso destra e delle diagonali discendenti verso
//   destra che sono minacciate da una regina collocata sulla scacchiera;
// • la codifica testuale della configurazione secondo le convenzioni in uso da parte degli scacchisti (String).

// In particolare, righe e colonne possono essere codificate dalle corrispondenti coordinate; le
// diagonali ascendenti verso destra dalla differenza delle coordinate delle caselle che ne
// fanno parte, che è invariante per una diagonale di questo tipo; quelle discendenti verso
// destra dalla somma invariante delle coordinate. In base ai criteri delineati, una possibile
// rappresentazione della configurazione della scacchiera 5 x 5 illustrata qui a fianco è la
// seguente, dove le righe sono numerate dal basso verso l’alto a partire da 1, come è
// consueto nella formulazione schematica dei problemi del gioco degli scacchi:
// < 5 , 2 , (3, 1) , (3, 4) , (0, -3) , (6, 5) , " d1 c3 " >
// In altri termini, dei 7 elementi che caratterizzano la nuova rappresentazione degli oggetti di tipo Board, i primi due e
// l’ultimo sono gli stessi utilizzati nella versione discussa a lezione, mentre le quattro liste di indici sostituiscono il
// predicato. Questa nuova impostazione consente di controllare se la riga, la colonna, l’una o l’altra diagonale che si
// incrociano in corrispondenza a una casella della scacchiera di coordinate (x,y) sono minacciate da qualche regina
// verificando se le rispettive codifiche (rispettivamente x, y, x–y e x+y) appartengono o meno alle liste appropriate.

public class Board {
    // codifica testuale dei numeri associati alle righe
    private static final String ROWS = " 123456789ABCDEF";
    // codifica testuale dei numeri associati alle colonne
    private static final String COLS = " abcdefghijklmno";
    private static final IntSList NULL_INTLIST = new IntSList();
    
    // variabili d'istanza per il costruttore
    private final int size;  
    private final int queens;
    private final String config;
    
    // indici delle righe minacciate
    private final IntSList rows;
    // indici delle colonne minacciate
    private final IntSList cols;
    // indici delle diagonali ascendenti minacciate
    private final IntSList dg1;
    // indici delle diagonali discendenti minacciate
    private final IntSList dg2;
    
    
    public Board (int n) {  // inizializzazione di una nuova board vuota
        size = n;
        queens = 0;
        config = " ";
        
        // con board vuota, nessuna riga/colonna/diagonale è minacciata
        rows = NULL_INTLIST;
        cols = NULL_INTLIST;
        dg1 = NULL_INTLIST;
        dg2 = NULL_INTLIST;
        
    }
    
    // costruttore privato utilizzato per addQueen()
    private Board (Board b, int i, int j) {  // i,j coordinate della regina aggiunta
        size = b.size;
        queens = b.queens + 1;
        config = b.arrangement() + COLS.charAt(j) + ROWS.charAt(i) + " ";  
        
        rows = b.rows.cons(i);  // i: indice di riga
        cols = b.cols.cons(j);  // j: indice di colonna
        dg1 = b.dg1.cons(i-j);  // i-j: invariante sulle diagonali ascendenti
        dg2 = b.dg2.cons(i+j);  // i+j: invariante sulle diagonali discendenti
        
    }
    
    public int size() {
        return size;
    }
    
    public int queensOn() {
        return queens;
    }
    
    public boolean underAttack(int i, int j) {
        // check se riga è minacciata
        for (int x = 0; x < this.rows.length(); x++) {
            if (i == this.rows.listRef(x)) {
                return true;
            }
        }
        // check se colonna è minacciata
        for (int x = 0; x < this.cols.length(); x++) {
            if (j == this.cols.listRef(x)) {
                return true;
            }
        }
        // check se diagonale ascendente è minacciata
        for (int x = 0; x < this.dg1.length(); x++) {
            if (i-j == this.dg1.listRef(x)) {
                return true;
            }
        }
        // check se diagonale discendente è minacciata
        for (int x = 0; x < this.dg2.length(); x++) {
            if (i+j == this.dg2.listRef(x)) {
                return true;
            }
        }
        // se tutti i check precedenti falliscono:
        return false;
    }
    
    public Board addQueen(int i, int j) {
        return new Board(this, i, j);  // il costruttore privato Board(b,i,j) si occupa effettivamente di aggiungere la regina
    }
    
    public String arrangement() {
        return config;
    }
    
    public String toString() {
        // return "[" + arrangement() + "]";
        return " " + arrangement() + " ";
    }
    
    // ------------------------------------------------------------
    
    public static void viewSolutions(SList<Board> list) {
        // prendo la prima board della lista
        Board sample = list.car();
        int size = sample.size();
        // creo una gui della dimensione del primo elemento della lista (tutti gli elementi hanno la stessa dimensione)
        ChessboardView gui = new ChessboardView(size);
        
        int length = list.length();
        
        for (int x=0; x<length; x++) {  // per tutte le board della lista, prendo il loro arrangement (disposizione delle regine) e lo visualizzo sulla gui
            Board first = list.car();

            String arr = first.arrangement();
            
            gui.setQueens(arr);
            
            list = list.cdr();  // facendo il cdr, al prossimo ciclo il car restituirà l'elemento successivo
                                // in tal modo ciclerò per tutti gli elementi della lista
        }
        
    }
    
    
}  // class Board
