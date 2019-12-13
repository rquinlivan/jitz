package jitz.service

import com.google.inject.{Inject, Singleton}
import jitz.model.entities.{TournamentCompetitor, TournamentId}
import jitz.model.entities.Competitor._
import jitz.model.entities.Tournament._
import jitz.model.entities.TournamentCompetitor._
import jitz.model.entities.{CompetitorModel, TournamentId}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CompetitorsService @Inject() (db: Database) {

  def listCompetitors(tournamentId: TournamentId)(implicit ec: ExecutionContext): Future[Seq[CompetitorModel]] = {
    val query = tournamentCompetitor
      .filter(_.tournamentId === tournamentId)
      .join(competitorTable).on(_.competitorId === _.id)
      .map({ case (_, c) => c })
      .result
    for {
      competitors <- db.run(query)
    } yield competitors
  }

//  def removeCompetitor(tournamentId: TournamentId, firstName: String, lastName: String)(implicit ec: ExecutionContext) = {
//    for {
//      competitors <- db.run(competitor.filter { c => c.firstName === firstName && c.lastName === lastName })
//
//    } yield Unit
//
//  }
}
