package week2
import scala.annotation.tailrec
object sum {
  def sum(f: Double => Double, lower: Int, upper: Int): Double = {
    @tailrec
    def iter(acc: Double, lower: Int): Double = {
      if (lower > upper) acc
      else iter(acc + f(lower), lower + 1)
    }
    iter(0, lower)
  }
  def main(args: Array[String]) {
    println(sum(x => x * x * x, 1, 10)); // sum of cubes
    println(sum(factorial.factorial(_), 1, 10)); // sum of factorials
  }
}