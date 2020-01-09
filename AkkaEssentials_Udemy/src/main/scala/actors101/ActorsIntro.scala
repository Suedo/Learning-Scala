package actors101

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ActorsIntro extends App {

  // step 1: define the actor system
  private val actorSystem = ActorSystem("FirstActorSystem")
  println(actorSystem.name)

  /*
    1. Each actor in the actorsystem will be uniquely identified
    2. all message communications happen async
    3. Each actor may react differently
    4. Never try to directly access an actors internal behaviour
   */

  // step 2: Create an Actor
  class WordCounter extends Actor {
    // internal state
    var totalWords = 0

    // behaviour
    def receive: PartialFunction[Any, Unit] = {
      case message: String =>
        println(message)
        totalWords += message.split(" ").length
      case _ => println("[WordCounter] Bhai bujlam na!")
    }
  }

  // step 3: Instantiate our actor
  private val wc: ActorRef = actorSystem.actorOf(Props[WordCounter], "myWordCounter")
  private val wc2: ActorRef = actorSystem.actorOf(Props[WordCounter], "myWordCounter2")

  wc ! "Akka hebby jinis bhaii!!"
  wc2 ! "Ekdom Bhai!"

  println(wc.path.name) // myWordCounter


  class Person(name: String) extends Actor {
    override def receive: Receive = {
      case _ => println("It's Alive!!")
    }
  }

  private val act1: ActorRef = actorSystem.actorOf(Props(new Person("somjit")), "actorWithArgs")

  //  This way doesnt work, the `new` has to be done inside Props()
  //  private val somjit = new Person("somjit")
  //  private val act1: ActorRef = actorSystem.actorOf(Props(somjit), "actorWithArgs")

  println(act1.path.name)
  act1 ! "yo"


  /*
  Order of println:
  -----------------

    FirstActorSystem
    myWordCounter
    Ekdom Bhai!
    Akka hebby jinis bhaii!!
    actorWithArgs
    It's Alive!!

   ----------------
   Course:
   https://www.udemy.com/course/akka-essentials/learn/lecture/12418628#overview

   */


}
