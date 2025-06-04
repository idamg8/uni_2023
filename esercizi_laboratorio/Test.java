import queens.*;

public class Test {
    
    // RoundTable
    public static IntSList josephus(int n) {
        RoundTable tr = new RoundTable(n);  // creazione di nuova tavola con n cavalieri
        
        while (tr.numberOfKnights() > 2) {
            tr = tr.serveNeighbor();
            tr = tr.passJug();
        }
        return tr.servingKnights();
    }
    
    // -----------------------------------------------------------------------------------------
    
    // Board/BoardMod
    public static int numberOfSolutions( int n ) {
        return numberOfCompletions( new BoardMod(n) );
    }
    
    private static int numberOfCompletions( BoardMod b ) {
        int n = b.size();
        int q = b.queensOn();
    
        if ( q == n ) {
    
            return 1;
    
        } else {
    
            int i = q + 1;  // se ho q regine, la prossima regina andrà posizionata in riga q+1
                            // es. 3 regine posizionate nelle prime 3 righe, la prossima andrà in riga 4
            int count = 0;
      
            for ( int j=1; j<=n; j=j+1 ) {      // per tutte le caselle della riga i...
                if ( !b.underAttack(i,j) ) {    // controllo se la casella i,j è sotto attacco
        
                   count = count + numberOfCompletions( b.addQueen(i,j) );  // se non lo è, calcolo il numero di soluzioni aggiungendo una regina in quella casella
                }
            }
            
            return count;
        }
    }
  
    // -----------------------------------------------------------------------------------------
    
    // Board/BoardMod
    public static SList<Board> listOfAllSolutions( int n ) {
  
        return listOfAllCompletions( new Board(n) );
    }
  
    public static final SList<Board> NULL_BOARDLIST = new SList<Board>();
    
    private static SList<Board> listOfAllCompletions( Board b ) {
  
        int n = b.size();
        int q = b.queensOn();
    
        if ( q == n ) {
    
            return ( NULL_BOARDLIST.cons(b) );
    
        } else {
    
            int i = q + 1;
            SList<Board> solutions = NULL_BOARDLIST;
      
            for ( int j=1; j<=n; j=j+1 ) {
                if ( !b.underAttack(i,j) ) {
        
                  solutions = solutions.append( listOfAllCompletions(b.addQueen(i,j)) );
                }
            }
            return solutions;
        }   
    }
    
    // -----------------------------------------------------------------------------------------
    
    // Board -> visualizzazione grafica
    
    public static void view(int n) {
        SList<Board> list = listOfAllSolutions(n);
        
        Board.viewSolutions(list);
        
    }
    
    // -----------------------------------------------------------------------------------------

    
    
} // class Test
