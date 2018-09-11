
(ns clujnio.nio
  "Wrapper around Java nio Path functionality."
  (:require [clujnio.files :refer [get-path]])
  (:import (java.nio.file Path Paths Files)))


(def user-dir (System/getProperty "user.dir"))

(def cwd (get-path user-dir))

(defn is-absolute [path]
  (.isAbsolute path))

(defn to-absolute-path [path]
  (.toAbsolutePath path))

(defn path-to-string [path]
  (.toString path))

(defn get-file-name [path]
  (.getFileName path))

(defn get-parent [path]
  (.getParent path))

(defn make-absolute [pathstring]
  (-> pathstring
      get-path
      to-absolute-path
      path-to-string))
      
(defn parse-path
  "Breaks up the path into segments."
  ([root path]
   (let [empty-array (into-array String [])
         java-path (Paths/get path empty-array)
         parent (get-parent java-path)
         filename (get-file-name java-path)]
     (cond
       (is-absolute java-path)
       [parent filename]

       (nil? parent)
       [(get-parent (to-absolute-path java-path))
        filename]

       :otherwise
       [parent filename])))
  ([path]
   (parse-path user-dir path)))
