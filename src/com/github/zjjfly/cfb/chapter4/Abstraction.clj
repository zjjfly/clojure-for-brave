(ns com.github.zjjfly.cfb.chapter4.Abstraction)
;clojure的map和reduce函数式高度抽象的,它们可以传入任何实现序列抽象的对象
;如果first,rest,cons这三个函数可以操作某个数据结构,那么这个数据结构就是实现序列抽象的
(defn titleize
  [topic]
  (str topic "for the Brave and True"))
(map titleize ["Hamsters" "Ragnarok"])
(map titleize '("Empathy" "Decorating"))
(map titleize #{"Elbows" "Soap Carving"})
(map #(titleize (second %)) {:uncomfortable-thing "Winking"})
;当你调用map,first,rest或cons的时候,clojure对传入的数据结构调用seq方法
(seq '(1 2 3))
(seq [1 2 3])
(seq #{1 2 3})
(seq {:name "jjzi" :occupation "aa"}) ;seq把map的每个键值转成了包含两个元素的vector
;可以使用into把这个seq转成map
(into {} (seq {:a 1 :b 2 :c 3}))
