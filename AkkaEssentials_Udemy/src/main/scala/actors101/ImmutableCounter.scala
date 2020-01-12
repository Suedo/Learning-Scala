package actors101

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ImmutableCounter extends App {

  object Counter {
    case object Increment
    case object Decrement
    case object Print
  }

  class Counter extends Actor {
    override def receive: Receive = countReceive(0)

    import Counter._
    def countReceive(count: Int): Receive = {
      case Increment =>
        println(s"Increment: $count")
        context.become(countReceive(count + 1))
      case Decrement =>
        println(s"Decrement: $count")
        context.become(countReceive(count - 1))
      case Print => println(s"The count is $count")
    }
  }

  import Counter._

  private val actorSystem = ActorSystem("MessagesBehaviorsTest")
  private val counter: ActorRef = actorSystem.actorOf(Props[Counter], "counter1")


  (1 to 5) foreach (_ => counter ! Increment)
  (1 to 3) foreach (_ => counter ! Decrement)
  counter ! Print
}
