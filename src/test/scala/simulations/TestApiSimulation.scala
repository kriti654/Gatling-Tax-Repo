package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._

class TestApiSimulation extends Simulation{

  //http conf
  val httpConf = http.baseUrl("http://localhost:8082")
    .header("Accept",value="application/json")
    .header(name="content-type", value ="application/json")

  //scenario
  val scn = scenario("Get all taxes")
    .exec(http("get all tax details")
      .get("/rvy/api/um/v1/taxes")
      .check(status is 200)

     )
  //setup
  setUp(scn.inject(atOnceUsers(10))).protocols(httpConf)


}
