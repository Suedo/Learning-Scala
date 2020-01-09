package week2
/*
 * function application associates to the left :
 * Ex : sum(cubes)(1,10) = (sum(cubes))(1,10)
 * 
 * Function types associate to the left
 * Ex: sum(cubes)(1,10) this has return types as:
 * (Double => Double) => (Int => Int) => Double
 * which can be written as (Double => Double) => ((Int => Int) => Double)
 * ie , the original function sum() takes in (Double => Double) and returns a function
 * that itself is like : (Int => Int) => Double , ie that returned function takes in (Int , Int)
 * and returns a Double value.
 * 
 * Basically we can write:
 * def f(args1)(args2)(args3)...(argsN)= E // where N > 0 , E = body of def
 * as : def f(args1)(args2)(args3)...(args(N-1)) = {def g(argsN)=E ; g}
 * as : def f(args1)(args2)(args3)...(args(N-1)) = (argsN => E) // anonymous
 * .
 * .
 * finally as : def f = (args1 =>(args2 =>(args3 =>...(argsN => E)..)
 * This style of a function definition as a chain of nested anonymous functions is known as
 * CURRYING ( or Partial Application as in JavaScript ) named after Haskell Brooks Curry.
 * 
 */
object product {
  // example of function returning a function , aka currying 
  def product1(f: Double => Double): (Int, Int) => Double = {
    def prod(lower: Int, upper: Int): Double = {
      def iter(acc: Double, lower: Double): Double = {
        if (lower > upper) acc
        else iter(acc * f(lower), lower + 1)
      }
      iter(1, lower)
    }
    prod
  }

  /*
   *  Scala has a dedicated syntax to facilitate currying.
   *  viz : def function(args1)(args2)..(argsn)=E;
   *  This lets us write more compact code , product2 is wriiten in that compact version
   *  The advantage of this method is that we can do product2() independently ,
   *  and the upper and lower bounds can be supplied later. 
   */
  def product2(f: Double => Double)(lower: Int, upper: Int): Double = {
    def iter(acc: Double, lower: Double): Double = {
      if (lower > upper) acc
      else iter(acc * f(lower), lower + 1)
    }
    iter(1, lower)
  }
  def fact(n: Int) = product2(x => x)(1, n)
  def main(args: Array[String]) {
    println(fact(5));
  }
}