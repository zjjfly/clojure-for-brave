(ns com.github.zjjfly.cfb.chapter3.exercise)
(require '[clojure.string :as str])
;1
(str "1" "2")
(vector 1 2 3)
(list 1 2 3)
(hash-map :a 1 :b 2)
(hash-set 2 1 1 2 3)
;2
(defn add-100
  [i]
  (+ 100 i))
(add-100 1)
;3
(defn dec-marker
  [dec-num]
  (fn [x]
    (- x dec-num)))
(def dec9 (dec-marker 9))
(dec9 10)
;4
(defn mapset
  [f col]
  (hash-set (map f col)))
(mapset inc [2 3])
;6
(def body-parts [{:name "eye-1" :size 1}
                 {:name "ear-2" :size 1}
                 {:name "mouth-3" :size 1}
                 {:name "nose-4" :size 1}
                 {:name "neck-4" :size 2}
                 {:name "left-foot-4" :size 2}])
(defn matching-part
  [part, num]
  (loop [iter 0
         parts []]
    (if (>= iter num)
      parts
      (recur
        (inc iter)
        (conj parts
              {:name (str/replace (:name part) #"^(.*)(\d+)$" (str "$1" (String/valueOf iter))) :size (:size part)})))))
(flatten
  (map #(matching-part % 10) body-parts))
