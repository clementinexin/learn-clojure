(ns learn-clojure-project.pb7con.channels.core
  (:require [clojure.core.async :as async :refer :all
             :exclude [map into reduce merge partition partition-by take]]))


(def c (chan))

(thread (println "Read:" (<!! c) "from c"))
(>!! c "Hello!")

(defn readall!! [ch]
  (loop [coll []]
    (if-let [x (<!! ch)]
      (recur (conj coll x))
      coll)))

(defn writeall!! [ch coll]
  (doseq [x coll]
    (>!! ch x))
  (close! ch))

(def ch (chan 11))

(writeall!! ch (range 0 11))

(readall!! ch)

(def ch (chan 13))

(onto-chan ch (range 0 13))

(<!! (async/into [] ch))

(def dc (chan (dropping-buffer 5)))

(onto-chan dc (range 0 10))

(<!! (async/into [] dc) )

(def sc (chan (sliding-buffer 5)))
(onto-chan sc (range 0 10))
(<!! (async/into [] sc))

(def ch (chan))

(go
  (let [x (<! ch)
        y (<! ch)]
    (println (+ x y))
    ))


(>!! ch 1)
(>!! ch 2)

(<!! (go (+ 3 4)))

(defn go-add [x y]
  (<!! (nth (iterate #(go (inc (<! %))) (go x)) y)))


