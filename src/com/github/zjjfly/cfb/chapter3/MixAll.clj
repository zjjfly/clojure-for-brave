(ns com.github.zjjfly.cfb.chapter3.MixAll)
(def asym-hobbit-body-parts [{:name "head" :size 3}
                             {:name "left-eye" :size 1}
                             {:name "left-ear" :size 1}
                             {:name "mouth" :size 1}
                             {:name "nose" :size 1}
                             {:name "neck" :size 2}
                             {:name "left-shoulder" :size 3}
                             {:name "left-upper-arm" :size 3}
                             {:name "chest" :size 10}
                             {:name "back" :size 10}
                             {:name "left-forearm" :size 3}
                             {:name "abdomen" :size 6}
                             {:name "left-kidney" :size 1}
                             {:name "left-hand" :size 2}
                             {:name "left-knee" :size 2}
                             {:name "left-thigh" :size 4}
                             {:name "left-lower-leg" :size 3}
                             {:name "left-achilles" :size 1}
                             {:name "left-foot" :size 2}])
(defn matching-part
  [part]
  {:name (clojure.string/replace (:name part) #"^left-" "right-")
   :size (:size part)})
(defn symmetrize-body-parts
  "docstring"
  [asym-body-parts]
  (loop [remaining-asym-parts asym-body-parts
         final-body-parts []]
    (if (empty? remaining-asym-parts)
      final-body-parts
      (let [[part & remaining] remaining-asym-parts]
        (recur remaining
               (into final-body-parts
                     (set [part (matching-part part)])))))))
(symmetrize-body-parts asym-hobbit-body-parts)

;自己实现的symmetrize-body-parts
(defn symmetrize-body
  [asym-body-parts]
  (set
    (flatten                                                ;flatten只对序列(list,vector)有用,所以只能先flatten后再用set去除重复
      (map #(vector % (matching-part %)) asym-body-parts))))
(symmetrize-body asym-hobbit-body-parts)

;let表达式
(let [x 3]
  x)
(def dalmatian-list
  ["Pongo" "Pertida" "Puppy 1" "Puppy 2"])
(let [dalmatians (take 2 dalmatian-list)]
  dalmatians)

;loop表达式
(loop [iteration 0]
  (println (str "Iteration:" iteration))
  (if (> iteration 3)
    (println "Goodbye!")
    (recur (inc iteration))))

;recur还可以用在函数中
(defn testCursive
  [x]
  (if (> x 100)
    x
    (recur (inc x))))
(testCursive 1)

;正则表达式
;正则的字面量形式是#"..."
;re-find用来查找匹配的字符串,有就返回匹配的文本,没有返回nil
(re-find #"^left-" "left-eye")                              ;left-
(re-find #"^left-" "cleft-chin")                            ;nil

;使用reduce重写symmetrize-body-parts
(defn better-symmetrize-body-parts
  [asym-body-parts]
  (reduce
    #(into %1 (set [%2 (matching-part %2)]))
    [] asym-body-parts))
(better-symmetrize-body-parts asym-hobbit-body-parts)

;揍人方法的实现
(defn hit
  [asym-body-parts]
  (let [sym-parts (better-symmetrize-body-parts asym-body-parts)
        body-part-size-sum (reduce + (map :size sym-parts))
        target (rand body-part-size-sum)]
    (loop [[part & remaining] sym-parts
           accumulate-size (:size part)]
      (if (> accumulate-size target)
        part
        (recur remaining (+ accumulate-size (:size (first remaining))))))))
(hit asym-hobbit-body-parts)
