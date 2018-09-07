(ns clujnio.files-test
  (:require [clojure.test :refer :all]
            [clujnio.files :refer :all])
  (:import (java.nio.file Path Paths)
           (java.io File)
           (java.net URI)))

(deftest as-path-accepts-nil
  (is (= nil (as-path nil))))

(deftest as-path-accepts-string
  (is (instance? Path (as-path "/etc/fstab"))))

(deftest as-path-accepts-file
  (is (instance? Path (as-path (File. "/etc/fstab")))))

(deftest as-path-accepts-path
  (is (instance? Path (as-path (Paths/get "/" (into-array String ["etc" "fstab"]))))))

(deftest as-path-accepts-uri
  (is (instance? Path (as-path (URI. "file:///etc/fstab")))))

(deftest path-accepts-single-arg
  (is (instance? Path (get-path "/etc"))))

(deftest path-accepts-multiple-args
  (is (instance? Path (get-path "/etc" "fstab"))))
