(ns com.github.zjjfly.cfb.chapter3.Function)
;函数调用
(+ 1 2 3 4)
(* 1 2 3 4)
(first [1 2 3 4])
;clojure的操作可以使一个函数,也可以是一个函数表达式(返回值是函数的表达式)
((or + -) 1 2 3)

;clojure的函数可以使用其他函数做参数,也可以把函数作为返回值
(map inc [0 1 2 3])

;除了函数调用,还有两种表达式
;一种是宏,一种是特殊形式,目前先说特殊形式
;特殊形式和函数不同的地方是,它不总是解析它的所有的操作元
;比如if,它永远只解析一个分支
;还有一个不同之处是,你不能把它们作为参数传递给函数
;宏也有这两个特点

;定义函数
(defn too-enthusiastic
  "Return a cheer that might be too enthusiastic"
  [name]
  (str "OH. MY. GOD! " name " YOU ARE MOST DEFINITELY LIKE THE BEST "
       "MAN SLASH WOMAN EVER I LOVE YOU AND WE SHOULD RUN AWAY SOMEWHERE"))
(too-enthusiastic "Zelda")
;clojure函数的参数可以使0到多个,参数的个数称为元数
;函数支持元数的重载,即在定义函数的时候可以定义多个参数列表,对不同的参数列表定义不同的函数体
(defn multi-arity
  ([first last]
   (str first last))
  ([first second last]
   (str first second last)))
(multi-arity "1" "2")
(multi-arity "1" "2" "3")
;元数重载是一种实现函数默认值的方法
(defn x-chop
  "Describe the kind of chop you're inflicting on someone"
  ([name chop-type]
   (str "I " chop-type " chop " name "! Take that!"))
  ([name]
   (x-chop name "karate")))
(x-chop "jjzi")
(x-chop "jjzi" "zijj")

;clojure函数还支持剩余参数
;通过&号,把余下的参数放入一个list中,并使用下一个参数名给这个list命名
;这样就实现了可变元数的函数
(defn codger-communication
  [whippersnapper]
  (str "Get off my lawn," whippersnapper "!!!"))
(defn codger
  [& whippersnappers]
  (map codger-communication whippersnappers))
(codger "Billy" "Anne-Marie" "The Incredible Bull")
;剩余参数可以和一般的参数混合使用,但必须把它放在最后

;clojure还有更高明的定义参数的方法--解构
;它可以让你方便的直接给集合中的元素绑定名字
(defn my-first
  [[first-thing]]
  first-thing)
;解构list或vector,把第一个参数绑定到first-thing
(my-first ["oven" "bike"])
(my-first '(1 2))
;解构也可以和剩余参数合用
(defn chooser
  [[first-choice second-choice & unimportant-choices]]
  (println (str "Your first choice is: " first-choice))
  (println (str "Your second choice is: " second-choice))
  (println (str "We're ignoring the rest of your choices. "
                "Here they are in case you need to cry over them: "
                (clojure.string/join ", " unimportant-choices))))
(chooser ["Marmalade", "Handsome Jack", "Pigpen", "Aquaman"])
;还可以解构map
(defn announce-treasure-location
  [{lat :lat lng :lng}]
  (println (str "Treasure lat:" lat))
  (println (str "Treasure lng:" lng)))
(announce-treasure-location {:lat 28.22 :lng 81.33})
;上面的解构语法还有一种简写
(defn announce-treasure-location
  [{:keys [lat lng]}]
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))
(announce-treasure-location {:lat 28.22 :lng 81.33})
;可以通过:as这个关键字保持被解构的那个wmap
(defn recieve-treasure-location
  [{:keys [lat lng] :as treasure-location}]
  (println treasure-location)
  (println (str "Treasure lat: " lat))
  (println (str "Treasure lng: " lng)))
(recieve-treasure-location {:lat 28.22 :lng 81.33})

;匿名函数,有两种方式定义匿名函数
;一种是fn
(map (fn [name] (str "Hi," name))
     ["Darth Vader" "Mr.Magoo"])
;还有一种是#()
(#(* % 3) 8)
(map #(str "Hi," %)
     ["Darth Vader" "Mr.Magoo"])
;%代表传入的参数,如果有多个参数,使用%1,%2,%3....
(#(* %1 %2 3) 2 4)
;可以使用%&表示剩余参数
(#(identity %&) 1 "blarg" :yip);(1 "blarg" :yip)

;函数作为返回值,是一个闭包,所以它可以访问它生成的时候的命名空间中的所有变量
(defn inc-maker
  "Create a custom incrementor"
  [inc-by]
  #(+ % inc-by))
(def inc3 (inc-maker 3))
(inc3 7)


