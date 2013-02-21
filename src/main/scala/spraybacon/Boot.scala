package spraybacon

import spray.can.server.SprayCanHttpServerApp
import akka.actor.Props


object Boot extends App with SprayCanHttpServerApp {

  // create and start our service actor
  val service = system.actorOf(Props[SprayBaconActor], "form-keeper-service")

  // create a new HttpServer using our handler tell it where to bind to
  newHttpServer(service) ! Bind(interface = "localhost", port = 8020)

}
