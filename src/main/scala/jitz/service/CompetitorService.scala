package jitz.service

import com.google.inject.{Inject, Singleton}
import jitz.model.entities.{Competitor, CompetitorId, CompetitorModel, TournamentCompetitor, TournamentId}
import jitz.model.entities.Competitor._
import jitz.model.entities.Tournament._
import jitz.model.entities.TournamentCompetitor._
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

@Singleton
class CompetitorService @Inject() (db: Database) {

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

  def showCompetitor(competitorId: CompetitorId)(implicit ec: ExecutionContext): Future[Option[CompetitorModel]] = {
    val query = competitorTable.filter(_.id === competitorId).result
    for {
      comp <- db.run(query)
    } yield comp.headOption
  }

  def createCompetitor(firstName: String, lastName: String)(implicit ec: ExecutionContext): Future[CompetitorId] = {
    for {
      newId <- db.run(competitorTable += CompetitorModel(id = None, firstName = firstName, lastName = lastName))
    } yield CompetitorId(newId)
  }

//  def removeCompetitor(tournamentId: TournamentId, firstName: String, lastName: String)(implicit ec: ExecutionContext) = {
//    for {
//      competitors <- db.run(competitor.filter { c => c.firstName === firstName && c.lastName === lastName })
//
//    } yield Unit
//
//  }
}