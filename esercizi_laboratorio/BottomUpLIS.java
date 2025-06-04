
public class BottomUpLIS {
    
    /*
    La struttura di supporto appropriata per applicare la tecnica di memoization seguendo l’impostazione proposta al punto
    2 dell’esercitazione precedente è una matrice quadrata (n+1) x (n+1).
    Una matrice di questo tipo per la sequenza s = < 2, 7, 5, 7, 4 > è illustrata nella figura della pagina seguente (n=5)
    attraverso una griglia (tratti orizzontali e verticali blu) i cui nodi corrispondono agli elementi della matrice.
    Ciascuna colonna è associata a una “coda” della sequenza s a partire dall’indice i, e tale porzione della sequenza si
    legge in alto, guardando gli elementi in neretto a destra della colonna di indice i. In particolare, l’indice 0 corrisponde
    all’intera sequenza s (tutti gli elementi sono a destra), mentre n corrisponde a una coda di s vuota (non ci sono elementi
    a destra della colonna n).
    Ciascuna riga è invece associata a un valore del parametro t, in accordo con l’interpretazione t = s[j] se 0 ≤ j < n
    oppure t = 0 se j = n, dove j è l’indice di riga della matrice. Il valore di t, in verde nella figura, si legge a sinistra in
    corrispondenza alla riga j.
    Gli indici di colonna (i) e di riga (j) sono riportati in grigio, rispettivamente sotto la griglia e a destra della griglia.
    Questa stessa struttura può essere utilizzata per applicare una tecnica di programmazione dinamica bottom-up, basata su
    costrutti iterativi anziché sulla ricorsione, in cui il programmatore controlla esplicitamente ogni passo del processo di
    calcolo, in particolare l’ordine delle operazioni da effettuare per determinare i valori da assegnare ai nodi della griglia,
    cioè agli elementi della matrice. Si può anche osservare che i valori dei nodi compresi nell’area a fondo grigio non sono
    rilevanti al fine di calcolare la lunghezza della sottosequenza crescente più lunga di s in quanto, considerando i possibili
    argomenti delle invocazioni di llisRec, t ≠ 0 è sempre il valore di un elemento di s la cui posizione in s precede
    strettamente i e pertanto non può mai essere associato a un indice j ≥ i. (Ciò significa che ci si può occupare o meno di
    quei nodi, a seconda di cosa sembra più semplice; presta però attenzione al fatto che, pur essendo n ≥ i, n non è indice
    di un elemento della sequenza, ma corrisponde a t = 0, e quindi i nodi della riga più in basso sono tutti significativi.)
    Le schede llis_bottom_up.pdf, consultabili in forma di presentazione, illustrano il processo di elaborazione per
    assegnare valori agli elementi della matrice nell’esempio considerato. Gli archi rossi orientati che hanno origine in un
    nodo riflettono le ricorsioni di llisRec (una o due) per i valori dei parametri corrispondenti ai nodi coinvolti.*/

      // Length of Longest Increasing Subsequence (LLIS):
      // Programmazione dinamica bottom-up
  
    public static int llisDP( int[] s ) {
      
        int n = s.length;
        
        // creo matrice di dimensioni n+1 per avere anche l'indice n e non andare out of bounds
        int[][] mem = new int[ n+1 ][ n+1 ];
        
        // Matrice: valori delle ricorsioni di llisRec relativi a diversi valori degli argomenti 
        
        // registrazione valori corrispondenti ai casi base
        for ( int j=0; j<=n; j=j+1 ) {
            // inserisco in tutte le caselle della colonna i=n il valore 0 (i=n significa coda di s vuota)
            mem[n][j] = 0;
        }
        
        // registrazione valori corrispondenti ai casi ricorsivi
        for ( int i=n-1; i>=0; i=i-1 ) {
            for ( int j=0; j<=n; j=j+1 ) {
          
                // casi dell'if:
                // 1. caso in cui s[i] > s[j] -> Math.max(1 + llisRec(s, i+1, i, mem), llisRec(s, i+1, j, mem)
                // 2. caso in cui s[i] <= s[j] -> llisRec(s, i+1, j, mem)
    
                if ( j == n || (j < i && s[i] > s[j]) ) {  // accorpo il caso j=n (s[n] andrebbe out of bounds, abbiamo deciso che rappresenta 0)
                    // pongo l'ulteriore condizione j<i perché l'indice della soglia deve sempre essere < indice del valore preso in esame (la soglia precede sempre il valore)
                    // valuto quale sia il valore massimo fra i casi in cui aumento la soglia
                    // oppure mantengo la stessa anche per il passo successivo
                    mem[i][j] = Math.max(1 + mem[i+1][i], mem[i+1][j]); 
                } else { // if (s[i] <= s[j])
                    mem[i][j] = mem[i+1][j];  // in questo caso mi riconduco ad un caso già precedentemente valutato (quello con i incrementato)
                }
            }
        }
    
        // elemento della matrice corrispondente a llis(s):
        // questa casella avrà accumulato tutti i risultati precedenti
        // e quindi restituirà la lunghezza della sottosequenza più lunga
        // con i=0 (ovvero tutto l'array, la coda intera) e j=0 (soglia iniziale t=0)
        return  mem[0][n];
    
    }
  
