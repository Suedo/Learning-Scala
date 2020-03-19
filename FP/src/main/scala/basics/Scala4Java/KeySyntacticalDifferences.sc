
class Person(val fname: String, val mname: String = "", val lname: String) {
  // first line indicates body of the constructor
  val fullname = if (mname != "") s"${fname} ${mname} ${lname}" else s"${fname} ${lname}"

  var address: Address = null
  override def toString: String = fullname
}

class Customer(val person: Person) {
  private val id_ = Customer.nextId()

  private var discount_ = 0

  def discount = discount_ // custom getter
  def discount_=(value: Int) = { // custom setter
    discount_ = value
  }

  override def toString: String = s"${person}[${id_}] has discount: ${discount}"
}

class Address (val street:String, val city: String, val pin: Int) {

}
object Address {
  def apply(street: String, city: String, pin: Int): Address = new Address(street, city, pin)
}

object Customer {
  private var seqId = 0

  private def nextId(): Int = {
    seqId += 1
    seqId
  }
}


// not companion object of Customer
object demo {
  def main(): Unit = {
    val somjit = new Person(fname = "Somjit", lname = "Nag")
    somjit.address = Address("Talpukur", "Kolkata", 700086) // 'apply' removes need for 'new'
    val cust1 = new Customer(somjit)
    cust1.discount = 20 // using custom setter
    println(cust1)

    val sanji = new Person("Sanji", "D", "Vinsmoke")
    val cust2 = new Customer(sanji)
    cust2.discount = 5
    println(cust2)
  }
}

demo.main()