package jitz.controller

import com.google.inject.{Inject, Singleton}
import com.twitter.finagle.http.Request
import com.twitter.finagle.thrift.Headers.Response
import com.twitter.finatra.http.Controller
import jitz.controller.request.RecordPointsRequest
import jitz.model.entities.{CompetitorId, MatchId}
import jitz.service.MatchService

import scala.concurrent.ExecutionContext

@Singleton
class MatchController @Inject() (matchService: MatchService)(implicit ec: ExecutionContext) extends Controller {
  get("/match/:id") { req: Request =>
    val id: MatchId = MatchId(req.getLongParam("id"))
    matchService.scoreForMatch(id)
  }

  post("/points") { req: RecordPointsRequest =>
    matchService.recordPoints(MatchId(req.match_id), CompetitorId(req.competitor_id), req.points).map { _ => response.ok }
  }
}
