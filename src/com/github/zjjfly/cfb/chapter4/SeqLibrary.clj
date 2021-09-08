(ns com.github.zjjfly.cfb.chapter4.SeqLibrary)
;map还有两张用法:1.接受多个集合作为参数 2.把一个函数的集合作为参数
(map str ["a" "b" "c"] ["A" "B" "C"]);接受多个集合作为参数
(def sum #(reduce + %))
(def avg #(/ (sum %) (count %)))
(defn stats
  [numbers]
  (map #(% numbers) [sum count avg]))
(stats [2 3 10]);把一个函数的集合作为参数
;map还有一个比较常用的模式:使用关键字作为函数
(def identities
  [{:alias "Batman" :real "Bruce Wayne"}
   {:alias "Spider-Man" :real "Peter Parker"}
   {:alias "Santa" :real "Your mom"}
   {:alias "Easter Bunny" :real "Your dad"}])
(map :real identities)

;reduce的一些其他用法
;1.有一个map产生一个新map,键不变,值会更新
(reduce (fn [new-map [key val]]
          (assoc new-map key (inc val)))
        {} {:max 30 :min 10})
;还有一个用法是是过滤掉不符合条件的键值对
(reduce (fn [new-map [key val]]
          (if (> val 4)
            (assoc new-map key val)
            new-map))
        {}
        {:human 4.1
         :critter 3.9})

;take drop take-while drop-while
(take 3 [1 2 3 4 5 6])
(drop 3 [1 2 3 4 5 6])
(def food-journal
  [{:month 1 :day 1 :human 5.3 :critter 2.3}
   {:month 1 :day 2 :human 5.1 :critter 2.0}
   {:month 2 :day 1 :human 4.9 :critter 2.1}
   {:month 2 :day 2 :human 5.0 :critter 2.5}
   {:month 3 :day 1 :human 4.2 :critter 3.3}
   {:month 3 :day 2 :human 4.0 :critter 3.8}
   {:month 4 :day 1 :human 3.7 :critter 3.9}
   {:month 4 :day 2 :human 3.7 :critter 3.6}])
(take-while #(< (:month %) 3)food-journal);take元素直到predicate函数返回false
(drop-while #(< (:month %) 3)food-journal);drop元素直到predicate函数返回false

;filter some
(filter #(< (:human %) 5) food-journal)
;some返回第一个符合条件的值(调用函数后得到的值)
(some #(> (:critter %) 5) food-journal)
(some #(> (:critter %) 3) food-journal);true
;如果相反会这个元素,可以这样写:
(some #(and (> (:critter %) 3) %) food-journal)

;sort sort-by
(sort [2 1 3])
(sort-by count ["aaa" "c" "bb"])

;concat 把一个序列加到另一个后面
(concat [1 2] [3 4])



