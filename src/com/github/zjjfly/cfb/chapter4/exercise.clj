(ns com.github.zjjfly.cfb.chapter4.exercise)

(def filename "suspects.csv")
(def vamp-keys [:name :glitter-index])
(slurp filename)
(defn parse
  "Convert a CSV into rows of columns"
  [string]
  (map #(clojure.string/split % #",")
       (clojure.string/split string #"\n")))
(defn str->int [str]
  (Integer/parseInt str))
(def conversions {:name identity :glitter-index str->int})
(defn convert [vamp-key value]
  ((get conversions vamp-key) value))
(defn mapify
  "Return a seq of maps like {:name \"Edward Cullen\" :glitter-index 10}"
  [rows]
  (map
    #(zipmap vamp-keys
             (map convert vamp-keys %))
    rows))
;;1
(defn glitter-filter
  [minimum-glitter records]
  (into [] (map :name (filter #(>= (:glitter-index %) minimum-glitter) records))))
(glitter-filter 3 (mapify (parse (slurp filename))))
(def suspects (mapify (parse (slurp filename))))
;;2&3
(defn validate
  [conversions m]
  (every? true? (map #(contains? m %) (keys conversions))))
(defn append
  [m]
  (when (validate conversions m)
    (conj suspects
          (zipmap (keys m)
                  (map apply
                       (vals conversions) (map list (vals m)))))))
(append {:name "jjzi" :glitter-index "10"})
(validate conversions {:name "jjzi" :glitter-index "8"})
;4
(map
  #(clojure.string/join "," %)
  (reduce
    #(zipmap (map %1 suspects) (map %2 suspects))
    (keys conversions)))
