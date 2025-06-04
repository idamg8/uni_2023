;; The first three lines of this file were inserted by DrRacket. They record metadata
;; about the language level of this file in a form that our tools can easily process.
#reader(lib "htdp-intermediate-lambda-reader.ss" "lang")((modname manhattan) (read-case-sensitive #t) (teachpacks ()) (htdp-settings #(#t constructor repeating-decimal #f #t none #f () #f)))
(define manhattan-2d
  (lambda (i j)
    (if (or (= i 0) (= j 0))
         1
         (+ (manhattan-2d i (- j 1)) (manhattan-2d (- i 1) j))
         )
      ))

(define manhattan-3d
  (lambda (i j k)
    (cond ((= i 0)
          (manhattan-2d j k))
          ((= j 0)
           (manhattan-2d i k))
          ((= k 0)
           (manhattan-2d i j))
          (else
           (+ (manhattan-3d (- i 1) j k) (manhattan-3d i (- j 1) k) (manhattan-3d i j (- k 1))))
          )
    ))

