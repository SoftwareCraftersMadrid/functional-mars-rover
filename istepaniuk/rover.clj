(ns rover.core
  (:gen-class))

(def ^:private planet-size
  {
    :around-x 100
    :around-y 100
    }
  )

(def ^:private forward-displacements
  {
    :north {:delta-x 0  :delta-y 1 }
    :east  {:delta-x 1  :delta-y 0 }
    :south {:delta-x 0  :delta-y -1}
    :west  {:delta-x -1 :delta-y 0 }
    }
  )

(defn- calculate-rotation[command]
  (get { "R" 1 "L" -1} command 0)
  )

(defn- calculate-new-bearing [old-bearing command]
  (let [all-bearings [:north :east :south :west]]
    (let [new-index (+ (calculate-rotation command) (.indexOf all-bearings old-bearing))]
      (get all-bearings (mod new-index (count all-bearings))
           )
      )
    )
  )

(defn- backward-displacements [bearing]
  (let [{delta-x :delta-x delta-y :delta-y} (forward-displacements bearing)]
    {:delta-x (- delta-x) :delta-y (- delta-y)})
  )

(defn- calculate-displacement [bearing command]
  (get {
         "F" (forward-displacements bearing)
         "B" (backward-displacements bearing)
         }
       command {:delta-x 0 :delta-y 0}
       )
  )

(defn- is-coordinate-an-obstacle? [coordinate obstacles]
  (some #(= % coordinate) obstacles)
  )

(defn- calculate-new-coordinates [position command]
  (let [{x :x y :y bearing :bearing} position]
    (let [{delta-x :delta-x delta-y :delta-y} (calculate-displacement bearing command)]
      {:x (mod (+ x delta-x) (planet-size :around-x))
       :y (mod (+ y delta-y) (planet-size :around-y))}
      )
    )
  )

(defn move-rover [initial-position commands obstacles]
  (letfn [(do-command [position command]
    (let [{x :x y :y bearing :bearing} position]
      (let [{new-x :x new-y :y} (calculate-new-coordinates position command)]
        (if (is-coordinate-an-obstacle? {:x new-x :y new-y} obstacles)
          position
          {:x new-x :y new-y :bearing (calculate-new-bearing bearing command)}
          )
        )
      )
    )]
    (reduce do-command initial-position (map str commands)
      )
    )
  )

(defn -main
  (println "Hey!"))