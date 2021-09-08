(ns com.github.zjjfly.cfb.chapter5.PureFunction)

;纯函数有两个特点
;1.没有副作用.副作用指的是改变了特定范围内的变量的名称和它的值之间的关联,包括IO操作
;2.相同的输入必有相同输出,这点也叫做引用透明

;数学中的函数都是纯函数
(+ 1 2)
;函数如果依赖的是一个不可变值,那么它是引用透明的
(defn wisdom
  [words]
  (str words ", Daniel-san"))
(wisdom "Are yo OK")
;相反的,任何依赖随机数的函数都不是引用透明的
(defn year-end-evaluation
  []
  (if (> (rand) 0.5)
    "You get a raise!"
    "Better luck next year!"))
(year-end-evaluation)
;有读取文件内容的函数也不是引用透明的,因为文件内容可能更改
(defn analysis
  [text]
  (str "Character count: " (count text)))
(defn analyze-file
  [filename]
  (analysis (slurp filename)))
(analyze-file "project.clj")
