package week2

object root {
	def root(x:Double) = FixedPoint.avgDamp(y => x / y)
	def main(args: Array[String]) {
		println(root(2));
}
}