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

(println *ns*)

(defn plus1 [x] (+ 1 x))
(map plus1   [1 2 3])

(def guys-whole-name ["Guy" "Lewis" "Steele"])
(str (nth guys-whole-name 2) ","
     (nth guys-whole-name 0) " "
     (nth guys-whole-name 1))

(let [[f-name m-name l-name] guys-whole-name]
  (str l-name "," f-name " " m-name))

(let [[a b c & more] (range 10)]
  (println "a b c are: " a b c)
  (println "more is:" more))

(let [range-vec (vec (range 10))
      [a b c & more :as all] range-vec]
  (println "a b c are: " a b c)
  (println "more is:" more)
  (println "all is:" all))

(def guys-whole-name-map {:f-name "Guy" :m-name "Lewis" :l-name "Steele"})

(let [{f-name :f-name,m-name :m-name,l-name :l-name} guys-whole-name-map]
  (str l-name "," f-name " " m-name))

(let [{:keys [f-name m-name l-name]} guys-whole-name-map]
  (str l-name "," f-name " " m-name))

(let [{f-name :f-name :as whole-name} guys-whole-name-map]
  whole-name)

(let [{:keys [title f-name m-name l-name],:or {title "Mr."}} guys-whole-name-map]
  (println title f-name m-name l-name))

(let [{first-key 0,last-key 1} [:zero :one ]]
  [first-key last-key])

(defn print-last-name [{:keys [l-name]}]
  l-name)
(print-last-name guys-whole-name-map)


(for [x (range 2) y (range 2)] [x y (bit-xor x y)])


(defn xors [max-x max-y]
  (for [x (range max-x) y (range max-y)] [x y (bit-xor x y)]))
(xors 2 2)




(for [method (seq (.getMethods java.awt.Frame))
      :let [method-name (.getName method)]
      :when (re-find #"Vis" method-name)]
  method-name)

(.isVisible frame)
(def frame (java.awt.Frame.))
(.setVisible frame true)

(.setSize frame (java.awt.Dimension. 200 200))

(def gfx (.getGraphics frame))

(.fillRect gfx 100 100 50 75)
(.setColor gfx (java.awt.Color. 255 128 0))


(doseq [[x y xor] (xors 200 200)]
  (.setColor gfx (java.awt.Color xor xor xor))
  (.fillRect gfx x y 1 1))
















