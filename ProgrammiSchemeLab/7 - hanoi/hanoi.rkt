;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname hanoi) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks")) #f)))
; hanoi-moves: procedura che restituisce la lista di tutte le mosse (con numero dischi n inserito dall'utente)
; ogni mossa è indicata come '(numero_asta_di_partenza numero_asta_di_arrivo) es. '(1 3)

(define hanoi-moves  ; val: lista di coppie
  (lambda (n)        ; n > 0 intero
    (hanoi-rec n 1 2 3)
    ))

(define hanoi-rec    ; val: lista di coppie
  (lambda (n s d t)  ; n intero, s, d, t: posizioni
    (if (= n 1)
        (list (list s d))
        (let ((m1 (hanoi-rec (- n 1) s t d))
              (m2 (hanoi-rec (- n 1) t d s))
              )
          (append m1 (cons (list s d) m2))
          ))
    ))


; hanoi-disks: procedura che, inserita la k-esima mossa, riporta la situazione dei dischi sulle astine
; [quanti dischi su ogni astina, es. '((1 3) (3 0) (2 0))]

(define hanoi-disks  ; lista di tre liste (terna di coppie)
  (lambda (n k)      ; n: intero > 0, k: intero, numero mossa
    (hanoi-disks-rec-no n k 1 2 3 n 0 0)
    ))

; s= sorgente, d= destinazione, t= transito; a= num dischi su sorgente, b= num dischi su destinazione, c= num dischi su transito
; k= numero mossa, 0 <= k <= (2^n)-1
; n= numero dischi totali, n > 0

(define hanoi-disks-rec-no      ; val: lista di liste (terna di coppie)
  (lambda (n k s d t ns nd nt)  ; n: intero, numero dischi; k: intero, numero mossa; s d t: interi, astine; ns nd nt: interi
    (cond ((= k 0)
           (list (list s n) (list d nd) (list t nt)))
          ((= k (expt 2 (- n 1)))
           (list (list s 0) (list d 1) (list t (- n 1))))
          ((< k (expt 2 (- n 1)))
           (hanoi-disks-rec-no))
          (else
           (hanoi-disks-rec-no))
        )
    ))




;(define hanoi-disks-rec         ; val: lista di liste (terna di coppie)
;  (lambda (n k s d t ns nd nt)  ; n: intero, numero dischi; k: intero, numero mossa; s d t: interi, astine; ns nd nt: interi
;    (cond ((= n 1)
;           (if (= k 0)
;               (list (list s n) (list d nd) (list t nt))
;               (list (list s 0) (list d n) (list t 0))
;               ))
;          ((= k (expt 2 (- n 1)))
;           (list (list s 0) (list d 1) (list t (- n 1))))
;          ((< k (expt 2 (- n 1)))
;           (hanoi-disks-rec))
;          (else
;           (hanoi-disks-rec))
;          )
;    ))






