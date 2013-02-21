package spraybacon

import org.specs2.mutable.Specification
import spray.testkit.Specs2RouteTest
import spray.http._
import StatusCodes._


class MyServiceSpec extends Specification with Specs2RouteTest with SprayBaconService {
  def actorRefFactory = system

  "Service" should {

    "return a greeting for GET requests to the greeting path" in {
      Get("/getGreeting") ~> myRoute ~> check {
        entityAs[String] must contain("Hello Bacon")
      }
    }

    "leave GET requests to other paths unhandled" in {
      Get("/kermit") ~> myRoute ~> check {
        handled must beFalse
      }
    }

    "return a MethodNotAllowed error for PUT requests to the root path" in {
      Put() ~> sealRoute(myRoute) ~> check {
        status === MethodNotAllowed
        entityAs[String] === "HTTP method not allowed, supported methods: GET"
      }
    }
  }
}
