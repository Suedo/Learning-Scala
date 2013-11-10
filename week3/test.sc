package week3

object test {
  val root = new NonEmpty(10 , new Empty ,new Empty)
                                                  //> root  : week3.NonEmpty = {.10.}
	var t1 = root incl 12 incl 5 incl 4 incl 7 incl 6 incl 3
                                                  //> t1  : week3.IntSet = {{{{.3.}4.}5{{.6.}7.}}10{.12.}}
	t1 contains 6                             //> res0: Boolean = true
}