package actors101

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ImmutableBankAccount extends App {

  object DebitCard {
    case class ConnectBank(bankActor: ActorRef)
    case object CheckBankBalance
    case class Transact(amount: Int)
  }
  class DebitCard extends Actor {
    import DebitCard._
    import BankAccount._

    override def receive: Receive = {
      case ConnectBank(bank) => context.become(attachedCreditCard(bank))
    }

    def attachedCreditCard(bankAccount: ActorRef): Receive = {
      case Transact(amount) => bankAccount ! Withdraw(amount)
      case CheckBankBalance => bankAccount ! CheckBalance
    }
  }

  object BankAccount {
    case class Deposit(amount: Int)
    case class Withdraw(amount: Int)
    case object InitializeAccount
    case object CheckBalance
  }
  class BankAccount extends Actor {

    import BankAccount._
    import DebitCard._

    override def receive: Receive = {
      case InitializeAccount =>
        // DebitCard is created as a child actor
        val card: ActorRef = context.actorOf(Props[DebitCard], "debitCard")
        card ! ConnectBank(self)
        context.become(InitializedAccount(0))
    }

    def InitializedAccount(bankBalance: Int): Receive = {
      case Deposit(amount) =>
        println(s"In deposit, current Bank Balance: $bankBalance")
        context.become(InitializedAccount(bankBalance + amount))
      case Withdraw(amount) =>
        println(s"In Withdraw, current Bank Balance: $bankBalance")
        context.become(InitializedAccount(bankBalance - amount))
      case CheckBalance => println(s"Bank Balance: $bankBalance")
    }
  }


  val system = ActorSystem("ImmutableBanking")
  private val sbi: ActorRef = system.actorOf(Props[BankAccount],"sbi")
  private val debitCard: ActorRef = system.actorOf(Props[DebitCard], "debitCard")

  import BankAccount._
  import DebitCard._

  sbi ! InitializeAccount
  debitCard ! ConnectBank(sbi)

  (1 to 5) foreach (_ => sbi ! Deposit(100))
  (1 to 3) foreach (_ => debitCard ! Transact(50))
  debitCard ! CheckBankBalance


}
