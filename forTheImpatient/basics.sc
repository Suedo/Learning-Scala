package forTheImpatient
import scala.annotation.tailrec

object basics {
	//new sum(x => x * x)(1, 5);
	var test = "50.365" toDouble;             //> test  : Double = 50.365
	println(1 to 10)                          //> Range(1, 2, 3, 4, 5, 6, 7, 8, 9, 10)
	test = 0;
	for (i <- "hello"){
	 println(i toInt);
	 test += i;
	}                                         //> 104
                                                  //| 101
                                                  //| 108
                                                  //| 108
                                                  //| 111
	println(test);                            //> 532.0
	var str = "hello"                         //> str  : String = hello
	for(i <- 0 until str.size)
		println(i)                        //> 0
                                                  //| 1
                                                  //| 2
                                                  //| 3
                                                  //| 4
}