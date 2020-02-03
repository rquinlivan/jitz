package jitz.feature

import jitz.controller.request.NewCompetitorRequest
import jitz.model.entities.CompetitorModel
import io.circe.generic.auto._
import io.circe.syntax._
import jitz.controller.response.Competitor

class CompetitorFeatureTest extends BaseFeatureTest {

  test("Creating a competitor") {
    val newComp = NewCompetitorRequest("John", "Smith")
    await {
      for {
        response <- post[NewCompetitorRequest, Competitor]("/competitor", newComp)
      } yield {
        response.firstName shouldBe "John"
        response.lastName shouldBe "Smith"
      }
    }
  }

}