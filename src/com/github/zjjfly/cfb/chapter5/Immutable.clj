(ns com.github.zjjfly.cfb.chapter5.Immutable)
(require '[clojure.string :as s])
;clojure避免副作用的方式是使用不可变的数据结构

;以求一个int数组的和为例,clojure没有复制操作符,所以无法通过迭代数组的时候不断修改一个变量来求值
(def great-baby-name "Rosanthony")
(let [great-baby-name "Bloodthunder"]
  great-baby-name)
(= great-baby-name "Bloodthunder")                          ;false,说明let中的great-baby-name和def中great-baby-name不在一个作用域中
;要规避这点需要使用递归
(defn sum
  ([vals] (sum vals 0))
  ([[x & xs] accumulator]
   (if (nil? x)
     accumulator
     (sum xs (+ accumulator x)))))
(sum [1])
;为了性能,一般使用recur,因为clojure和Java一样不支持尾递归优化
;如果集合较小,用不用recur都可以,但如果集合较大,则要使用recur
;看上去使用递归会产生很多中间值,但其实不会,因为clojure使用了结构共享
(defn sum'
  ([vals] (sum vals 0))
  ([[x & xs] accumulator]
   (if (nil? x)
     accumulator
     (recur xs (+ accumulator x)))))
(sum' [1 2 3])
;使用函数组合来替代属性变换,好处是不会改变原有的数据
(defn clean
  [text]
  (s/replace (s/trim text) #"lol" "LOL"))
(clean "My boa constrictor is so sassy lol!  ")

;函数也可以和数据一样,有原有的函数派生出来,之前学到的partial就是这样的函数
;comp函数也是这样的函数,它专门用于组合若干函数,并把它们从右到左应用到参数上
((comp inc *) 2 3)
(def character
  {:name       "Smooches McCutes"
   :attributes {:intelligence 10
                :strength     4
                :dexterity    5}})
(def c-int (comp :intelligence :attributes))
(def c-str (comp :strength :attributes))
(def c-dex (comp :dexterity :attributes))
(c-int character)
(c-str character)
(c-dex character)
(defn spell-slots
  [char]
  (int (inc (/ (c-int char) 2))))
(spell-slots character)
;;如果要组合的函数不止一个函数.那么你需要使用匿名函数
(def spell-slots-comp (comp int inc #(/ % 2) c-int))
;comp可以组合任意数量的函数
;自己实现comp
(defn my-comp
  [& fns]
  (fn [& args]
    (let [ordered-fns (reverse fns)
          first-result (apply (first ordered-fns) args)
          remaining-fns (rest ordered-fns)]
      (reduce
        (fn [result-so-far next-fn] (next-fn result-so-far))
        first-result
        remaining-fns))))
;;还有一个函数是memorize,它利用clojure函数引用透明的特点,存储传入函数的参数和返回值,以便下次调用的时候可以直接得到结果
;;这对于那些耗时的函数很有用
(defn sleepy-identity
  "Returns the given value after 1 second"
  [x]
  (Thread/sleep 1000)
  x)
(sleepy-identity "Mr. Fantastico")
;使用memorize
(def memo-sleepy-identity (memoize sleepy-identity))
(memo-sleepy-identity "Mr. Fantastico")
