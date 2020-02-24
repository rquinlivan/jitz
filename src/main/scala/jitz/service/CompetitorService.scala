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
      newId <- db.run {
        competitorTable returning competitorTable.map(_.id) += CompetitorModel(
          id = None,
          firstName = firstName,
          lastName = lastName
        )
      }
    } yield newId
  }

  def removeCompetitor(competitorId: CompetitorId)(implicit ec: ExecutionContext): Future[Boolean] = {
    for {
      _ <- db.run(competitorTable.filter(_.id === competitorId).delete)
    } yield true
  }

  def modifyCompetitor(competitorId: CompetitorId, firstName: Option[String], lastName: Option[String])
    (implicit ec: ExecutionContext): Future[Boolean] = {
    for {
      _ <- db.run {
        val query = competitorTable.filter(_.id === competitorId)

        (firstName, lastName) match {
          case (Some(f), None) =>       query.map(c => (c.firstName)).update(f)
          case (Some(f), Some(l)) =>    query.map(c => (c.firstName, c.lastName)).update((f,l))
          case (None, Some(l)) =>       query.map(c => (c.lastName)).update(l)
          case _ =>                     DBIO.successful(true)
        }
      }
    } yield true
  }
}
