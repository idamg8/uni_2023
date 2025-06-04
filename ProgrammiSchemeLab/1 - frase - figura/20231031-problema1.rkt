;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname 20231031-problema1) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define articolo    ; val: stringa, articolo concordato con oggetto
  (lambda (obj)     ; obj: stringa, oggetto regolare
      (cond
        ((char=? (string-ref obj (-(string-length obj) 1)) #\o) "il")
        ((char=? (string-ref obj (-(string-length obj) 1)) #\i) "i")
        ((char=? (string-ref obj (-(string-length obj) 1)) #\a) "la")
        ((char=? (string-ref obj (-(string-length obj) 1)) #\e) "le")
        )
    )
  )

(define declinazione    ; val: stringa, verbo declinato in base al soggetto
  (lambda (verbo subj)  ; verbo, subj: stringhe
      (if (string=? (substring verbo (-(string-length verbo) 3)) "are")
       (cond
         ((string=? (articolo subj) "il")
          (string-append (substring verbo 0 (-(string-length verbo) 3)) "a"))
         ((string=? (articolo subj) "la")
          (string-append (substring verbo 0 (-(string-length verbo) 3)) "a"))
         ((string=? (articolo subj) "i")
          (string-append (substring verbo 0 (-(string-length verbo) 3)) "ano"))
         ((string=? (articolo subj) "le")
          (string-append (substring verbo 0 (-(string-length verbo) 3)) "ano"))
           )
       (cond
         ((string=? (articolo subj) "il")
          (string-append (substring verbo 0 (-(string-length verbo) 3)) "e"))
         ((string=? (articolo subj) "la")
          (string-append (substring verbo 0 (-(string-length verbo) 3)) "e"))
         ((string=? (articolo subj) "i")
          (string-append (substring verbo 0 (-(string-length verbo) 3)) "ono"))
         ((string=? (articolo subj) "le")
          (string-append (substring verbo 0 (-(string-length verbo) 3)) "ono"))
           )
        )
    )
  )

(define frase              ; val: stringa, frase composta da soggetto, predicato, oggetto e relativi articoli
  (lambda (subj verbo obj) ; subj, verbo, obj: stringhe
    (string-append
     (articolo subj)
     " " subj
     " " (declinazione verbo subj)
     " " (articolo obj)
     " " obj
     )
    ))