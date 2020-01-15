package actors101

import akka.actor.{Actor, ActorRef, ActorSystem, Props}

object DistributedWordCounter extends App {

  /*
  1. FLOW: WordCounterMaster > (countTask) > WordCounterWorker [nth] > (counterReply) > WordCounterMaster
  2. There will be 'n' WordCounterWorker children, RoundRobin-ed by WordCounterMaster
   */

  object WordCounterMaster {
    case class Initialize(numOfWorkers: Int)
    case class Count(str: String)
    case class CountReply(id: Int, word: String, wordCount: Int)
  }
  class WordCounterMaster extends Actor {
    import WordCounterMaster._
    import WordCounterWorker._

    override def receive: Receive = {
      case Initialize(numOfWorkers) => {
        val workers: Seq[ActorRef] =
          for { i <- 0 to numOfWorkers} yield context.actorOf(Props[WordCounterWorker], s"w$i")
        context.become(withChildren(workers, 0))
      }
    }

    def withChildren(workers: Seq[ActorRef], currentWorkerIdx: Int): Receive = {
      case Count(str) => {
        val worker = workers(currentWorkerIdx)
        worker ! CountTask(currentWorkerIdx, str)
        context.become(withChildren(workers, (currentWorkerIdx + 1) % workers.length))
      }
      case CountReply(id, str, count) => {
        println(s"Worker $id: count of [$str] = $count")
      }
    }
  }

  object WordCounterWorker {
    case class CountTask(workerId: Int, str: String)
  }
  class WordCounterWorker extends Actor {
    import WordCounterMaster._
    import WordCounterWorker._

    override def receive: Receive = {
      case CountTask(id, str) => sender() ! CountReply(id, str, str.split(" ").length)
    }
  }

  import WordCounterMaster._

  private val system = ActorSystem("DistWordCOunter")
  val master = system.actorOf(Props[WordCounterMaster], "master")

  master ! Initialize(3)
  private val words: List[String] =
    "Akka is very different, needs a lot of practice" ::
      "Scala FTW!" :: "Martin for president" ::
      "Lol! Ki bolchis bhai!!" :: "Poush Sankrantir pithe puli khete chai" ::
      "but khabo chicken ar fried rice" :: Nil
  (0 until words.length) foreach (i => master ! Count(words(i)))

}
