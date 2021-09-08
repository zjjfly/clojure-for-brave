(ns com.github.zjjfly.cfb.chapter4.FunctionFunctions)

;clojure的函数可以把其他函数作为参数和返回值,其中有两个奇怪的函数:apply和partial

;apply可以实现把集合打碎,把集合中的元素作为单独的一个个参数传入函数
(apply max [1 2 3 4])
(apply max '(1 2 3 4))
(apply max #{1 3 4 5})
;有了apply,可以用conj实现into
(defn my-into
  [target additions]
  (apply conj target additions))
(my-into [0] [1 2 3])

;partial用于产生偏函数,也就是已经有了部分参数的函数
(def add10 (partial + 10))
(add10 2)
(def add-missing-elements (partial conj ["water" "earth" "air"]))
(add-missing-elements "unobtainium" "adamantium")
;自己实现partial
(defn my-partial
  [partialized-fn & args]
  (fn [& more-args]
    (apply partialized-fn
           (into args more-args))))
(def add20 (my-partial + 20))
(add20 2)
;partial通常用在需要重复使用某种相同的函数和参数组合的时候
(defn lousy-logger
  [log-level message]
  (condp = log-level
    :warn (clojure.string/lower-case message)
    :emergency (clojure.string/upper-case message)))
(def warn (partial lousy-logger :warn))
(warn "Red light ahead ")

;complement函数,用于对一个函数的结果取反
(def my-neg? (complement pos?))
(my-neg? -1)
;自己实现complement
(defn my-complement
  [func]
  (fn [& args]
    (not (apply func args))))
(def my-pos? (complement neg?))
(my-pos? 1)
