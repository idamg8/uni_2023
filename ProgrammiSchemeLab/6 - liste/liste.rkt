;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname liste) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks")) #f)))

; procedura che restituisce se l'elemento n fa parte della lista l
(define belong?      ; val: true/false
  (lambda (l n)      ; l: lista, n: intero
    (cond ((null? l)  ; se la lista è vuota, ovviamente il risultato è false
           false)
          ((= (car l) n)  ; se n è uguale al primo elemento di l, il risultato è true
            true)
          (else
           (belong? (cdr l) n))  ; altrimenti procedo ricorsivamente togliendo il primo elemento dalla lista
        )
      ))

; procedura che restituisce la posizione di un dato elemento all'interno della lista
(define position           ; val: intero
  (lambda (l n)            ; l: lista, n: intero (facente parte della lista)
    (if (> (length l) 0)   ; procedo con la valutazione solo se la lunghezza dela lista è maggiore di 0
        (if (= n (car l))  ; se n è uguale al car della ista significa che si trova in prima posizione  
            0
            (+ 1 (position (cdr l) n))  ; altrimenti procedo ricorsivamente con il cdr
            )
        0  ; se la lunghezza della lista è 0, la posizione ritornata sarà 0
        )
    ))

(define position-compact
       (lambda (l n)
         (if (= n (car l))
             0
             (+ 1 (position (cdr l) n))
             )
         ))

; procedura che restituisce la lista ordinata e senza ripetizioni che contine n e tutti gli elementi di l
(define sorted-ins       ; val: lista
  (lambda (l n)          ; l: lista, n: intero (non facente parte della lista)
    (if (belong? l n)    ; se n fa già parte della lista, ritorno la lista stessa
        l
        (if (= (length l) 0)  ; se la lista è vuota, posso semplicemente aggiungere n senza preoccuparmi di posizione o ripetizioni
            (cons n null)
            (if (> n (car l))  ; se n è maggiore del primo elemento della lista...
                (cons (car l) (sorted-ins (cdr l) n))  ; ...mantengo invariato il primo elemento e procedo ricorsivamente con il cdr
                (cons n l)))  ; altrimenti inserisco prima n e poi il resto degli elementi
        )
    ))

; procedura che restituisce la lista ordinata e senza ripetizioni che contine tutti gli elementi di l
(define sorted-list      ; val: lista di interi ordinati
  (lambda (l)            ; l: lista di interi
    (if (= (length l) 0)  ; se la lista è vuota, la sua versione ordinata è semplicemente una lista vuota
        '()
        (sorted-ins (sorted-list (cdr l)) (car l))  ; altrimenti utilizzo sorted-ins per inserire nella corretta posizione il primo elemento nella lista ordinata ricorsivamente
        )
    ))
