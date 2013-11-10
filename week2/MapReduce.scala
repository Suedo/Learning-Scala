package week2

/*
 * The only difference between the previous sum and product functions were
 * the zero value and the how we combine two results in each step.
 * 
 * zeroVal for addition is 0
 * zeroVal for product is 1
 * 
 * combination scheme for addition is +
 * combination scheme for product is *
 * 
 * note : "zero value" means the starting value , not the number zero.
 * 
 */

object MapReduce {
	def mapReduce(f: Double => Double, combine: (Double, Double) => Double, zeroVal: Int)(lower: Int, upper: Int): Double = {

		def iter(acc: Double, lower: Int): Double = {
			if (lower > upper) acc
			else iter(combine(acc, f(lower)), lower + 1)
		}
		iter(zeroVal, lower);
	}
	def main(args: Array[String]) {
		println(mapReduce(x => x * x * x, (x, y) => x + y, 0)(1, 5)) // addition of cubes
		println(mapReduce(factorial.factorial(_), (x, y) => x * y, 1)(1, 6)) // product of factorials

	}
}