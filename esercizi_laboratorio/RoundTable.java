
public class RoundTable {
    
    private final int quanti;
    private final IntSList brocca;
    private final IntSList lista1;
    private final IntSList lista2;
    
    private static final IntSList NULL_INTLIST = new IntSList();
    
    // costruttore pubblico: crea nuova tavola rotonda
    public RoundTable(int n) {
        quanti = n;  // quando creo una nuova tavola, il numero di cavalieri è n
        brocca = IntSList.intervallo(1,2);  // la lista di cavalieri con la brocca è (1,2)
        lista1 = IntSList.intervallo(3,n);  // la lista degli altri cavalieri va da 3 a n
        lista2 = NULL_INTLIST;  // la lista di supporto è vuota
    }
    
    // costruttore privato: necessario per serveNeighbor() e passJug()
    // crea una nuova configurazione della tavola sulla base dei valori passati da serveNeighbor() e passJug()
    private RoundTable(int q, IntSList b, IntSList l1, IntSList l2) {  
        quanti = q;  
        brocca = b;
        lista1 = l1;
        lista2 = l2;
    }
    
    // procedura che restituisce numero cavalieri rimasti a tavola
    public int numberOfKnights() {
        return quanti;
    }
    
    // procedura che ritorna lista di 2 cavalieri con la brocca
    public IntSList servingKnights() {
        return brocca;
    }
    
    // procedura che restituisce nuova tavola rotonda dopo aver servito il vicino
    public RoundTable serveNeighbor() { 
        if (lista1.isNull()) {  // se la lista1 è vuota
            IntSList r = lista2.reverse();  // rovescio lista2 e la metto in lista 1
            return new RoundTable(quanti-1, brocca, r.cdr(), NULL_INTLIST);  // quando servo rimuovo un cavaliere, tutto il resto rimane uguale
                                                                             // rimuovere un cavaliere significa: 
                                                                             // quanti-1 | togliere il cavaliere dalla lista degli "altri" mediante cdr()
        } else {
            return new RoundTable(quanti-1, brocca, lista1.cdr(), lista2);  // se lista1 non è vuota la mantengo, togliendo solo il primo elemento
        }
    }
    
    // procedura che ritorna nuova tavola dopo aver passato la brocca
    public RoundTable passJug() {
        
        if (quanti == 2) {
            
            return this;  // se ci sono solo 2 cavalieri rimasti sono giunto alla conclusione, restituisco la configurazione attuale
            
        } else if (lista1.length() < 2) {  // se lista1 ha meno di 2 elementi, non ce ne sono a sufficienza per trovare i prossimi 2 possessori della brocca
                                           // devo quindi lavorare modificando lista1, con il supporto di lista2
            
            IntSList r = lista2.cons(brocca.car()).cons(brocca.cdr().car()); // mette i 2 elementi di brocca in lista 2
            IntSList s = lista1.append(r.reverse()); // mette r (attuale lista2) in lista 1, dopo l'elemento già presente in lista1
            IntSList b = NULL_INTLIST.cons(s.car()).cons(s.cdr().car()); // prende i primi due elementi di lista1 per farli diventare brocca (da fare reverse poiché uso cons)
            
            return new RoundTable(quanti, b.reverse(), s.cdr().cdr(), NULL_INTLIST);
            
        } else {  // se lista1 ha più di due elementi, posso lavorare direttamente su di essa
            
            IntSList b = NULL_INTLIST.cons(lista1.car()).cons(lista1.cdr().car());  // i primi due elementi di lista1 diventeranno brocca
            IntSList h = lista2.cons(brocca.car()).cons(brocca.cdr().car());  // in lista2 aggiungerò i due cavalieri che prima avevano la brocca, a rovescio
            
            return new RoundTable(quanti, b.reverse(), lista1.cdr().cdr(), h);
            
        }
        
    }
     
    

}  // class RoundTable
