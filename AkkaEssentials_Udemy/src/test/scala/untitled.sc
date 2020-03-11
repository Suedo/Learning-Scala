//val height = 5

def generateLine(height: Int, i: Int): String = {
  val dashes: String = "-"*(height - (i + 1))
  val stars: String = "*"*((i*2) + 1)
  dashes + stars
}

(0 until 10).foreach(i => println(generateLine(10, i)))