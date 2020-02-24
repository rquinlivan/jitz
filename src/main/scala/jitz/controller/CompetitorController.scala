package jitz.controller

import com.google.inject.{Inject, Singleton}
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import jitz.controller.exceptions.CompetitorNotFound
import jitz.controller.request.{ModifyCompetitorRequest, NewCompetitorRequest}
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
      maybeCompetitor.map(Competitor(_)) match {
        case Some(c) => c
        case None => throw new CompetitorNotFound(id = id.value)
      }
    }
  }

  post("/competitor") { req: NewCompetitorRequest =>
    for {
      id <- competitorService.createCompetitor(firstName = req.firstName, lastName = req.lastName)
      competitor <- competitorService.showCompetitor(id)
    } yield competitor.map(_.toPresentation()).getOrElse(throw new Exception("Could not create competitor!"))
  }

  delete("/competitor/:id") { req: Request =>
    val id = CompetitorId(req.getLongParam("id"))
    competitorService.removeCompetitor(id)
  }

  put("/competitor/:id") { req: ModifyCompetitorRequest =>
    for {
      _ <- competitorService.modifyCompetitor(CompetitorId(req.id), firstName = req.firstName, lastName = req.lastName)
      competitor <- competitorService.showCompetitor(CompetitorId(req.id))
    } yield competitor.map(_.toPresentation()).getOrElse(throw new Exception("Error"))
  }

}
