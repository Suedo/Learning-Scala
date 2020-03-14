package Scala4Java

object test extends App {

  class Item(val name: String, val price: Double)
  object Item {
    def apply(name: String, price: Double): Item = new Item(name, price)
  }

  class Address(val street: String, val city: String, val pin: Int)
  object Address {
    def apply(street: String, city: String, pin: Int): Address = new Address(street, city, pin)
  }

  class Customer(val name: String, val address: Address) {
    var cart: Map[Item, Int] = Map(Item("", 0) -> 0)
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
  }

  class DiscountedCustomer(name: String, address: Address, val discount: Int) extends Customer(name, address) {
    override def total(startingAmount: Double): Double = super.total(startingAmount) * (1 - (discount * .01)) // constructor
  }

  val home: Address = Address("18th Cross", "Bangalore", 560102)
  val cust = new DiscountedCustomer("Somjit", home, 5)

  cust.addToCart(Item("cabbage", 20), 1)
    .addToCart(Item("pepsi", 80), 1)

  println("Total Expense: " + cust.total()) // 95
}