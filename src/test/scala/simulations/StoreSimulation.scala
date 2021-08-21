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
  val scn = scenario("Get all stores")
    .exec(http("get all user details")
      .get("/rvy/api/um/v1/stores")
      .check(status is 200)
	.exec(http("Post store")
      .post("/rvy/api/um/v1/user")
      .body(RawFileBody(filePath = "./src/test/resources/bodies/addStore.json")).asJson
      .header(name="content-type",value = "application/json")
      .check(status is 200))
      .exec(http("Get User By Id")
       .get("/rvy/api/um/v1/user/1620")
      .header(name="content-type",value = "application/json")
      .check(status is 200)
     )
  //setup
  setUp(scn.inject(atOnceUsers(10))).protocols(httpConf)


}
