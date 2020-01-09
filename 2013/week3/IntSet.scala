package week3

abstract class IntSet {
	def contains(x: Int): Boolean
	def incl(x: Int): IntSet
	def union(that:IntSet):IntSet
}
/*
 * Why make Empty an "object" (a singleton) and not a class?
 * well , there is nothing to differentiate between one Empty and the other.
 * and as a result , all Empty-s are basically one and the same.
 * So creating multiple instances of them is overkill.
 * PS : object aka singletons are values , ie Empty evaluates to itself.
 */
object Empty extends IntSet {
	def contains(x: Int): Boolean = false
	def incl(x: Int): IntSet = new NonEmpty(x, Empty, Empty)
	def union(that:IntSet) = that
	override def toString() = "."
}
class NonEmpty(elem: Int, left: IntSet, right: IntSet) extends IntSet {
	def contains(x: Int): Boolean = {
		if (x < elem) left contains x
		else if (x > elem) right contains x
		else true
	}
	def incl(x: Int): IntSet = {
		if (x < elem) new NonEmpty(elem, left incl x, right)
		else if (x > elem) new NonEmpty(elem, left, right incl x)
		else this
	}
	/*
	 * in the following implementation ,
	 * each time union is called on a smaller intset : left , right are smaller than this
	 * (left union right) is also smaller than this by 1 element , ie the element of this
	 * which we add in the last step : incl elem
	 * With this argument we can convince ourselves that the recursion will converge
	 */
	def union(that:IntSet):IntSet = ((left union right)union that)incl elem
	override def toString() = "{" + left + elem + right + "}"
}

/*
 * a note on the "override" keyword : 
 * we need to put that keyword only when we are redefing an already 
 * concrete definition from the superclass in the subclass. 
 * However , when we are subclassing an abstract function , 
 * we are "implementing" the abstract classes , not re-defining them.  
 * Hence we dont need to put any "override"  keyword in there 
 * 
 * Why this is good ? 
 * In this case , scala is more picky than java , 
 * and this has its advantage because this prevents accidental overrides,
 * As well as the opposite case ,ie when you think you are overriding something,
 * and thus put in the keyword , but there is nothing to override.  
 * it will let you know about that with an error message as well. 
 * So you are protected on both fronts using override
 */