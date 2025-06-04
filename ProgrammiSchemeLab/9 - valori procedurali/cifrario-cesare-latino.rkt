;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname cifrario-cesare-latino) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks")) #f)))
; do un nome all'alfabeto latino
(define alfabeto "ABCDEFGHILMNOPQRSTVX")

; Procedura che costruisce la procedura che cifra una frase con il Cifrario di Cesare
(define cesare   ; val: procedura
  (lambda (key)  ; key: intero (0 <= key <= 19) indica di quanto ruotare i caratteri
    
    (lambda (str)  ; str: messsaggio (stringa) da crittare
      (if (= (string-length str) 0)  ; se la lunghezza del messaggio è 0
          ""                         ; la versione crittata sarà a sua volta una stringa vuota
          
          (string-append  ; altrimenti faccio uno string-append tra
           (string (rotate (string-ref str 0) key alfabeto))  ; il primo carattere ruotato (crittato) manualmente richiamando la procedura rotate
           ((cesare key) (substring str 1)))                  ; e il resto del messaggio (tolto il primo carattere) che viene gestito ricorsivamente
          )
      )
    
    ))

; Procedura che effettua la rotazione dei caratteri in base alla key data in input
(define rotate           ; val: char
  (lambda (char i alph)  ; char: char, i: integer (>= 0), alph: string
    (let ((c (+ (find-char char alph) i))  ; c rappresenta la posizione del carattere nell'alfabeto + l'incremento dato da key
          )
      (if (<= c 19)  ; significa che c non "esce dai limiti" del nostro alfabeto di 10 lettere (indici da 0 a 19)
          (string-ref alph c)  ; posso riportare direttamente il carattere della posizione corrispondente
          (string-ref alph (- c 20))  ; altrimenti riporto il carattere nella posizione decrementata di 20 (dimensione alfabeto)
          )
      )
    ))

; Procedura che trova il carattere all'interno del "dizionario" (stringa di lettere latine)
(define find-char   ; val: integer
  (lambda (c dict)  ; c: char, dict: string
    (if (char=? c (string-ref dict 0))
        0
        (+ 1 (find-char c (substring dict 1)))
        )
    ))

; procedura finale che definisce la crittazione di cesare con l'alfabeto latino
(define crittazione-cesare-lat
  (cesare 3)
  )



; PARTE 2 ________________________________________________________________________________________________

; definizione di funzione identità: ritorna l'elemento stesso
(define i
  (lambda (x)
    x))

; definizione di funzione costante zero
(define z
  (lambda (x)
    0))

; definizione di funzione costante 1
(define u
  (lambda (x)
    1))

; definzione di funzione che effettua l'incremento di 1
; la modifico rispetto a succ in modo tale che, come le altre (add/mul/pow) abbia due valori in input e possa essere utilizzata come funzione g
(define s2
  (lambda (u v)
    (+ v 1)))

; definizione dell'operatore funzionale H, tale che h = H( f, g )
(define H
  (lambda (caso-base operation)  ; caso-base: f, operation: g
    
    (lambda (m n)  ; parametri che verranno presi in input alla chiamata della funzione definita da H
      (if (= n 0)  ; se n = 0...
          (caso-base m)  ; ...applico solo la funzione di caso base a m
          (operation m ((H caso-base operation) m (- n 1)))  ; altrimenti applico l'operazione a m e al secondo parametro (dato da una nuova chiamata di H, ma con n decrementato)
      )
    )
    
  ))

; per definire le seguenti funzioni utilizzo il modello di H, passando come parametri le funzioni che serviranno come caso-base e operazione
(define add
  (H i s2))  ; caso-base: identità, operazione: funzione di incremento

(define mul
  (H z add))  ; caso-base: zero, operazione: somma

(define pow
  (H u mul))  ; caso-base: uno, operazione: moltiplicazione

; i valori m,n vengono passati dall'utente che chiama la funzione
(add 1536 7911)
(mul 543 28)
(pow 2 7)