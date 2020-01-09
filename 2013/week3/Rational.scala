package week3
class Rational(x: Int, y: Int) {
	require(y != 0, "Denominator must be non-zero") // throws IllegalArgumentException

	def this(x: Int) = this(x, 1) // easily creates integers
	private def gcd(a: Int, b: Int): Int = if (b == 0) a else gcd(b, a % b)
	private val g = gcd(x, y)
	private val num = x / g
	private val den = y / g
	
	// add
	def +(that: Rational): Rational =
		new Rational(this.num * that.den + that.num * this.den, this.den * that.den)
	
	// subtract
	def -(that: Rational): Rational = this + (-that) // infix "-"
	
	// negate
	def unary_- : Rational = new Rational(-this.num, this.den) // prefix "-"
	
	// multiply
	def *(that: Rational) = new Rational(this.num * that.num, this.den * that.den)
	
	// divide
	def /(that: Rational) = new Rational(this.num * that.den, this.den * that.num)
	
	// inverse
	def inv = new Rational(this.den, this.num)
	override def toString() = num + "/" + den
}