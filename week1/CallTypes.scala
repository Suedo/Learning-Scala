package week1
/**
 * By Theorem, if a CBV evaluation of an expression terminates ,
 * then the CBN of the same expression is also guaranteed to terminate
 * HOWEVER , converse is not necessarily true.
 *
 * CBV : Call By Value : the parameter is evaluated at the first instance it is found ,
 * and thus , this method has the advantage of evaluating the parameter only once.
 *
 * CBN : Call By Name : this has the advantage that the function argument is not evaluated
 * if the corresponding parameter is unused in the evaluation of the function body.
 *
 * In simpler terms : This means that CBN passes its arguments as is to it's inner parts,
 *  ie , the argument is assigned to its corresponding parameters as is, and evaluation occurs only when
 *  the parameters are directly evaluated at the end of all the passing around.
 *
 *  EX:
 *  sumOfSquares(3+3 , 2*2)
 *  = square(3+3) + square(2*2)
 *  = (3+3)*(3+3) + square(2*2)  each arguments are getting passed as is to the next call
 *  = (6)*(3+3) + square(2*2)
 *  = (6*6) + square(2*2)
 *  = 36 + square(2*2)
 *  = 36 + (2+2)*(2+2) // evaluation occurs only when the parameters are directly operated upon.
 *  = 36 + (4)*(2+2)
 *  = 36 + 4*4
 *  = 36 + 16
 *  = 52
 *
 * Below is an example where CBN terminates , but CBV does not.
 */
object CallTypes {

  // loop does  not reduce to a value in a finite number of terms
  def loop: Int = loop;

  def CBV(x: Int, y: Int) = x;
  def CBN(x: Int, y: => Int) = x;

  def main(args: Array[String]) {
    /*
     * CBV , when passed the "loop" variable , results in an infinite loop , 
     * as scala tries to evaluate loop, making it call itself recursively , infinitely.
     */
    
    // println(CBV(1, loop)); wont work
    
    /*
     *  this works because CBN doesn't try to evaluate loop , and merely passes it down 
     *  and down inside , loop is completely ignored , so it doesn't affect anything.
     */
    println(CBN(1,loop)); 
    
  
    /*
     * this however will break , as loop is passed to the first parameter , which 
     * is called by value. Resulting in infite loop in an effort to evaluate it.
     */
    // println(CBN(loop,1)); 
  }

}

