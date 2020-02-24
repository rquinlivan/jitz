package jitz.feature

import com.twitter.finagle.http.Status
import jitz.controller.request.{ModifyCompetitorRequest, NewCompetitorRequest}
import jitz.model.entities.CompetitorModel
import io.circe.generic.auto._
import io.circe.syntax._
import jitz.controller.response.Competitor

import scala.concurrent.Future

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

  test("Get competitor") {
    val newComp = NewCompetitorRequest("John", "Wayne")
    await {
      for {
        created <- post[NewCompetitorRequest, Competitor]("/competitor", newComp)
        fetched <- get[Competitor](s"/competitor/${created.id}")
      } yield {
        fetched.firstName shouldBe "John"
        fetched.lastName shouldBe "Wayne"
        fetched.id shouldBe created.id
      }
    }
  }

  test("Delete competitor") {
    val newComp = NewCompetitorRequest("John", "Wayne")
    await {
      for {
        created <- post[NewCompetitorRequest, Competitor]("/competitor", newComp)
        deleted <- delete[Boolean](s"/competitor/${created.id}")
      } yield {
        deleted shouldBe true

        server.httpGet(
          path = s"/competitor/${created.id}",
          andExpect = Status.InternalServerError // TODO: should return useful error messages
        )
      }
    }
  }

  test("Modify a competitor") {
    val newComp = NewCompetitorRequest("John", "Denver")
    await {
      for {
        created <- post[NewCompetitorRequest, Competitor]("/competitor", newComp)
        modComp = ModifyCompetitorRequest(firstName = Some("Jack"), lastName = None, id = created.id)
        modified <- put[ModifyCompetitorRequest, Competitor](s"/competitor/${created.id}", modComp)
      } yield {
        created.firstName shouldBe "John"
        created.lastName shouldBe "Denver"

        modified.firstName shouldBe "Jack"
        modified.lastName shouldBe "Denver"

        created.id shouldBe modified.id
      }
    }

  }

}