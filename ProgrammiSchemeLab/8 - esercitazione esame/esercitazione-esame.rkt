;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname esercitazione-esame) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks")) #f)))
; ESERCIZIO 1
; procedura che restituisce stringa di lunghezza minore tra u e v con le lettere in comune e asterischi nelle posizioni di lettere diverse
(define match
  (lambda (u v)
    (if (or (string=? u "") (string=? v ""))
      ""
      (let ((uh (string-ref u 0)) (vh (string-ref v 0)) (s (match (substring u 1) (substring v 1))))
        (if (char=? uh vh)
          (string-append (string uh) s)  ; se i primi due caratteri sono uguali, inserisco quel carattere e procedo ricorsivamente
          (string-append "*" s)          ; se i caratteri sono diversi inserisco un asterisco e procedo ricorsivamente
        )
      )
    )
  )
)

; ESERCIZIO 2

(define offset (char->integer #\0))

(define last-digit
  (lambda (base)
    (integer->char (+ (- base 1) offset))
  )
)

(define next-digit
  (lambda (dgt)
    (string (integer->char (+ (char->integer dgt) 1)))
  )
)

(define increment
  (lambda (num base)  ; 2 <= base <= 10
    (let ((digits (string-length num)))
      (if (= digits 0)
        "1"  ; se la stringa è lunga 0 il successivo è 1
        (let ((dgt (string-ref num (- digits 1))))
          (if (char=? dgt (last-digit base))  ; se l'ultimo carattere è l'ultimo della base
            (string-append (increment (substring num 0 (- digits 1)) base) "0")  ; 
            (string-append (substring num 0 (- digits 1)) (next-digit dgt))
          )
        )
      )
    )
  )
)

; ESERCIZIO 3

(define lcs      ; valore: lista di terne
  (lambda (u v)  ; u, v: stringhe
    (lcs-rec 1 u 1 v)
  )
)

(define lcs-rec
  (lambda (i u j v)
    (cond
      ((or (string=? u "") (string=? v ""))
       null)
      ((char=? (string-ref u 0) (string-ref v 0))
       (cons (list i j (substring u 0 1)) (lcs-rec (+ i 1) (substring u 1) (+ j 1) (substring v 1))))
      (else
       (better (lcs-rec (+ i 1) (substring u 1) j v) (lcs-rec i u (+ j 1) (substring v 1))))
    )
  )
)

(define better
  (lambda (x y)
    (if (< (length x) (length y)) y x)
  )
)

; ESERCIZIO 4

(define cyclic-string       ; val: string
  (lambda (pattern length)  ; pattern: string, length: integer (>= 0)
    (if (= length 0)
      ""
      (string-append (cyclic-string pattern (- length 1)) (string (string-ref pattern (remainder (- length 1) (string-length pattern)))))  ; decremento length di 1 perché gli indici per string-ref partono da 0
    )
  )
)

; ESERCIZIO 5

(define av     ; val: list
  (lambda (l)  ; l: non-empty list
    (cond
      ((= (length l) 1) null)
      ((< (+ (car l) (car (cdr l))) 0) (cons -1 (av (cdr l))))  ; se la somma di primo e secondo elemento è minore di 0, aggiungi -1 in testa alla lista
      ((= (+ (car l) (car (cdr l))) 0) (cons 0 (av (cdr l))))   ; se la somma di primo e secondo elemento è uguale a 0, aggiungi 0 in testa alla lista
      (else (cons 1 (av (cdr l))))                              ; se la somma di primo e secondo elemento è maggiore di 0, aggiungi 1 in testa alla lista
    )
  )
)

; ESERCIZIO 6

(define shared   ; val: ordered list
  (lambda (u v)  ; u: ordered list, v: ordered list
    (cond
      ((or (= (length u) 0) (= (length v) 0))
       null)
      ((= (list-ref u 0) (list-ref v 0))
       (cons (list-ref u 0) (shared (cdr u) (cdr v))))
      (else
       (betterList (shared (cdr u) v) (shared u (cdr v))))
      )
    )
  )

(define betterList   ; ritorna la lista più lunga
  (lambda (u v)      ; u, v: liste
    (if (> (length u) (length v))
        u
        v)
    )
  )


; ESERCIZIO 7

(define parity-check-failures  ; val: list
  (lambda (l)                  ; l: list of binary strings
    (parity-check-failures-rec l 0)
  )
)

(define parity-check-failures-rec  ; val: list
  (lambda (l i)                    ; l: list of binary strings, i: integer (>= 0)
    (cond
      ((= (length l) 0)
       null)  ; se la lunghezza della lista è 0, ritorno null
      ((parity? (car l))  ; se il primo elemento della lista supera il check di parità, proseguo al prossimo senza tener conto del suo indice
       (parity-check-failures-rec (cdr l) (+ i 1)))
      (else  ; se il primo elemento della lista NON supera il check di parità, aggiungo il suo indice alla lista e continua ricorsivamente
       (cons i (parity-check-failures-rec (cdr l) (+ i 1))))
    )
  )
)

(define parity?  ; val: boolean
  (lambda (s)    ; s: string
    (parity?-rec s 0)
  )
)

(define parity?-rec  ; val: boolean
  (lambda (s c)      ; s: string, c: integer (>= 0)
    (cond
      ((string=? s "")
       (if (= (remainder c 2) 0)  ; c funge da accumulatore di 1+1+...+1
           #t    ; se la divisione di c per 2 dà resto 0 significa che il numero di 1 è pari
           #f))  ; altrimenti è dispari e quindi non passa il parity check
      ((char=? (string-ref s 0) #\1)
       (parity?-rec (substring s 1) (+ c 1)))  ; se il primo carattere incontrato è 1, incremento l'accumulatore c
      (else (parity?-rec (substring s 1) c))  ; se il primo carattere incontrato è diverso da 1, proseguo ricorsivamente senza incrementare l'accumulatore
    )
  )
)

; ESERCIZIO 9

; Funzione principale che rimuove duplicati da una lista di stringhe
(define clean-up
  (lambda (u)
    (remove-duplicates u empty))                                         ; Inizia con una lista vuota di elementi visti
  )

; Funzione per rimuovere duplicati da una lista
(define remove-duplicates
  (lambda (lst seen)
    (cond
      ((empty? lst) empty)                                              ; Se la lista è vuota, restituisci una lista vuota
      ((belong (first lst) seen) (remove-duplicates (rest lst) seen))   ; Se l'elemento corrente è già stato visto, passa all'elemento successivo
      (else                                                             ; Altrimenti, aggiungi l'elemento corrente alla lista risultante e continua
       (cons (first lst) (remove-duplicates (rest lst) (cons (first lst) seen)))
       ))
    ))  


(define belong
  (lambda (item lst)
    (cond
      ((empty? lst) false)
      ((equal? item (first lst)) true)
      (else
       (belong item (rest lst))
      ))
    ))

; ESERCIZIO 10

; Funzione per trovare la stringa con il maggior numero di occorrenze contigue
(define longest-contiguous-repeat
  (lambda (s)
    (find-longest s (first s) 1 (first s) 1)
  ))

; Funzione per trovare la stringa con il maggior numero di occorrenze contigue in una lista
(define find-longest
  (lambda (lst current max-count max-str current-count)
    (cond
      
      ((empty? (rest lst)) ; Se la lista ha solo un elemento rimanente
       (if (> current-count max-count)
           current
           max-str))
      
      ((equal? (first lst) (first (rest lst))) ; Se l'elemento corrente è uguale al prossimo elemento
       (find-longest (rest lst) (first lst) max-count max-str (+ current-count 1)))
      
      (else ; Se l'elemento corrente non è uguale al prossimo elemento
       (if (> current-count max-count)
           (find-longest (rest lst) (first (rest lst)) current-count (first lst) 1)
           (find-longest (rest lst) (first (rest lst)) max-count max-str 1)))
    
      )
    ))



































; ESERCIZIO 6

(define r-val  ; val: integer
  (lambda (s)  ; s: string
    (let ((n (substring s 1)))
      (if (= (string-length n) 0)
        0
        (+ (* (string->number (substring n (- (string-length n) 1))) (expt 2 (- 0 (string-length n)))) (r-val (substring s 0 (- (string-length s) 1))))
      )
    )
  )
)

; ESERCIZIO 9

(define closest-pair  ; val: list
  (lambda (l)         ; l: ordered list (length >= 2)
    (closest-pair-rec l -1 null)
  )
)

(define closest-pair-rec  ; val: list
  (lambda (l diff pair)   ; l: ordered list (length >= 2), diff: integer, pair: list
    (cond
      ((= (length l) 1) pair)
      ((or (< diff 0) (> diff (- (car (cdr l)) (car l)))) (closest-pair-rec (cdr l) (- (car (cdr l)) (car l)) (list (car l) (car (cdr l)))))
      (else (closest-pair-rec (cdr l) diff pair))
    )
  )
)

; ESERCIZIO 10

(define sorted-char-list  ; val: list of chars
  (lambda (s)             ; s: string
    (sort (char-list-rec s null) less-than?)
  )
)

(define less-than?
  (lambda (a b)
    (if (> (char->integer a) (char->integer b))
      #f
      #t
    )
  )
)

(define char-list-rec  ; val: list of chars
  (lambda (s l)        ; s: string, l: list of chars
    (cond
      ((= (string-length s) 0) l)
      ((char-is-in (string-ref s 0) l) (char-list-rec (substring s 1) l))
      (else (char-list-rec (substring s 1) (cons (string-ref s 0) l)))
    )
  )
)

(define char-is-in  ; val: boolean
  (lambda (a l)     ; a: char, l: list of chars
    (cond
      ((null? l) #f)
      ((char=? (car l) a) #t)
      (else (char-is-in a (cdr l)))
    )
  )
)

