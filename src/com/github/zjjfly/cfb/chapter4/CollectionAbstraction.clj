(ns com.github.zjjfly.cfb.chapter4.CollectionAbstraction)

;clojure的核心数据结构都涉及了两种抽象,一种是集合抽象,一种是序列抽象
;集合抽象是关于数据结构的整体的,而序列抽象仅仅关于数据结构中的元素
;所以,像count,empty?,every?就是集合抽象中的方法
(empty? [])
(empty? ["no"])

;;下面是两个常用的集合函数:into和conj
;;很多函数返回的是seq而不是原来的类型,而into可以把它们还原成以前的类型
(into {} (map identity {:sunlight-reaction "Glitter!"}))
(into [] (map identity [:garlic :sesame-oil :fried-eggs]))
(into #{} (map identity [:garlic-clove :garlic-clove]))
;;第一个参数不一定是空集合
(into {:favorite-emotion "gloomy"} [[:sunlight-reaction "Glitter!"]])
(into ["cherry"] '("pine" "spruce"))
;;两个参数也可以是同一个类型
(into {:favorite-animal "kitty"} {:least-favorite-smell "dog"
                                  :relationship-with-teenager "creepy"})
;;into的作用就是把第二个参数中的元素加入到第一个中去

;;conj也是把元素加入到集合,但和into不同的是,它把余下的参数作为一个个元素加到第一个集合中
(conj [0] [1]) ;=>[0 [1]]
(conj [0] 1) ;=>[0 1]
;;用conj可以添加任意多个元素
(conj [0] 1 2 3 4)
(conj {:time "midnight"} [:place "ye olde cemetarium"])
;可以使用into实现conj
(defn my-conj
  [target & additions]
  (into target additions))
(my-conj [0] 1 2 3)
;;类似into和conj这样功能相同的函数很常见,其中一个函数接收剩余参数,另一个接收一个集合

