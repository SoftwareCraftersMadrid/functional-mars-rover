(ns mars-clojure.core)

(def inital-rover {:y 0} )

(defn- execute-command
  [rover command]
  (case command
    'f' (assoc rover :y (inc (:y rover)))
    'b' (assoc rover :y (dec (:y rover)))
    )
  )

(defn move-rover
  [rover commands]
  (reduce (fn
            [rover command]
            (execute-command rover)) commands)

  )
