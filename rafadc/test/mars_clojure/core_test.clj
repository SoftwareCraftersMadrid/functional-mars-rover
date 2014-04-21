(ns mars-clojure.core-test
  (:require [clojure.test :refer :all]
            [mars-clojure.core :refer :all]))

(def rover-at-top
  (assoc initial-rover :y 100))

(deftest rover-movement
  (testing "moving forward"
    (is (= 1 (:y (move-rover initial-rover "f" '() )))))
    (is (= 2 (:y (move-rover initial-rover "ff" '() ))))

  (testing "moving backwards"
    (is (= 0 (:y (move-rover {:x 0 :y 1 :facing :north} "b" '() )))))

  (testing "rotation"
    (is (= :east (:facing (move-rover initial-rover "r" '() ))))
    (is (= :west (:facing (move-rover initial-rover "l" '() ))))
    (is (= :south (:facing (move-rover initial-rover "rr" '() ))))
    (is (= :south (:facing (move-rover initial-rover "ll" '() ))))
    (is (= :north (:facing (move-rover initial-rover "llll" '() ))))
  )

  (testing "movement facing east"
    (is (= 1 (:x (move-rover initial-rover "rf" '() )))))

  (testing "movement facing west"
    (is (= 11 (:x (move-rover {:x 10 :y 5 :facing :west} "fbb" '() )))))

  (testing "movement facing south"
    (is (= 1 (:y (move-rover initial-rover "llb" '() )))))

  (testing "planet is round"
    (is (= 100 (:y (move-rover initial-rover "b" '() ))))
    (is (= 0 (:y (move-rover rover-at-top "f" '() ))))
    (is (= 100 (:x (move-rover {:x 0 :y 34 :facing :west} "f" '() ))))
    (is (= 0 (:x (move-rover {:x 100 :y 34 :facing :east} "f" '() ))))
    )

  (testing "collision"
    (is (= 0 (:y (move-rover initial-rover "f" '({:x 0 :y 1})))))
    (is (= 0 (:y (move-rover initial-rover "b" '({:x 0 :y 100})))))
    (is (= 1 (:y (move-rover initial-rover "bf" '({:x 0 :y 100})))))
    )
)
