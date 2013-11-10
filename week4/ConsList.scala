package week4

trait List[T] {
	def isEmpty: Boolean
	def head: Int
	def tail: List[T]
}
class Nil[T] extends List[T] {
	def isEmpty: Boolean = true
	def head: Nothing = throw new NoSuchElementException("Nil.head")
	def tail: Nothing = throw new NoSuchElementException("Nil.tail")
	override def toString() = "."
}
class Cons[T](val head: Int, val tail: List[T]) extends List[T] {
	def isEmpty = false
	override def toString() = {
		def stringify(x:List[T]): String = {
			if (tail isEmpty) "" + head
			else head + stringify(tail)
		}
		stringify(this)
	}
} 