    /*
    Analogamente a quanto visto a lezione per il problema della sottosequenza comune più lunga (LCS), a partire dalla
    matrice risultante alla fine dell’elaborazione oggetto del punto precedente è possibile ricostruire una sottosequenza
    crescente più lunga (LIS). A tale proposito è sufficiente identificare un opportuno cammino attraverso la matrice che
    percorre i nodi corrispondenti alle ricorsioni di llisRec che hanno contribuito al risultato finale.
    Le schede lis_percorso.pdf, consultabili in forma di presentazione, illustrano il processo di elaborazione per
    identificare un percorso utile nell’esempio considerato sopra. Gli archi verdi riflettono le ricorsioni di llisRec che
    hanno fornito i valori utilizzati per calcolare il risultato: in base alla codifica adottata per egli argomenti di llisRec,
    archi orizzontali corrispondono a elementi di s non selezionati; archi inclinati corrispondono a elementi presi a far parte
    della sottosequenza (evidenziati in questo caso dal fondo verde chiaro).
    Completa il programma preimpostato nel file BottomUpLIS.java, senza modificare le parti già codificate, per
    realizzare la procedura lisDP (metodo statico) applicando una tecnica di programmazione dinamica bottom-up
    completata da un cammino attraverso la matrice in accordo con le indicazioni fornite. Verifica quindi che i risultati
    ottenuti siano coerenti con quanto ci si dovrebbe attendere.*/
  
    // Longest Increasing Subsequence (LIS):
    // Programmazione dinamica bottom-up
      
    public static int[] lisDP( int[] s ) {
      
        int n = s.length;
        
        int[][] mem = new int[ n+1 ][ n+1 ];
        
        // 1. Matrice: valori delle ricorsioni di llisRec
        //    calcolati esattamente come per llisDP
        
        // registrazione valori corrispondenti ai casi base
        for ( int j=0; j<=n; j=j+1 ) {
            // inserisco in tutte le caselle della colonna i=n il valore 0 (i=n significa coda di s vuota)
            mem[n][j] = 0;
        }
        
        // registrazione valori corrispondenti ai casi ricorsivi
        for ( int i=n-1; i>=0; i=i-1 ) {
            for ( int j=0; j<=n; j=j+1 ) {
          
                // casi dell'if:
                // 1. caso in cui s[i] > s[j] -> Math.max(1 + llisRec(s, i+1, i, mem), llisRec(s, i+1, j, mem)
                // 2. caso in cui s[i] <= s[j] -> llisRec(s, i+1, j, mem)
    
                if ( j == n || (j < i && s[i] > s[j]) ) {  // accorpo il caso j=n (s[n] andrebbe out of bounds, abbiamo deciso che rappresenta 0)
                    // pongo l'ulteriore condizione j<i perché l'indice della soglia deve sempre essere < indice del valore preso in esame (la soglia precede sempre il valore)
                    // valuto quale sia il valore massimo fra i casi in cui aumento la soglia
                    // oppure mantengo la stessa anche per il passo successivo
                    mem[i][j] = Math.max(1 + mem[i+1][i], mem[i+1][j]); 
                } else { // if (s[i] <= s[j])
                    mem[i][j] = mem[i+1][j];  // in questo caso mi riconduco ad un caso già precedentemente valutato (quello con i incrementato)
                }
            }
        }
        
        // 2. Cammino attraverso la matrice per ricostruire
        //    un esempio di Longest Increasing Subsequence
        
        // elemento della matrice il cui valore corrisponde a llis(s):
        int m =  mem[0][n];
        
        int[] r = new int[ m ];  // per rappresentare una possibile LIS
                                 // array di lunghezza llis(s)
        
        int i = 0;
        int j = n;
        int rIndex = 0;
    
        while ( mem[i][j] > 0 ) {  // itero finché il contenuto della cella della matrice non è pari a 0
        
            int t = ( j == n ) ? 0 : s[j]; // t=0 se j=n, altrimenti t=s[j]
          
            if (1 + mem[i+1][i] >= mem [i+1][j]) {  // 1+mem[i][j] è il ramo della ricorsione in cui aggiungo un numero all'array
                                                    // se aggiungendo il numero il risultato è maggiore, prendo questo ramo e mi sposto nella casella corrispondente
                if (s[i] > t) {        // se il valore nell'array è maggiore della soglia...
                    r[rIndex] = s[i];  // ...aggiungo questo valore all'array del risultato
                    rIndex++;          // e aumento l'indice per il prossimo ciclo
                }
              
                // mi sposto nella matrice in corrispondenza di mem[i+1][i]
                j = i;  // PRIMA associo a j il valore di i, poi incremento i!
                i = i+1;
              
            } else {  // se il risultato maggiore si trova in corrispondenza del secondo ramo (freccia orizzontale)
                      // significa che non devo aggiungere numeri all'array, ma comunque devo spostarmi orizzontalmente
                      // quindi rimango sulla stessa riga (j=j) ma mi sposto a destra (i=i+1)
                i = i+1;
                j = j;
              
            }
          
        }
        
        return r;                // = LIS relativa alla sequenza s
    }
  

  
}  // class BottomUpLIS
