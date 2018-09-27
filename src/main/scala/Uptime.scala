import akka.actor.{Actor, ActorSystem}
import akka.http.scaladsl.Http
import akka.http.scaladsl.model.HttpRequest

import scala.util._

case class Ping(host: String)

class Uptime(implicit system: ActorSystem) extends Actor {

  implicit val ec = system.dispatcher

  override def receive: Receive = {
    case Ping(host) =>
      Http().singleRequest(HttpRequest(uri = host)).onComplete {
        case Success(value) => println("success")
        case Failure(exception) => println("failure")
      }
  }
}
