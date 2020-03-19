package basics.Scala4Java

import scala.util.Sorting

object test extends App {

  class Item(val name: String, val price: Double){
    override def toString: String = s"${name}"
  }
  object Item {
    def apply(name: String, price: Double): Item = new Item(name, price)
  }

  class Address(val street: String, val city: String, val pin: Int)
  object Address {
    def apply(street: String, city: String, pin: Int): Address = new Address(street, city, pin)
  }

  class Customer(val name: String, val address: Address) {
    var cart: Map[Item, Int] = Map()
    private val discount: Int = 0

    def addToCart(item: Item, count: Int) = {
      cart = cart.get(item) match {
        case Some(value) => cart + (item -> (value + count))
        case None => cart + (item -> count)
      }
      this
    }

    def total(startingAmount: Double = 0): Double = {
      cart.foldLeft(startingAmount)((startingAmount, cartItem) => {
        val priceOfItem = cartItem._1.price
        startingAmount + (cartItem._2 * priceOfItem)
      })
    }

    override def toString: String = s"${name}'s cart: ${cart}. Total: ${total()}"
  }

  object CartComparator extends Ordering[Customer] {
    def compare(a: Customer, b:Customer) = (a.total() - b.total()).toInt
  }

  // inheritance, calling super
  class DiscountedCustomer(name: String, address: Address, val discount: Int) extends Customer(name, address) {
    override def total(startingAmount: Double): Double =
      super.total(startingAmount) * (1 - (discount * .01)) // constructor
  }

  val home: Address = Address("18th Cross", "Bangalore", 560102)
  val cust1 = new DiscountedCustomer("Somjit", home, 5)
  val cust2 = new DiscountedCustomer("CC", home, 10)
  val cust3 = new DiscountedCustomer("Sanji", home, 20)



  cust1.addToCart(Item("cabbage", 20), 1)
    .addToCart(Item("pepsi", 80), 1)


  cust2.addToCart(Item("lotion", 120), 1)
    .addToCart(Item("cream", 80), 1)


  cust3.addToCart(Item("suit", 1200), 1)
    .addToCart(Item("mushrooms", 80), 1)

  val custList = List(cust1, cust2, cust3)
  for (cust <- custList.sorted(CartComparator)) println(cust)
}