;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname hanoi-def) (read-case-sensitive #t) (teachpacks ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks"))) (htdp-settings #(#t constructor repeating-decimal #f #t none #f ((lib "drawings.ss" "installed-teachpacks") (lib "hanoi.ss" "installed-teachpacks")) #f)))
; hanoi-moves: procedura che restituisce la lista di tutte le mosse (con numero dischi n inserito dall'utente)
; ogni mossa è indicata come '(numero_asta_di_partenza numero_asta_di_arrivo) es. '(1 3)

(define hanoi-moves  ; val: lista di coppie
  (lambda (n)        ; n > 0 intero
    (hanoi-rec n 1 2 3)
    ))

(define hanoi-rec    ; val: lista di coppie
  (lambda (n s d t)  ; n intero, s, d, t: posizioni
    (if (= n 1)
        (list (list s d))  ; nel caso in cui mi rimane solo un disco, posso spostarlo direttamente sull'attuale asta di destinazione 
        (let ((m1 (hanoi-rec (- n 1) s t d))  ; nella prima metà gli spostamenti avvengono da sorgente a transito, per cui i primi 2 parametri della chiamata sono proprio s e t
              (m2 (hanoi-rec (- n 1) t d s))  ; nella seconda metà gli spostamenti avvengono da transito a destinazione, per cui i primi 2 parametri della chiamata sono t e d
              )
          (append m1 (cons (list s d) m2))  ; (list s d) rappresenta la mossa centrale (sposto disco più grande da sorgente a destinazione)
          ))                                ; m1: mosse prima della metà, m2: mosse dopo la metà (in cui le aste si invertono)
    ))

; hanoi-disks: procedura che, inserita la k-esima mossa, riporta la situazione dei dischi sulle astine
; [quanti dischi su ogni astina, es. '((1 3) (3 0) (2 0))]
(define hanoi-disks  ; lista di tre liste (terna di coppie)
  (lambda (n k)      ; n: intero > 0, k: intero, numero mossa
    (hanoi-disks-rec n k 1 2 3 0 0 0)  
    ))

; s= sorgente, d= destinazione, t= transito; ns= num dischi su sorgente, nd= num dischi su destinazione, nt= num dischi su transito
; k= numero mossa, 0 <= k <= (2^n)-1; mossa centrale: 2^(n-1)
; n= numero dischi totali, n > 0

(define hanoi-disks-rec         ; val: lista di liste (terna di coppie)
  (lambda (n k s d t ns nd nt)  ; n: intero, numero dischi; k: intero, numero mossa; s d t: interi, astine; ns nd nt: interi
    
    (cond ((= n 0)                                      ; se ci sono 0 dischi rimasti da muovere
           (list (list s ns) (list d nd) (list t nt)))  ; posso passare a creare la lista con numeri di aste associati al numero di dischi
          
          ((< k (expt 2 (- n 1)))                             ; se k (la mossa attuale) si trova prima della metà delle mosse:
           (hanoi-disks-rec (- n 1) k s t d (+ ns 1) nt nd))  ; posso passare a lavorare al sottoproblema con n-1 dischi
                                                              ; sapendo che nella prima metà il disco più grande (diametro n) si trova in asta sorgente (qualunque essa sia al momento)
          
          (else                                                                    ; se invece k si trova in corrispondenza di o dopo la metà:
           (hanoi-disks-rec (- n 1) (- k (expt 2 (- n 1))) t d s nt (+ nd 1) ns))  ; tolgo da k il numero di mosse già avvenute prima della metà per lavorare solo sulla seconda
          )                                                                        ; e so che se mi trovo nella seconda metà, il disco più grande del sottoproblema è stato spostato in asta di destinazione
                                                                                   ; inoltre devo invertire l'ordine delle aste, perché nella 2^ metà i dischi partono dal tranisto e finiscono in destinazione...
                                                                                   ; ...utilizzando l'asta sorgente come transito
    ))

; procedura che restituisce rappresentazione grafica di hanoi-disks
(define hanoi-picture  ; val: picture
  (lambda (n k)        ; n: integer (> 0), k: integer (0 <= k <= 2^n-1)
    (hanoi-picture-rec n k 1 2 3 0 0 0 (towers-background n) n)  ; towers-background crea l'immagine delle torri correttamente dimensionata
                                                                 ; passo anche il parametro n che dovrà rimanere fisso, in modo tale che la gui sappia sempre quanti dischi ho in totale
  ))

(define hanoi-picture-rec
  (lambda (n k s d t ns nd nt picture tot)
    (cond
      ((= n 0)
       picture)  ; se ho 0 dischi mostro semplicemente l'immagine sneza alcuna modifica
      
      ((< k (expt 2 (- n 1)))  ; se mi trovo nella prima metà
       (hanoi-picture-rec (- n 1) k s t d (+ ns 1) nt nd (above (disk-image n tot s ns) picture) tot))  ; posiziono il disco più grande nell'asta sorgente (sopra al numero di dischi già presenti)
      
      (else  ; se mi trovo nella seconda metà
       (hanoi-picture-rec (- n 1) (- k (expt 2 (- n 1))) t d s nt (+ nd 1) ns (above (disk-image n tot d nd) picture) tot)) ; posiziono il disco più grande nell'asta destinazione
    )
  ))