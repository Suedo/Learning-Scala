package week1
/*
 * This seeks to show the difference between 
 * val x = something
 * and
 * def x(some arguments) = something
 */
object ValDef {
  def loop: Int = loop // the cliche infinite loop element
  def x = loop  // def is Call By Name in essence
  val y = println("you can see mee");
  // execution enters infinite loop below , as val tries to evaluate loop , 
  // because unlike def ,  val is CBV in nature whereas def is CBN
  val z = loop 

  def main(args: Array[String]) {
    // execution will never reach the print below.
    println("you can't see mee");
  }
}