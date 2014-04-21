(ns mars-clojure.core)

(def initial-rover {:x 0 :y 0 :facing :north} )

(defn- warp-if-needed [position]
  (case position
    -1 100
    101 0
    position))


(defn- collision-checker-for
  [rover]
  (fn [obstacle]
    (and (= (:x rover) (:x obstacle))
       (= (:y rover) (:y obstacle)))))

(defn- collide
  [rover obstacles]
  (first (filter (collision-checker-for rover) obstacles)))

(defn- calculate-destination
  [rover axis movement-function]
  (assoc rover axis (->> (axis rover)
                         movement-function
                         warp-if-needed)
  ))

(defn- move
  [rover axis movement-function obstacles]
  (let [destination (calculate-destination rover axis movement-function)]
  (if (collide destination obstacles)
    rover
    destination
    )))

(defn- increment
  [rover axis obstacles]
  (move rover axis inc obstacles)
  )

(defn- decrement
  [rover axis obstacles]
  (move rover axis dec obstacles)
  )

(defmulti execute-command (fn [rover _ _] (:facing rover)))

(defmethod execute-command :north
  [rover command obstacles]
  (case command
    \f (increment rover :y obstacles)
    \b (decrement rover :y obstacles)
    \r (assoc rover :facing :east)
    \l (assoc rover :facing :west)
    )
  )

(defmethod execute-command :east
  [rover command obstacles]
  (case command
    \f (increment rover :x obstacles)
    \b (decrement rover :x obstacles)
    \r (assoc rover :facing :south)
    \l (assoc rover :facing :north)
    )
  )

(defmethod execute-command :west
  [rover command obstacles]
  (case command
    \f (decrement rover :x obstacles)
    \b (increment rover :x obstacles)
    \r (assoc rover :facing :north)
    \l (assoc rover :facing :south)
    )
  )

(defmethod execute-command :south
  [rover command obstacles]
  (case command
    \f (decrement rover :y obstacles)
    \b (increment rover :y obstacles)
    \r (assoc rover :facing :west)
    \l (assoc rover :facing :east)
    )
  )

(defn move-rover
  [rover commands obstacles]
  (reduce (fn
            [partial-rover command]
            (execute-command partial-rover command obstacles)) rover commands)

  )
