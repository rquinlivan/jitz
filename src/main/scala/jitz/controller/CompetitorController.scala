package jitz.controller

import com.google.inject.{Inject, Singleton}
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import jitz.controller.exceptions.CompetitorNotFound
import jitz.controller.request.NewCompetitorRequest
import jitz.controller.response.Competitor
import jitz.model.entities.CompetitorId
import jitz.service.CompetitorService

import scala.concurrent.ExecutionContext

@Singleton
class CompetitorController @Inject() (competitorService: CompetitorService)(implicit ec: ExecutionContext) extends Controller {

  get("/competitor/:id") { req: Request =>
    val id = CompetitorId(req.getLongParam("id"))
    for {
      maybeCompetitor <- competitorService.showCompetitor(id)
    } yield {
      maybeCompetitor match {
        case None => throw new CompetitorNotFound(id = id.value)
      }
    }
  }

  post("/competitor") { req: NewCompetitorRequest =>
    for {
      id <- competitorService.createCompetitor(firstName = req.firstName, lastName = req.lastName)
      competitor <- competitorService.showCompetitor(id)
    } yield competitor.map { c =>
      Competitor(id = c.id.map(_.value).getOrElse(-1), firstName = c.firstName, lastName = c.lastName)
    }.getOrElse(throw new Exception("Could not create competitor!"))
  }

}
