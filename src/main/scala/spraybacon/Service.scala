package spraybacon

import akka.actor.Actor
import spray.routing._


class SprayBaconActor extends Actor with SprayBaconService {

  // the HttpService trait defines only one abstract member, which
  // connects the services environment to the enclosing actor or test
  def actorRefFactory = context

  // this actor only runs our route, but you could add
  // other things here, like request stream processing
  // or timeout handling
  def receive = runRoute(myRoute)
}


trait SprayBaconService extends HttpService {

  val myRoute =
    getFromResourceDirectory("webapp") ~
      path("") {
        getFromResource("webapp/main.html")
      } ~
      path("getGreeting") {
        get {
          complete("Hello Bacon from Spray!")
        }

      }

}
