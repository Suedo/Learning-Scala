// basic map and list
val l1 = List(1,2,3)
val m1 = Map(1 -> "Amar", 2 -> "Akbar", 3 -> "Antony")

for (value <- l1) println(value)
for ((k,v) <- m1) println(k,v)

// using java collections
val list = new java.util.ArrayList[String]
list add "Hello"
println(list)

// small demo of less boilerplate
val bdTotal = BigDecimal(10) + BigDecimal(20)   // scala
// BigDecimal total = new BigDecimal(10).add(new BigDecimal(20)) // java


// unkike java, scala does not have `protected` access modifier. it uses other things to acheive the same thing