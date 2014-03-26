(ns mars-clojure.core)

(def inital-rover {:x 0} )

(defn move-rover
  [rover commands]
  (assoc rover
    :x (count commands))
  )
