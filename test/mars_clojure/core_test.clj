(ns mars-clojure.core-test
  (:require [clojure.test :refer :all]
            [mars-clojure.core :refer :all]))

(def inital-rover {:x 0 :y 0 :facing :north} )


(deftest rover-movement
  (testing "moving forward"
    (is (= 1 (:y (move-rover inital-rover "f")))))
    (is (= 2 (:y (move-rover inital-rover "ff"))))

  (testing "moving backwards"
    (is (= 0 (:y (move-rover {:x 0 :y 1 :facing :north} "b")))))

  (testing "rotation"
    (is (= :east (:facing (move-rover inital-rover "r"))))
    (is (= :west (:facing (move-rover inital-rover "l"))))
    (is (= :south (:facing (move-rover inital-rover "rr"))))
    (is (= :south (:facing (move-rover inital-rover "ll"))))
    (is (= :north (:facing (move-rover inital-rover "llll"))))
  )

  (testing "movement facing east"
    (is (= 1 (:x (move-rover inital-rover "rf")))))

  (testing "movement facing west"
    (is (= -1 (:x (move-rover inital-rover "lf")))))

  (testing "movement facing south"
    (is (= 1 (:y (move-rover inital-rover "llb")))))
)
