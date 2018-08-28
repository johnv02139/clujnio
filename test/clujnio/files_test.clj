(ns clujnio.files-test
  (:require [clojure.test :refer :all]
            [clujnio.core :refer :all]))

(deftest jvm-dir-defined
  (testing "make sure JVM dir is defined"
    (is (not (nil? jvm-dir)))))
