package basics

object demo extends App {

  def factorial(n: Int, acc: Int): Int = {
    if (n <= 0) acc
    else factorial(n - 1, n * acc)
  }

  println(factorial(5, 1))

  // OOP
  class Animal
  class Dog extends Animal
  val doggy: Animal = new Dog


  trait Carnivore {
    def eat(a: Animal)
  }

  class Crocodile extends Animal with Carnivore {
    def eat(a: Animal): Unit = {
      println("crunch! eating " + a)
    }
  }

  val croco = new Crocodile
  croco eat doggy

}


