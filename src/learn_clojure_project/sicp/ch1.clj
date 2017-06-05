(ns learn-clojure-project.sicp.ch1)

(/ (+ 5 4 (- 2 (- 3 (+ 6 (/ 4 5)))))
   (* 3 (- 6 2) (- 2 7)))

(defn sum-of-square [x y]
 (+ (* x x) (* y y)))

(sum-of-square 3 4)

(if (= (count "four") 4)
  :predicate-true
  :predicate-false)

(def x 10)
(cond (> x 0) x
      (< x 0) (- x)
      (= x 0) 0)

(cond (> x 0) "Positive"
      :else "Negetive")

;;;http://www.sicpdistilled.com/section/1.1.7/
(defn abs [x]
  (max x (- x)))

(defn square [x]
  (* x x))

(defn close? [presume x]
  (< (abs (- (square presume) x) ) 0.001))

(defn average [x y]
  (/ (+ x y) 2))
(defn improve [presume x]
  (average presume (/ x presume)))

(defn sqrt-iter [presume x]
  (if (close? presume x)
    presume
    (sqrt-iter (improve presume x) x)
  ))

(defn sqrt [x]
  (sqrt-iter 1.0 x))

(sqrt 3)

;;;https://github.com/gregsexton/SICP-Clojure/blob/master/src/sicp/ch1.clj
(defn sqrt [n]
  (letfn [(good-enough? [old-guess new-guess]
            (< (/ (Math/abs (- old-guess new-guess))
                  old-guess)
               0.001))
          (average [x y] (/ (+ x y) 2))
          (improve [guess]
            (average guess (/ n guess)))
          (help [guess]
            (let [new (improve guess)]
              (if (good-enough? guess new) new
                                           (help new))))]
    (help 1.0)))
(sqrt 3)

