package week1

object sqroot {
  /* 
   * Newton's Method of sq root .
   */
  def sqroot(x: Double): Double = {
    /*
     * x for abs() and mean() are different than the x passed to sqroot.
     * the new x over-shadows the outer x( ie the one passed to sqroot()).
     */
    def abs(x: Double) = if (x < 0) -x else x;
    def mean(x: Double, y: Double) = (x + y) / 2;

    val epsilon = x / (1e10); // note : this is the outer x

    // must provide a return type for a recursive function
    def iter(estimate: Double): Double = {
      /*
       * if estimate is good enough , return the estimate , else 
       * we repeatedly improve our estimate of the sqroot by 
       * taking the mean of the estimate and x/estimate
       */
      if (abs((estimate * estimate) - x) < epsilon) estimate;
      else {
        println(estimate);
        iter(mean((x / estimate), estimate)); // tail recursion.
      }
    }

    iter(1.0)

  }
  def main(args: Array[String]) {
    println(sqroot(64));
  }
}