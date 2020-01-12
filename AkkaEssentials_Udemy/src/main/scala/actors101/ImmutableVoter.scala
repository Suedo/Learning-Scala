package actors101

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object ImmutableVoter extends App {


  object Citizen {
    case class Vote(candidate: String)
    case class VoteStatusReply(candidate: Option[String])
    case object VotesStatusRequest
  }

  class Citizen extends Actor {

    import Citizen._

    // candidate would be an otherwise mutable state variable
    // var candidate: Option[String] = None
    // but we don't need it, because we pass it as a param to `voted`
    override def receive: Receive = {
      case Vote(c) => context.become(voted(Some(c)))
      case VotesStatusRequest => sender() ! VoteStatusReply(None) // initial case
    }

    def voted(candidate: Option[String]): Receive = {
      case VotesStatusRequest => sender() ! VoteStatusReply(candidate)
    }
  }


  object VoteCounter {

    case class initiateCount(citizens: Set[ActorRef])

  }

  class VoteCounter extends Actor {

    import VoteCounter._
    import Citizen._

    override def receive: Receive = {
      case initiateCount(citizens) => {
        citizens.foreach(cref => cref ! VotesStatusRequest)
        context.become(awaitResponse(citizens, Map()))
      }
    }

    def awaitResponse(peopleNotVoted: Set[ActorRef], currentResults: Map[String, Int]): Receive = {

      // sender hasn't voted, keep asking him. can lead to inf loop
      case VoteStatusReply(None) => {
        sender() ! VotesStatusRequest
      }
      // when a proper vote is given
      case VoteStatusReply(Some(candidate)) => {

        // take sender out of list
        var newPeopleNotVoted = peopleNotVoted - sender()

        // update count for the candidate in results map
        val candidateVoteCount = currentResults.getOrElse(candidate, 0)
        val updatedResults = currentResults + (candidate -> (candidateVoteCount + 1))

        if (newPeopleNotVoted.isEmpty) {
          println(s"[VoteCounter] Vote Results: $updatedResults")
        }
        else {
          // still some voters left to process
          context.become(awaitResponse(newPeopleNotVoted, updatedResults))
        }
      }
    }
  }

  private val system = ActorSystem("VoterActorSystem")
  val v1 = system.actorOf(Props[Citizen], "v1")
  val v2 = system.actorOf(Props[Citizen], "v2")
  val v3 = system.actorOf(Props[Citizen], "v3")
  val v4 = system.actorOf(Props[Citizen], "v4")
  val v5 = system.actorOf(Props[Citizen], "v5")
  val v6 = system.actorOf(Props[Citizen], "v6")

  import Citizen._

  v1 ! Vote("Martin")
  v2 ! Vote("Jonas")
  v3 ! Vote("Roland")
  v4 ! Vote("Roland")

  import VoteCounter._

  val counter = system.actorOf(Props[VoteCounter], "VoteCounter")
  counter ! initiateCount(Set(v1, v2, v3, v4, v5, v6))

  v5 ! Vote("Roland")
  v6 ! Vote("Martin")

  /*
  Output:
  [VoteCounter] Vote Results: Map(Martin -> 2, Roland -> 3, Jonas -> 1)
   */

}

