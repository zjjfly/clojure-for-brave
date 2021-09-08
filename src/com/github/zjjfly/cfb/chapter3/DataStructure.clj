(ns com.github.zjjfly.cfb.chapter3.DataStructure)
;clojure的数据结构都是不可变的
;数字,clojure可以直接表示分数
94
1.2
1/5

;Strings
;clojure中只能使用双引号包括字符串,而且没有修改字符串的方法
(def name "Chewbacca")
(str "\"Uggllglglglglglglll\" - " name)

;map
;clojure中有两种map,hash map和sorted map,目前只说hash map
;map字面量,使用{...}声明
{};空map
{:first-name "Charlie"
 :last-name "McFiswich"}
{"string-key" +}
;map可以嵌套
{:name {:first "John" :middle "Jacob" :last "Jingleheimerschmidt"}}
;使用hash-map函数声明map
(hash-map :a 1 :b 2)
;使用get从map中取键对应的值
(get {:a 0 :b 1} :b)
(get {:a 0 :b {:c "ho hum"}} :b)
;如果map中没有这个键,那么get返回的是nil
(nil? (get {:a 0 :b 1} :c))
;可以设置一个默认值,当没有这个key的时候返回默认值
(get {:a 0 :b 1} :c "unicorns?")
;使用get-in可以取嵌套map中的值
(get-in {:a 0 :b {:c "ho hum"}} [:b :c])
;可以把map看作是一个function,直接使用key去取值
({:name "The Human Coffeepot"} :name)
;甚至可以使用key作为函数去map中查找对应的值
;推荐使用这种方式,因为当map为nil的时候,这种方法不会抛出异常
(:name {:name "The Human Coffeepot"})

;关键字
;它的首要作用是作为map中的key,以:开头,后面可以包含任意非空字符
:a
:rumplestilltsken
:34
:_?
;它还有一个作用是可以当做函数用来查找数据结构中相关的值,clojure程序员都这么用
(:a {:a 1 :b 2 :c 3})
;也可以设置一个默认值
(:d {:a 1 :b 2 :c 3} "No gnome knows homes like Noah knows")

;Vector,类似数组的存在
;vector字面量
[3 2 1]
(get [3 2 1] 0)
(nth [3 2 1] 0);nth更符合语义
;使用vector函数生成vector
(vector "creepy" "full" "moon")
;使用conj函数添加一个元素到vector的结尾
(conj [1 2 3] 4)

;List
;类似vector,但也有一些不同之处,比如不能使用get取其中的元素
;list字面量
'(1 2 3 4)
;list使用nth获取元素,而不是get
(nth '(:a :b :c) 0)
(nth '(:a :b :c) 2)
;使用list函数生成list
(list 1 "two" {3 4})
;同样可以使用conj添加元素,但不是添加在尾部,而是添加在头部
(conj '(1 2 3) 4);(4 1 2 3)

;set
;clojure中有两种set,hash set和sorted set,其中hash set更常用
;字面量
#{"kurt vonnegut" 20 :icicle};元素实际的顺序可能和声明的时候不一样
;使用hash-set生成hash set
(hash-set 1 1 2 2)
;还是可以使用conj添加元素,如果set中已经有这个元素,不会放入其中
(conj #{:a :b} :b)
;还可以使用set函数把已有的vector或list变成set
(set [3 3 3 4 4])
(set '(1 2 3 2))
;使用contain?可以判断一个元素是否在set中
(contains? #{:a :b} :a)
(contains? #{:a :b} 3)
(contains? #{nil} nil)
;也可以使用get和关键字做函数的方式判断一个元素是否在其中
;如果得到是nil表示这个元素不在其中
(:a #{:a :b})
(get #{:a :b} :a)
(get #{:a nil} nil)
(get #{:a :b} "kk")
;我们发现,使用get去判读set其中是否有nil 总是会返回nil,无论其中是否存在nil,所以最好还是使用contain?去判断
;contain?也可以用在vector和map上
(contains? [1 2] 1)
(contains? {:1 2} :1)













