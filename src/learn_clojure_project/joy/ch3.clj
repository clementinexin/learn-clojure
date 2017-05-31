(ns learn-clojure-project.joy.ch3)

(if true :truthy :falsey)
(if [] :truthy :falsey)
(if nil :truthy :falsey)
(if false :truthy :falsey)

(def eveil-false (Boolean. "false"))

eveil-false

(= false eveil-false)
(if eveil-false :truthy :falsey)

(if (Boolean/valueOf "false") :truthy :falsey)

(when (nil? nil) "Actually it is nil,not false")

(seq  [1 2 3])

(seq [])

(defn print-seq [s]
  (when (seq s)
    (prn (first s))
    (recur (rest s))))

(print-seq [1 2 3 ])
(print-seq [])

