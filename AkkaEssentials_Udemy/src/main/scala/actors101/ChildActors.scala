package actors101

import actors101.ChildActors.Parent.{CreateChild, TellChild}
import akka.actor.{Actor, ActorRef, ActorSelection, ActorSystem, Props}

object ChildActors extends App {

  object Parent {
    case class CreateChild(name: String)
    case class TellChild(message: String)
  }

  class Parent extends Actor {

    override def receive: Receive = {
      case CreateChild(name) => {
        println(s"${self.path} creating child")
        val child = context.actorOf(Props[Child], name) // opposed to ActorSystem.actorOf
        context.become(withChild(child))
      }
    }

    def withChild(child: ActorRef): Receive = {
      case TellChild(msg) => child forward msg
    }
  }

  class Child extends Actor {
    override def receive: Receive = {
      case msg => println(s"[Child: ${self.path}] I got $msg")
    }
  }

  import Parent._

  val actorSystem = ActorSystem("simpleParentChildSystem")
  val parent: ActorRef = actorSystem.actorOf(Props[Parent], "daParent")

  parent ! CreateChild("molly")
  parent ! TellChild("It's bed time")


  /*
  Actor Hierarchy:

  >  "/" - root level actor: Parent of both System and User level actors
  >      "/system" level actor : controls all system actors
  >      "/user" level actor : Controls all the actors we create

  if the root level actor dies, all actors die as well.
   */


  /**
    * Actor selection. Select actor via it's path
    */

    // if this path is not correct, we will get a 'deadletters' error
  private val selectedChild: ActorSelection = actorSystem.actorSelection("user/daParent/molly") // correct path
  selectedChild ! "I found you!"

  // private val selectedChild: ActorSelection = actorSystem.actorSelection("user/xyz/molly") // incorrect path

}
