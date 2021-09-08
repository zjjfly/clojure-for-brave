(ns com.github.zjjfly.cfb.chapter4.Seqs)

(def vampire-database
  {0 {:makes-blood-puns? false, :has-pulse? true :name "McFishwich"}
   1 {:makes-blood-puns? false, :has-pulse? true :name "McMackson"}
   2 {:makes-blood-puns? true, :has-pulse? false :name "Damon Salvatore"}
   3 {:makes-blood-puns? true, :has-pulse? true :name "Mickey Mouse"}})
(defn vampire-related-details
  [social-security-number]
  (Thread/sleep 1000)
  (get vampire-database social-security-number))
(defn vampire?
  [record]
  (and (:makes-blood-puns? record)
       (not (:has-pulse? record))
       record))
(defn identify-vampire
  [social-security-numbers]
  (first (filter vampire? (map vampire-related-details social-security-numbers))))
;time可以打印执行方法的耗时
(time (identify-vampire [0 1 2 3]))
;map返回的序列是lazy seq
;lazy seq只有在需要的时候才会去获取这个值,类似Java8的Stream
;所以下面这个函数很快就返回结果了
(time (def mapped-details (map vampire-related-details (range 0 10000))))
(time (first mapped-details))
;为什么获取第一个值会花费32秒?因为当clojure去获取lazy seq的值的时候,会同时去获取接下来的几个值
;通过32秒这个结果,可以看到一次获取了32个值
;所以,获取接下来的31个值都会非常快
(vampire-related-details 1)

;无穷序列
;clojure中产生无穷序列的一些函数:
;1.repeat,重复一个元素
(concat (take 8 (repeat "na")) ["Batman"])
;2.repeatedly 重复调用一个函数生成元素
(take 3 (repeatedly #(rand-int 10)))
;无限序列的关键就是没有具体的结束点,你不知道下一个元素是什么,元素不断生成就不可以不断获取.
;所以,可以自己实现生成无限序列的方法
(defn event-numbers
  ([] (event-numbers 0))
  ([n] (cons n (lazy-seq (event-numbers (+ n 2)))))) ;lazy-seq让我们可以在用到的时候在对元素求值
(take 10 (event-numbers))





