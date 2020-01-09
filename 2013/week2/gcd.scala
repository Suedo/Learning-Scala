package week2

object gcd {
  def gcd(a: Int, b: Int): Int = {
    if (b == 0) a
    else {
      println("a: "+a+" , b: "+b);
      gcd(b, a % b);
    }
  }

  def main(args: Array[String]) {
    println(gcd(14, 91));
  }
}