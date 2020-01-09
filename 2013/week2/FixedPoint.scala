package week2

import scala.annotation.tailrec

object FixedPoint {
	private val tolerance = 1 / 1e10;
	private def abs(x: Double) = if (x < 0) -x else x
	private def isGoodEnough(estimate: Double, value: Double): Boolean = {
		abs(value - estimate) < tolerance;
	}
	def avgDamp(f: Double => Double): Double = {
		/*
		 * Without avg damping , in some cases , we get oscillating estimates,
		 * which result in an infite loop. One such case is square root.
		 * EX : sqroot(2) without avg damping.
		 * 
		 * let the estimate be y ,and the  given number be x ( = 2 in this case)
		 * then the function would be like this : y = x/y.
		 *  
		 * placing initial estimate y = 1 >>> we obtain y = 2
		 * updating estimate with y = 2 >>> we obtain y = 1
		 * updating estimate with y = 1 >>> we obtain y = 2
		 * .... Aaand we have oscillation.
		 * 
		 * This is removed with avg damping : ie y = ((x/y) + y)/2
		 * How we obtain this form :
		 * y = x/y // initial condition
		 * or , (y+y)/2 = ((x/y) + y)/2
		 * thus y = ((x/y) + y)/2 // our avg damped equation
		 */
		@tailrec
		def iter(estimate: Double): Double = {
			if (isGoodEnough(f(estimate), estimate)) estimate
			else iter((f(estimate) + estimate) / 2) // avg damping
		}

		iter(1.0)
	}
	def fixedPoint(f: Double => Double): Double = {
		@tailrec
		def iter(estimate: Double): Double = {
			if (isGoodEnough(f(estimate), estimate)) estimate
			else iter(f(estimate))
		}

		iter(1.0)

	}
}