
public class LLIS {
    
    /*
    Data una sequenza s di n interi positivi, rappresentata da un array, il seguente programma in Java calcola la lunghezza
    della più lunga sottosequenza di s strettamente crescente (llis: length of the longest increasing subsequence).
    Una sottosequenza strettamente crescente di s è formata da una selezione dei suoi elementi che, considerati esattamente
    nell’ordine in cui compaiono in s, determinano una sequenza di interi crescente in senso stretto. Per esempio, in
    relazione alla sequenza s = <2, 7, 5, 7, 4>, le sottosequenze <2, 4> e <5, 7> sono strettamente crescenti, ma non
    della lunghezza massima possibile, <2, 5, 7> è una sottosequenze crescente più lunga (di fatto l’unica nel caso
    considerato), mentre <2, 7, 7> non è strettamente crescente e <2, 4, 5, 7> non è una sottosequenza di s perché non è
    rispettato l’ordine degli elementi.
    Il programma per llis si basa essenzialmente sulla procedura ricorsiva llisRec, che risolve un problema correlato
    per una “coda” della sequenza originale s. Più precisamente, llisRec calcola la lunghezza della sottosequenza
    crescente più lunga relativa agli elementi di s a partire dalla posizione i, corrispondenti cioè all’intervallo di indici
    compresi fra i e n–1, imponendo l’ulteriore vincolo che gli elementi della sottosequenza debbano essere strettamente
    maggiori del valore di un parametro aggiuntivo t che funge da soglia. I casi base di llisRec corrispondono a una coda
    vuota (i = n). Se l’elemento x nella posizione iniziale i non soddisfa il vincolo x > t, allora non può far parte della
    sottosequenza misurata da llisRec e ci si riconduce direttamente alla corrispondente istanza del problema per una
    coda più corta che inizia in posizione i+1. Altrimenti x può far parte o meno della sottosequenza più lunga: se vi fa
    parte, l’elemento va contato ai fini della lunghezza (1+) e i successivi elementi, da cercare a partire da i+1, devono
    essere maggiori di x; se invece non vi fa parte il vincolo aggiuntivo resta determinato da t anche quando si restringe la
    ricerca alla coda che inizia in posizione i+1; si sceglierà poi l’opzione più favorevole in base ai risultati ottenuti
    ricorsivamente, confrontando le lunghezze nei due casi.
    Per quanto riguarda la prima invocazione di llisRec da parte di llis, i = 0 identifica l’intera sequenza e t = 0 non
    vincola la scelta del primo elemento della sottosequenza poiché tutti gli interi di s sono positivi e quindi maggiori di 0.
    Di conseguenza, llisRec(s,0,0) calcola precisamente la lunghezza della più lunga sottosequenza crescente di s. */
    
    /**public static int llis( int[] s ) {  // s[i] > 0 per i in [0,n-1], dove n = s.length
        return llisRec( s, 0, 0 );
    }
    
    private static int llisRec( int[] s, int i, int t ) {
        if ( i == s.length ) {          // i = n : coda di s vuota
            return 0;
        } else if ( s[i] <= t ) {       // x = s[i] ≤ t : x non può essere scelto
            return llisRec( s, i+1, t );
        } else {                        // x > t : x può essere scelto o meno
            return Math.max( 1+llisRec(s,i+1,s[i]), llisRec(s,i+1,t) );
        }
    }*/
    
    // dichiarazione della costante utilizzata per indicare che una casella della matrice è sconosciuta
    private static final int UNKNOWN = -1;
    
    
    public static int llisMem1(int[] s) {
        int n = s.length;  // funzione predefinita per lunghezza array
        
        int[][] matrix = new int[n+1][n+1];  // matrice grande n+1 per avere anche l'indice n (altrimenti si andrebbe out of bounds)
        
        for (int x=0; x<=n; x++) {
            for (int y=0; y<=n; y++) {
                matrix[x][y] = UNKNOWN;  // inizialmente segno tutta la matrice come sconosciuta
            }
        }
        
        return llisRec1(s, 0, 0, matrix);
    }
    
