(ns mars-clojure.core)

(def initial-rover {:x 0 :y 0 :facing :north} )

(defn- warp-if-needed [position]
  (case position
    -1 100
    101 0
    position))

(defmulti execute-command (fn [rover _] (:facing rover)))

(defmethod execute-command :north
  [rover command]
  (case command
    \f (assoc rover :y (warp-if-needed (inc (:y rover))))
    \b (assoc rover :y (warp-if-needed (dec (:y rover))))
    \r (assoc rover :facing :east)
    \l (assoc rover :facing :west)
    )
  )

(defmethod execute-command :east
  [rover command]
  (case command
    \f (assoc rover :x (warp-if-needed (inc (:x rover))))
    \b (assoc rover :x (warp-if-needed (dec (:x rover))))
    \r (assoc rover :facing :south)
    \l (assoc rover :facing :north)
    )
  )

(defmethod execute-command :west
  [rover command]
  (case command
    \f (assoc rover :x (warp-if-needed (dec (:x rover))))
    \b (assoc rover :x (warp-if-needed (inc (:x rover))))
    \r (assoc rover :facing :north)
    \l (assoc rover :facing :south)
    )
  )

(defmethod execute-command :south
  [rover command]
  (case command
    \f (assoc rover :y (warp-if-needed (dec (:y rover))))
    \b (assoc rover :y (warp-if-needed (inc (:y rover))))
    \r (assoc rover :facing :west)
    \l (assoc rover :facing :east)
    )
  )

(defn move-rover
  [rover commands]
  (reduce (fn
            [partial-rover command]
            (execute-command partial-rover command)) rover commands)

  )
