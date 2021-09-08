(ns com.github.zjjfly.cfb.chapter3.Syntax)
;form在clojure中指那些有效的代码,类似其他语言中的表达式
(+ 1 2 3)
(str "It was the panda " "in the library " "with a dust buster")

;控制结构
;if 第一个参数是一个predicate 第三四个参数是then和else对应的操作
(if true
  "By Zeu's hammer!"
  "By Aquaman's trident")
(if false
  "By Zeu's hammer!"
  "By Aquaman's trident")
;;第四个参数可以不加,如果结果是false,则返回nil
(if false
  "By Odin's Elbow")
(nil?
  (if false
    "s"))
;上面的then和else的操作都只有一个form
;如果要有多个form,需要使用另一个控制结构--do
(if true
  (do (println "Success")
      "By Zeu's hammer!")
  (do (println "Failure")
      "By Aquaman's trident"))
;when,类似if和do的组合体.
;当你想要在条件是true的时候进行多个操作,是false的时候总是返回nil,使用when
(when true
  (println "Success!")
  "abra cadabra")
(nil? (when false
        "s"))
;clojure有true和false字面量,nil表示是空值,和false不一样
;使用nil?判断是否是nil
(nil? 1)
(nil? nil)
;nil和false都表示逻辑上的false,除此之外都是逻辑true
(if "bears eat beets"
  "bears beets Battlestar Galactica")
(if nil
  "This won't be the result nil is falsey"
  "nil is falsey")

;clojure的相等使用=
(= 1 1)
(= nil nil)
(= 1 2)
;比较clojure的内置数据结构只需使用=,这和java不一样
(= "a" "a")

;布尔操作符or和and
;or返回第一个逻辑上是true的值或最后一个值
(or false nil :large_I_mean_venti :why_cant_I_just_say_large) ;:large_I_mean_venti
(or (= 0 1) (= "yes" "no"));false
;and返回第一个逻辑上是false的值,或者最后一个值
(and :free_wifi :hot_coffee);:hot_gcoffee
(and :feeling_super_cool nil false);nil

;;使用def给值命名
(def failed-protagonist-names
  ["Larry Potter" "Doreen the Explorer" "The Incredible Bulk"])
failed-protagonist-names
;clojure鼓励不给一个变量重复赋值,这和很多语言不一样
(defn error-message
  [severity]
  (str "OH GOD!IT'S A DISASTER! WE'RE "
       (if (= severity :mild)
         "MILDLY INCONVENIENCED!"
         "DOOOOOOOMED!")))
(error-message :mild)












