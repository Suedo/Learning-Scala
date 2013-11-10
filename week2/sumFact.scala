package week2

import scala.annotation.tailrec

object sumFact {

  def sumFact(lower: Int, upper: Int): Double = {
    @tailrec
    def iter(acc: Double, lower: Int): Double = {
      if (lower > upper) acc
      else iter(acc + factorial.factorial(lower), lower + 1) // calls itself > tail recursive
    }
    iter(1, lower);

  }
  def main(args: Array[String]) {
    println(sumFact(2, 7));
  }
}