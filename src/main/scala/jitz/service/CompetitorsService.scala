package jitz.service

import com.google.inject.{Inject, Singleton}
import jitz.model.{Competitor, CompetitorModel, TournamentCompetitor, TournamentId}
import jitz.model.Competitor._
import jitz.model.Tournament._
import jitz.model.TournamentCompetitor._
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CompetitorsService @Inject() (db: Database) {

  def listCompetitors(tournamentId: TournamentId)(implicit ec: ExecutionContext): Future[Seq[CompetitorModel]] = {
    val query = tournamentCompetitor
      .filter(_.tournamentId === tournamentId)
      .join(competitor).on(_.competitorId === _.id)
      .map(tup => tup._2)
      .result
    for {
      competitors <- db.run(query)
    } yield competitors
  }

  def removeCompetitor(tournamentId: TournamentId, firstName: String, lastName: String)(implicit ec: ExecutionContext) = {
    for {
      competitors <- db.run(competitor.filter { c => c.firstName === firstName && c.lastName === lastName })

    } yield Unit

  }
}
