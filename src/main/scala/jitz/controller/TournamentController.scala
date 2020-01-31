package jitz.controller

import com.google.inject.Inject
import com.twitter.finagle.http.Request
import com.twitter.finatra.http.Controller
import jitz.model.entities.TournamentId
import jitz.service.TournamentService

import scala.concurrent.ExecutionContext

/**
  * @author robertquinlivan
  * @since 2020-01-31.
  */
class TournamentController @Inject() (tournamentService: TournamentService)(implicit ec: ExecutionContext) extends Controller {

  get("/tournament/:id") { req: Request =>
    val tournId  = TournamentId(req.getLongParam("id"))
    tournamentService.getTournament(tournId)
  }

}
