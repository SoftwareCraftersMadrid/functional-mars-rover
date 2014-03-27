(ns mars-clojure.core-test
  (:require [clojure.test :refer :all]
            [mars-clojure.core :refer :all]))

(def inital-rover {:x 0 :y 0 :facing :north} )


(deftest rover-movement
  (testing "When Rover moves forward facing north it advances one square"
    (is (= 1 (:y (move-rover inital-rover "f")))))

  (testing "When Rover moves forward twice facing north it advances two squares"
    (is (= 2 (:y (move-rover inital-rover "ff")))))

  (testing "moves backwards also"
    (is (= 0 (:y (move-rover {:x 0 :y 1 :facing :north} "b")))))

  (testing "rotates right"
    (is (= :east (:facing (move-rover inital-rover "r"))))
    (is (= :west (:facing (move-rover inital-rover "l"))))
  )
)
