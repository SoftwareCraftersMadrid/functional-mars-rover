(ns mars-clojure.core)

(def initial-rover {:x 0 :y 0 :facing :north} )

(defn- warp-if-needed [position]
  (case position
    -1 100
    101 0
    position))

(defn- move
  [rover axis movement-function]
  (assoc rover axis (->> (axis rover)
                         movement-function
                         warp-if-needed)
    ))

(defn- increment
  [rover axis]
  (move rover axis inc)
  )

(defn- decrement
  [rover axis]
  (move rover axis dec)
  )

(defmulti execute-command (fn [rover _] (:facing rover)))

(defmethod execute-command :north
  [rover command]
  (case command
    \f (increment rover :y)
    \b (decrement rover :y)
    \r (assoc rover :facing :east)
    \l (assoc rover :facing :west)
    )
  )

(defmethod execute-command :east
  [rover command]
  (case command
    \f (increment rover :x)
    \b (decrement rover :x)
    \r (assoc rover :facing :south)
    \l (assoc rover :facing :north)
    )
  )

(defmethod execute-command :west
  [rover command]
  (case command
    \f (decrement rover :x)
    \b (increment rover :x)
    \r (assoc rover :facing :north)
    \l (assoc rover :facing :south)
    )
  )

(defmethod execute-command :south
  [rover command]
  (case command
    \f (decrement rover :y)
    \b (increment rover :y)
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