    private static int llisRec1(int[] s, int i, int t, int[][] matrix) {
        
        if (matrix[i][t] == UNKNOWN) {
            if ( i == s.length ) {          // i = n : coda di s vuota
                matrix[i][t] = 0;
            } else if ( s[i] <= t ) {       // x = s[i] ≤ t : x non può essere scelto, mi sposto quindi all'indice successivo
                matrix[i][t] = llisRec1( s, i+1, t, matrix );
            } else {                        // x > t : x può essere scelto o meno
                matrix[i][t] = Math.max( 1 + llisRec1(s,i+1,s[i], matrix), llisRec1(s,i+1,t, matrix) );  // valuto la migliore opzione tra aggiornare la soglia o mantenere la stessa
            }
        }
        
        return matrix[i][t];  // i=0, t=0 significa tutta la sequenza ("coda intera") e nessuna soglia (soglia 0)
    }
    
    // --------------------------------------------------------------------------------------------------------
    
    /*
    Estendi ora il programma che applica la tecnica top-down di memoization ai casi più generali in cui gli elementi della
    sequenza possano assumere qualunque valore intero (di tipo int) rappresentabile e verifica che i risultati ottenuti siano
    sempre coerenti con i valori calcolati dal programma riportato sopra.
    Suggerimento.
    • Poiché in generale il terzo argomento di llisRec può assumere valori molto grandi, non è ragionevole
    utilizzare i valori di t direttamente come indici di array, cosa che richiederebbe di allocare uno spazio in
    memoria di estensione esorbitante. Tuttavia è evidente che t è zero oppure è il valore di un elemento della
    sequenza s, nel qual caso lo si può rappresentare indirettamente attraverso la posizione di quella componente.
    • Inoltre, il caso t = 0 può essere a sua volta rappresentato indirettamente utilizzando un intero diverso dagli indici
    della sequenza, per esempio n (∉ [0, n–1]).
    • Alla luce delle osservazioni precedenti, al fine di rielaborare il programma attraverso una tecnica di
    memoization, si può sostituire il parametro della proceedura ricorsiva corrispondente a t (rappresentato
    direttamente) con un indice j compreso nell’intervallo [0, n] (che rappresenta t indirettamente). A partire
    dall’indice j sarà comunque possibile risalire facilmente al valore della soglia t, sulla base dell’interpretazione
    t = s[j] se 0 ≤ j < n, oppure t = 0 se j = n.*/
   
    public static int llisMem2(int[] s) {
        int n = s.length;
        
        // creo un nuovo array contenente gli stessi elementi di quello preso in input, e uno 0 finale
        int[] s1 = new int[n+1];
        for (int i = 0; i < n; i++) {
            s1[i] = s[i];
        }
        
        int[][] matrix = new int[n+1][n+1];  // matrice grande n+1 per avere anche l'indice n (altrimenti si andrebbe out of bounds)
        
        for (int x=0; x<=n; x++) {
            for (int y=0; y<=n; y++) {
                matrix[x][y] = UNKNOWN;
            }
        }
        
        return llisRec2(s1, 0, n, matrix);  // passo n come indice j (s[j] = s[n] = 0, soglia 0)
    }
    
    private static int llisRec2(int[] s1, int i, int j, int[][] matrix) {
        // uso come metro di misura la lunghezza del mio array originale s (che era di 1 più corto)
        int n = s1.length - 1;
        
        if (matrix[i][j] == UNKNOWN) {
            if ( i == n ) {                      // i = n : coda di s vuota
                matrix[i][j] = 0;
            } else if ( s1[i] <= s1[j] ) {       // x = s[i] ≤ s[j](=t) : x non può essere scelto
                matrix[i][j] = llisRec2( s1, i+1, j, matrix );
            } else {                             // x > t : x può essere scelto o meno
                matrix[i][j] = Math.max( 1 + llisRec2(s1, i+1, i, matrix), llisRec2(s1, i+1, j, matrix) );
            }   // nel primo caso j diventa i (la soglia sarà il valore contenuto in s[i]), nel secondo caso rimane s[j] invariato
        }
        
        return matrix[i][j];  // i=0, j=n: coda di s intera, soglia t=0 (s[n] = 0)
    }
   
    
}  // class LLIS
