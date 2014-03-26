(ns mars-clojure.core-test
  (:require [clojure.test :refer :all]
            [mars-clojure.core :refer :all]))

(deftest rover-movement
  (testing "When Rover moves forward facing north it advances one square"
    (is (= {:x 1} (move-rover {:x 0} "f"))))

  (testing "When Rover moves forward twice facing north it advances two squares"
    (is (= {:x 2} (move-rover {:x 0} "ff"))))
  )
