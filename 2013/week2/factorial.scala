package week2
import scala.annotation.tailrec
object factorial {
	/**
	 * simple recursion >> BAD , don't use this !
	 */
	def simpleFactorial(x: Double): Double = {
		if (x == 0) 1
		else x * simpleFactorial(x - 1);
	}
	/**
	 * tail recursion based approach
	 */
	def factRec(x: Double): Double = {

		@tailrec // shows if the functions is really tail-recursive or not.
		def iter(acc: Double, x: Double): Double = {
			if (x == 0) acc
			else iter(acc * x, x - 1); // calls itself : tail recursion
		}
		iter(1, x);
	}
	/**
	 * loop based approach
	 */
	def fact(x: Int): BigInt = {
		var acc: BigInt = 1; var n = x;
		while (n > 0) { acc *= n; n -= 1; }
		acc
	}

	def main(args: Array[String]) {
		println("\n" + fact(100));
	}
}