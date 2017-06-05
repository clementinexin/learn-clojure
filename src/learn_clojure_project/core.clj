(ns learn-clojure-project.core
  (:import (learn_clojure_project.core Compass)))

(defn foo
  "I don't do a whole lot."
  [x]
  (println x "Hello, World!"))

(defn process [list-of-emps]
  (reduce str (interpose ","
                         (map s/capitalize (filter #(< 1 (count %)) list-of-emps)))))
(defn process2 [list-of-emps]
  (->>
    list-of-emps
    (filter #(< 1 (count %)))
    (map s/capitalize)
    (interpose ",")
    (reduce str)
    )
  )

(every? number? [1 2 3 :four])
(some nil? [1 2 nil])

(not-every? odd? [7 9])

(not-any? number? [:one :two])

(def words ["one" "two" "three" "four"])
(filter (fn [word] (> (count  word) 4)) words)

(map (fn [x] (* x x)) [1 3 5 7 9])

(def colors ["red" "blue"])
(for [x colors] (str "I like" x))
(def toys ["car" "flight"])
(for [x colors  y toys] (str "I like " x " " y))

(defn small-word?[w] (< (count w) 4))
(for [x colors,y toys :when (small-word? y)] (str "I like " x " " y))


(reduce + [1 2 3])
(reduce * (1 2 3))
(sort [3 2 1])


(defn abs [x] (if (< x 0) (- x) x))
(sort-by abs [-4 -1 3])


(range 1 10)
(range 1 10 4)
(range 10)

(take 3 (repeat "Apple"))
(take 4 (cycle ["Apple" "Banana" "Orange"]))

(take 4 (drop 2 (cycle ["Apple" "Banana" "Orange"])))

(->> [:apple :banana :orange] (cycle) (drop 2) (take 10))


(take 5 (interpose :and (cycle [:apple :banana :orange])))

(take 20 (interleave (cycle (range 2)) (cycle (range 3))))

(take 5 (iterate inc 1))
(take 5 (iterate dec 1))


(defn fab-pair[[a b]] [b (+ a b)])

(take 10 (map first (iterate fab-pair[1 1])))

(defn factorial[n] (apply * (take n (iterate inc 1))))
(factorial 10)

(defprotocol Compass
  (direction [c])
  (left [c])
  (right [c]))

(def directions [:north :east :south :west])
(defn turn [base amount] (rem (+ base amount) (count directions)))

(defrecord SimpleCompass [bearing] Compass

(direction [_] (directions bearing))

  (left [_] (SimpleCompass. (turn bearing 3)))
  (right [_] (SimpleCompass. (turn bearing 1)))

  (toString [this] (str "[" (direction this) "]"))
  )

(def c (SimpleCompass. 0))
(left c)
c

(:bearing c)

(defn unless [test body] (if (not test) body))

(unless true (println "True"))



(macroexpand ''something-we-do-not-expand)

(macroexpand '#(count %))


(fn mk-set [x y] #{x y})
((fn [x y] #{x y})  1 2)
((fn
   ([x] #{x})
   ([x y] #{x y}))
  1 2)

((fn
   ([x] #{x})
   ([x y] #{x y}))
  1 )


((fn arity2 [x y] [x y]) 1 2 3)
((fn arity2 [x y] #{x y}) 1 2 )
((fn artity2+ [x y & z] [x y z])  1 2 )
((fn artity2+ [x y & z] [x y z])  1 2 3 4 )
((fn artity2+ [x y & z] [x y z])  1 )

(def make-a-set
  (fn
    ([x] #{x})
    ([x y] #{x y})))

(make-a-set 1)
(make-a-set 1 2)

(defn make-a-set
  "Function takes either one or two args then returns a set of them"
  ([x] #{x})
  ([x y] #{x y})
  )

(def make-a-list #(list %))
(def make-a-list1 #(list %1))
(def make-a-list2 #(list %1 %2))
(def make-a-list3 #(list %1 %2 %3))
(def make-a-list3+ #(list %1 %2 %3 %&))

(make-a-list 1)
(make-a-list1 1)
(make-a-list3+ 1 2 3 4 5)

(def x 42)
(.start (Thread. #(println "Answer: " x)))


(do
  7
  (+ 4 3)
  3)

(let [r 5
      pi 3.141592653589723846264338327950288
      r-squared (* r r)]
  (print "radius is "  r)
  (* pi r-squared))

(defn print-down-form [x]
  (when (pos? x)
    (println x)
    (recur (dec x))))

(print-down-form 10)

(defn sum-down-form [sum x]
  (if (pos? x)
    (recur (+ sum x) (dec x))
    sum)
  )
(sum-down-form 0 10)
(defn sum-down-form2 [initial-x]
  (loop [sum 0,x initial-x]
    (if (pos? x)
      (recur (+ sum x) (dec x))
      sum)))

(sum-down-form2 10)

(defn absolute-value [x]
  (if (> x 0)
    (x)
    (- x)))
(absolute-value -1)
(absolute-value 1)

(fn [x] (recur x) (print x))

(def tean 1)
(print tean)
(quote tean)

(cons 1 [2 3])

(quote (cons 1 [2 3]))


(cons 1 (2 3))
(cons 1 (quote (2 3)))

(cons 1 '(2 3))


[1 (+ 2 3)]
'(1 (+ 2 3))

`(1 2 3)

`map
`Integer
`(map even? [1 2 3])

(map even? [1 2 3])

`is-a-quote

`(+ 10 (* 2 3))

`(+ 10 ~(* 2 3))

`(1 2 ~3)

`(1 2 3)

(let [x 2]
  `(1 ~x 3))

`(1 ~(2 3 ))

(let  [x '(2 3)]
  `(1 ~x))

(let [x '(2 3)] `(1 ~@x))

`potion#

`potion#

java.lang.String
java.util.regex/Matcher

(Math/abs -3)

(new java.util.HashMap {"One" 1})

(java.util.HashMap. {"Two" 2})


(.x (java.awt.Point. 10 10))

(.divide (java.math.BigDecimal. "20") 10M)

(let [origin (java.awt.Point. 10 10)]
  (set! (.x origin) 15)
  (str origin))

(.. (java.util.Date.) (toString) (endsWith "2017"))

(.. (java.util.Date.) toString (endsWith "2017"))

(doto (java.util.Properties.)
  (.put "one" 1)
  (.put "two" 2)
  (.put "three" 3))

(throw (Exception. "Exception occurs"))

(defn throw-catch [f]
  [(try
     (f)
     (catch ArithmeticException e "No Dividing By Zero")
     (catch Exception e (str (.getMessage e)))
     (finally (println "returning...")))])

(throw-catch #(/ 1 0))
(throw-catch #(/ 1 1))

(throw-catch #(throw (Exception. "ex")))

(ns joy.ch2)
(defn print-ns [] (str "the current ns is" *ns*))
(print-ns)
print-ns


(ns joy.chx)
(print-ns)



(joy.ch2/print-ns)


(ns joy.req
  (:require clojure.set))
(clojure.set/intersection #{1 2 3} #{2 3 4})

(ns joy.req-alias
  (:require [clojure.set :as s]))
(s/intersection #{1 2 3} #{ 2 3 4})


(ns joy.use-ex
  (:use [clojure.string :only [capitalize]]))
(map capitalize ["key"])


(ns joy.exclusion
  (:use [clojure.string :exclude [capitalize]] ))
(map capitalize ["key"])

(ns joy.yet-another
  (:refer joy.ch2) )
(print-ns)

(ns joy.yet-another
  (:refer joy.ch2 :rename {print-ns echo-ns}))
(echo-ns)

(ns joy.java
  (:import
    [java.util.HashMap]
    [java.util.concurrent.atomic AtomicLong]
    (java.util HashMap)))
(HashMap. {"One" 1})


(println *ns*)

(map (+ 1)   [1 2 3])