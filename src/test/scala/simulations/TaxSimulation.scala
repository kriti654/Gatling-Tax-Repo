package simulations

import io.gatling.core.scenario.Simulation
import io.gatling.core.Predef._
import io.gatling.http.Predef._
class TaxTestSimulation extends Simulation {

  //conf
  val value_conf = http.baseUrl("http://localhost:8082")
    .header("Accept",value="application/json")
    .header(name="content-type", value ="application/json")


  //scenario
  val scn = scenario("Insert tax")
    .exec(http("Post tax")
      .post("/rvy/api/um/v1/tax")
      .body(RawFileBody(filePath = "./src/test/resources/bodies/addTax.json")).asJson
      .header(name="content-type",value = "application/json")
      .check(status is 200))
      .exec(http("Get Tax By Id")
       .get("/rvy/api/um/v1/tax/2")
      .header(name="content-type",value = "application/json")
      .check(status is 200))
      .exec(http("get all tax details")
      .get("/rvy/api/um/v1/taxes")
      .check(status is 200)   
    )



  //setup
  setUp(scn.inject(atOnceUsers(users=10))).protocols(value_conf)



}
