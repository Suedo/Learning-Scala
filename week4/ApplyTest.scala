package week4

object Add {
	// the apply method lets us call Objects with parameters like we can with classes
	def apply(x1: Int, x2: Int): Int = x1 + x2
	def main(args: Array[String]) {
		// notice we didnt need to put "new" before Add()
		println(Add(3, 2));
	}
}