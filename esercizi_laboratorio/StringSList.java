
public class StringSList
{
    public static final StringSList NULL_STRINGLIST = new StringSList();  // creo lista vuota
    
    // variabili d'istanza per il costruttore della lista
    private final boolean empty;
    private final String first;
    private final StringSList rest;
    
    
    public StringSList() {
        empty = true;   // lista nuova è vuota
        first = null;   // non c'è primo elemento lista
        rest = null;    // non c'è resto lista
    }
    
    
    private StringSList(String e, StringSList sl) {  // costruttore privato con parametri forniti da altre funzioni
        empty = false;
        first = e;
        rest = sl;
    }
    
    
    public boolean isNull() {
        return empty;
    }
    
    
    public String car() {
        return first;
    }
    
    
    public StringSList cdr() {
        return rest;
    }
    
    
    public StringSList cons(String e) {
        return new StringSList(e, this);  // mette e come primo elemento, this (ovvero l'oggetto a cui è applicato) come resto
    }
    
    
    public int length() {
        if (isNull()) {
            return 0;
        } else {
            return 1 + this.cdr().length();  // procedo con il cdr finché la lista si svuota completamente
        }
    }
    
    // ritorna elemento in posizione k della lista
    public String listRef(int k) {
        if (k == 0) {
            return this.car();
        } else {
            return this.cdr().listRef(k-1);     // applico list-ref al cdr finché la lista non si svuota 
                                                // devo anche decrementare l'indice di posizione k poiché ora la mia lista ha dimensione minore
        }
    }
    
    // ritorna se due liste sono uguali
    public boolean equals(StringSList sl) {
        if (this.isNull()) {
            return sl.isNull();  // se anche sl è null sarà true, altrimenti false
        } else if (sl.isNull()) {
            return false;  // ritorno subito false perché, se entriamo in questo caso, significa che this non è null
        } else {
            if (this.car().equals(sl.car())) {
                return this.cdr().equals(sl.cdr());  // procedo a confrontare il cdr delle due liste
            }   else {
                return false;  // se i due elementi che confronto sono diversi, le due liste sono diverse
            }
        }
    }
    
    // aggiunge la lista sl in coda a quella a cui applico il metodo
    public StringSList append(StringSList sl) {
        if (this.isNull()) {
            return sl;
        } else {
            return (this.cdr().append(sl)).cons(this.car());    // metto il primo elemento di this in prima posizione
                                                                // procedo ricorsivamente con il cdr finché la lista si svuota
                                                                // a quel punto entrerò nel caso base
        }
    }
    
    // inverte la lista
    public StringSList reverse() {              
        return reverseRec( new StringSList() );  // parametro sarà re della funzione ricorsiva
    }
    
    private StringSList reverseRec( StringSList re ) {  // metodo di supporto: private!
        if ( isNull() ) {
            return re;  // dal metodo reverse viene passata una stringa vuota, che corrisponde al reverse di una stringa vuota
        } else {
            return this.cdr().reverseRec( re.cons(car()) );     // procedo con il cdr di this, aggiungendo a re il primo elemento di this finché la lista non si svuoterà
                                                                // a quel punto avrò aggiunto tutti gli elementi a re ma al contrario (poiché uso cons)
        }
    }
    
    
    public String toString() {
          if (this.isNull()) {
              return "()";  // rappresentazione di lista vuota
          } else {
            String lst = "(" + this.car();  // inizializzo la rappresentazione testuale con il primo elemento della lista
            StringSList r = this.cdr();
            while (!r.isNull()) {  // procedo con il ciclo finché r si svuota
                lst = lst + ", " + r.car();  // aggiungo ogni volta un nuovo elemento separato con una virgola
                r = r.cdr();  // aggiorno r con il cdr (finché non si svuota completamente)
            }
            return lst + ")";  // ritorno il risultato con la parentesi di chiusura
      }
    }
    
} // class StringSList
