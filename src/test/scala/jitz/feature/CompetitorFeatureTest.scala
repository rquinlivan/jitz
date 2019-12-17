package jitz.feature

import jitz.controller.request.NewCompetitorRequest
import jitz.model.entities.CompetitorModel

class CompetitorFeatureTest extends BaseFeatureTest {

  test("Creating a competitor") {
    val newComp = NewCompetitorRequest(
      firstName = "John",
      lastName = "Smith"
    )
    await {
      for {
        response <- post[NewCompetitorRequest, CompetitorModel]("/competitor", newComp)
      } yield {
        response.firstName shouldBe "John"
        response.lastName shouldBe "Smith"
      }
    }
  }

}
