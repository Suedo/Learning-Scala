package week2
import scala.annotation.tailrec
object factorial {
  // normal recursion
  def simpleFactorial(x: Double): Double = {
    if (x == 0) 1
    else x * simpleFactorial(x - 1);
  }
  def factorial(x: Double): Double = {

    @tailrec // shows if the functions is really tail-recursive or not.
    def iter(acc: Double, x: Double): Double = {
      if (x == 0) acc
      else iter(acc * x, x - 1); // calls itself : tail recursion
    }
    iter(1, x);
  }

  def main(args: Array[String]) {
    println("\n" + factorial(100));
  }
}