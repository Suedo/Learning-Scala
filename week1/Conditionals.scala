package week1

object Conditionals {
  def less(e1: Int, e2: Int) = {
    e1 < e2;
  }

  /*
   * if-else : unlike java , if-else in scala is an expression , Not a statement.
   * Below , the abs() function has an if-else expression as its body, it is different from the
   * 
   * if(condition 1){ do some stuff; return some value; }
   * else { do some other stuff; return some other value; }
   * 
   * construct found in Java. The above is a statement , but scala's ifelse is an expression	 
   */

  def abs(x: Int) = if (x > 0) x else -x; // the if-else expression is evaluated based on the x.
  /* Short Circuit Evaluation 
   * && (and , guard operator) : left to right flow possible as long as left is true 
   * || (or , "seeker of truth" operator) : left to right flow possible as long as left is false , thus seeks for the 1st true expression
   */

  // functional implementation of &&
  def and(left: Boolean, right: Boolean) = if (left) right else left;
  // functional implementation of ||
  def or(left: Boolean, right: Boolean) = if (!left) right else left;

  def main(args: Array[String]) {
    println(!true);
    println(!false);

    println((true && less(2, 3)) == and(true, less(2, 3))); // true
    println((false && less(2, 3)) == and(false, less(2, 3))); // true

    println((true || less(2, 3)) == or(true, less(2, 3))); // true
    println((false || less(2, 3)) == or(false, less(2, 3))); // true

    println(abs(-5));

  }
}