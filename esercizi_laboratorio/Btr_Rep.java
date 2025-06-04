
public class Btr_Rep {
    
    /*
     * Dato un intero non negativo in notazione ternaria bilanciata (stringa di cifre –/./+), la seguente procedura in Scheme
     * restituisce la rappresentazione dell’intero successivo, calcolata operando direttamente sulle cifre a livello testuale:
        (define btr-succ ; val: stringa di -/./+
             (lambda (btr) ; btr: stringa di -/./+
                 (let ((n (string-length btr))) ; (brt = "." oppure inizia con "+")
                 (let ((lsb (string-ref btr (- n 1))))
                     (if (= n 1)
                         (if (char=? lsb #\+)
                         "+-"
                         "+")
                         (let ((pre (substring btr 0 (- n 1))))
                         (if (char=? lsb #\+)
                             (string-append (btr-succ pre) "-")
                             (string-append pre (if (char=? lsb #\-) "." "+"))
                         ))
                 )))
             ))*/
    
    public static String btrSucc(String btr) {
        int n = btr.length();           // lunghezza stringa btr
        char lsb = btr.charAt(n-1);     // carattere in ultima posizione
        
        if (n == 1) {  
            if (lsb == '+') {
                return "+-";    // il successivo di + è +-
            } else {
                return "+";     // il successivo . è + (non consideriamo il caso - perché l'intero dev'essere non negativo)
            }
        } else {
            String pre = btr.substring(0, n-1);  // sottostringa di btr a cui tolgo l'ultimo carattere
            if (lsb == '+') {
                return btrSucc(pre) + "-";  // in questo caso c'è un riporto da aggiungere ("+" + "+" = "+-", devo aggiungere il "+" al penultimo carattere con btrSucc)
            } else if (lsb == '-'){
                return pre + ".";  // - incrementato diventa . (-1 + 1 = 0)
            } else { 
                return pre + "+";  // . incrementato diventa + (0 + 1 = 1)
            }
        }
        }
        
        
    /**
     * Complemento a uno:

        (define bit-complement   ; val: stringa
          (lambda (bit)          ; bit: stringa
            (if (string=? bit "0")
                "1"
                "0"
                )))
        
        (define ones-complement  ; val: stringa di 0/1
          (lambda (bin)          ; bin: stringa di 0/1
            (if (string=? bin "")
                ""
                (string-append
                 (ones-complement (substring bin 0 (- (string-length bin) 1)))
                 (bit-complement (substring bin (- (string-length bin) 1)))
                 ))
            ))
     */
   
    public static String OnesComplement (String str) {
        String nuova = "";
        
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '1') {
                nuova = nuova + "0";
            } else {
                nuova = nuova + "1";
            }
        }
        
        return nuova;
    }
    
    // Data una stringa btr (oggetto di tipo String) che rappresenta un intero non negativo nella
    // notazione BTR (Balanced Ternary Representation) e dato un intero non negativo n (valore di tipo int), restituisce la
    // lista di n interi consecutivi in notazione BTR, a partire da btr. 
    // Per esempio, se btr = "+-" e n = 5, la lista risultante deve contenere le cinque stringhe "+-", "+.", "++", "+--" e "+-.", nell’ordine.
    public static StringSList btrRange(String btr, int n) {
        StringSList list = new StringSList();  
        
        for (int i=1; i<=n; i=i+1) {
            list = list.cons(btr);
            btr = btrSucc(btr);
        }
         
        return list.reverse();
    }
    
    
    } // class Btr_Rep 
