package actors101

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ActorCapabilities extends App {

  class SimpleActor extends Actor {

    // context.self === self
    override def receive: Receive = {
      case "Hi" =>  self ! s"Hello there! ${sender.path.name}"
      case message: String => println(s"[${self.path.name}] $message")
      case number: Int => println(s"[${self.path.name}] $number")
      case SpecialMessage(contents) => println(s"[${self.path.name}] I have received a Special Message: $contents")
      case MessageToSelf(contents) => self ! contents // goes to `case message: String`
      case SayHiTo(actor) =>
        println(s"[${self.path.name}] Hi ${actor.path.name}")
        actor ! "Hi"
      case IntroduceSelf(actor) => actor ! s"Hi, I am ${actor.path.name}"
      case ForwardMessages(content, actor) => actor forward s"Fowarded message: ${content}"
    }
  }

  val actorSystem = ActorSystem("actorCapabilitiesDemo")
  val sa: ActorRef = actorSystem.actorOf(Props[SimpleActor], "simpleActor")

  /*
  1. Messages can be of any type
    a. Messages must be IMMUTABLE, big problems otherwise
    b. Messages must be SERIALIZABLE

  in practice, use case classes, and case objects
   */

  sa ! "Hello Bhai!"
  sa ! 42 // in these cases, `sender` is `deadletters`

  case class SpecialMessage(contents: String)
  sa ! SpecialMessage("Something Special")

  /*
    2. Actors have info about their context and themselves, through context.self
       context.self === self === `this` in OOP
   */

  case class MessageToSelf(contents: String)
  sa ! MessageToSelf("I'm talking to my self")

  /*
    3. Actors can REPLY to messages
   */
  val bob = actorSystem.actorOf(Props[SimpleActor], "bob")
  val alice = actorSystem.actorOf(Props[SimpleActor], "alice")

  case class SayHiTo(ref: ActorRef)
  bob ! SayHiTo(alice)

  case class IntroduceSelf(ref: ActorRef)
  sa ! IntroduceSelf(alice)

  /*
   4. Dead Letters
   */
  alice ! "Hi" // [alice] Hello there! deadLetters


  /*
   5. Forwarding Messages
   */

  case class ForwardMessages(content: String, actor: ActorRef)
  bob ! ForwardMessages("Forwarding like a boss!", alice)

}
