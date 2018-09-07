
(ns clujnio.files
  "Wrapper around Java file functionality."
  (:require [clojure.string :refer [split replace] :rename {replace str-replace}])
  (:import (java.nio.file Path Paths Files)
           (java.io File)
           (java.net URI)))

;; Note, this file is not really implementing yet even the beginnings of what
;; this package is intended to be.  This is just some wrappers around Java
;; functionality, which provides Clojure code a simpler way to use the files.
;; But it doesn't provide any concept of a Clojure file type.


(defn- literalize-for-regex [s]
  (str-replace s "\\" "\\\\"))

(defn- fail [& rest]
  (apply printf rest)
  false)


(def file-separator (System/getProperty "file.separator"))
(def file-separator-for-regex (literalize-for-regex file-separator))
(def file-separator-inversion-for-regex (str \[ \^ file-separator-for-regex \]))




(def path-splitter (re-pattern file-separator-for-regex))

(defn split-path [pth]
  (split pth path-splitter))


(defn as-file [x]
  (if (instance? File x) x (File. x)))


(defprotocol FilePath
  "Implement this protocol if your type can be converted to a
  java.nio.file.Path object."
  (as-path [x]))

(extend-protocol FilePath
  nil
  (as-path [x] nil)

  String
  (as-path [x] (Paths/get x (into-array String [])))

  Path
  (as-path [x] x)

  File
  (as-path [x] (.toPath x))

  URI
  (as-path [x] (Paths/get x)))

(defn get-path
  "Creates a Path using the segments provided."
  [x & more]
  (let [more-array (into-array String more)]
    (Paths/get x more-array)))


(defn normalize-pathstring [s]
  (str-replace s  "/./" "/"))

(defn make-file
  ([^String pathstring]
   (File. pathstring))
  ([^File parent ^String name]
   (File. parent name)))

(defn fmkdirs [^File f]
  (.mkdirs f))

(defn file-exists [^File f]
  (.exists f))

(defn parent-file [^File f]
  (.getParentFile f))

(defn file-path [^File f]
  (.getPath f))

(defn file-name [^File f]
  (.getName f))

(defn files-below [d]
  (let [dir (as-file d)
        all-below (file-seq dir)]
    (filter #(.isFile %) all-below)))

(defn subdirs [d]
  (let [dir (as-file d)
        children (map #(File. dir %) (into [] (.list dir)))]
    (filter #(.isDirectory %) children)))

(defn write-to-file [f text]
  (let [file (as-file f)
        fname (.getAbsolutePath file)
        parent (.getParentFile file)]
    (fmkdirs parent)
    (spit fname text)))


;;; Safe and reliable file moves

(def do-move-files true)

(defn move-file [^File oldfile ^File newfile]
  (let [status (and do-move-files (.renameTo oldfile newfile))]
    (when-not do-move-files
      (printf "/bin/mv -i '%s' '%s'\n" (.getPath oldfile) (.getPath newfile)))
    status))

(defn nonexistent-file
  ([^File f]
   (nonexistent-file (.getParentFile f) (.getName f)))
  ([^File dir ^String fname]
   (let [check (File. dir fname)]
     (if (.exists check)
       (nonexistent-file dir (str "d" fname))
       check))))

(defn move-file-if-nonexistent [^File old ^File new]
  (if (.exists new)
    (fail "already exists: %s\n" new)
    (let [new-parent (.getParentFile new)]
      (.mkdirs new-parent)
      (if (.exists new-parent)
        (if (.isDirectory new-parent)
          (let [moved? (move-file old new)]
            (or moved?
                (fail "could not move \"%s\" to \"%s\"\n" old new)))
          (fail "would be destination exists as non-directory: %s\n" new-parent))
        (fail "failed to create destination: %s\n" new-parent)))))

;; takes a single vector with both files within
(defn do-move-file [[^File old ^File new]]
  (move-file-if-nonexistent old (nonexistent-file new)))

