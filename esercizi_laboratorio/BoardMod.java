
/**
 *   Board b = new Board(n);
 *   
 *   b.size()           : int
 *   b.queensOn()       : int
 *   b.underAttack(i,j) : boolean
 *   b.addQueen(i,j)    : void
 *   b.arrangement()    : String
 */

// In relazione al rompicapo delle N regine, modifica la realizzazione della classe Board sviluppata nella precedente
// esercitazione in laboratorio (problema 12) utilizzando un’istanza di SList<SList<Integer>> per rappresentare le
// coppie di coordinate in cui sono collocate le regine disposte sulla scacchiera (in sostituzione delle quattro liste di interi).
// Il tipo degli elementi di una lista può essere infatti a sua volta una lista — nel caso specifico la lista di due interi che
// denota una coppia di coordinate. Intendendo come di consueto le liste in stile Scheme, l’interpretazione del costrutto
// composto SList<SList<Integer>> è dunque “liste di (liste di interi)” e risulta perfettamente definita in Java.
// Sulla base di questa impostazione, una possibile rappresentazione della configurazione
// della scacchiera 5 x 5 illustrata qui a fianco è la seguente, dove le righe sono numerate dal
// basso verso l’alto e le colonne da sinistra a destra, in entrambi i casi a partire dall’indice 1:
// < 5 , 3 , ((1, 4) , (3, 3) , (4, 5) ) , " d1 c3 e4 " >
// Dei 4 elementi che caratterizzano la nuova realizzazione, i primi due e l’ultimo sono gli
// stessi utilizzati in precedenza. Per verificare se una casella di coordinate (i, j) è minacciata
// da una delle regine regine disposte sulla scacchiera sarà poi sufficiente controllare se per
// una delle coppie (u, v) contenute nella lista di tipo SList<SList<Integer>> si abbia
// i = u oppure j = v oppure i–j = u–v o infine i+j = u+v.
// Sperimenta questa nuova versione della classe Board attraverso il programma Queens.java, discusso a lezione, che
// codifica gli algoritmi per determinare il numero di soluzioni e la lista delle soluzioni — di tipo SList<Board>.
// Verifica, in particolare, il numero delle soluzioni che si ottengono al variare della dimensione della scacchiera.

public class BoardMod {
    // codifica testuale dei numeri associati alle righe
    private static final String ROWS = " 123456789ABCDEF";
    // codifica testuale dei numeri associati alle colonne
    private static final String COLS = " abcdefghijklmno";
    private static final SList<Integer> NULL_INTLIST = new SList<Integer>();
    
    private final int size;  
    private final int queens;
    private final String config;
    
    // lista di liste contenente le coordinate delle regine presenti sulla scacchiera
    private final SList<SList<Integer>> queenCoords;
    
    
    public BoardMod (int n) {  // inizializzazione di una nuova board vuota
        size = n;
        queens = 0;
        config = " ";
        
        // la lista delle coordinate delle regine è vuota poiché non ci sono regine
        queenCoords = new SList<SList<Integer>>();
        
    }
    
    // costruttore privato utilizzato per addQueen()
    private BoardMod(BoardMod b, int i, int j) {
        size = b.size;
        queens = b.queens + 1;
        config = b.arrangement() + COLS.charAt(j) + ROWS.charAt(i) + " "; 
        
        // creo lista (coppia) contenente le coordinate della regina
        SList<Integer> newQueen = NULL_INTLIST.cons(j).cons(i);
        // aggiungo tale lista alla lista di tutte le coordinate delle regine
        queenCoords = b.queenCoords.cons(newQueen);
    }
    
    public int size() {
        return size;
    }
    
    public int queensOn() {
        return queens;
    }
    
    public boolean underAttack(int i, int j) {  // i,j: coordinate della casella da esaminare
        // a ogni ciclo prende lista (u,v) in posizione x della lista queenCoords (da 0 a n-1)
        // compara se i/j/i-j/i+j sono uguali a u/v/u-v/u+v 
        // se sì ritorna true ed esce, altrimenti continua a ciclare
        for (int x=0; x < queenCoords.length(); x++) {
            SList<Integer> comp = queenCoords.listRef(x);  // comp = comparison
            
            if (i == comp.car()) {
                return true;
            } else if (j == comp.cdr().car()) {
                return true;
            } else if (i-j == comp.car() - comp.cdr().car()) {
                return true;
            } else if (i+j == comp.car() + comp.cdr().car()) {
                return true;
            }
            
        }
        // se tutti i check precedenti falliscono:
        return false;
    }
    
    public BoardMod addQueen(int i, int j) {
        return new BoardMod(this, i, j);
    }
    
    public String arrangement() {
        return config;
    }
    
    public String toString() {
        return "[" + arrangement() + "]";
    }
    
    
}  // class BoardMod
