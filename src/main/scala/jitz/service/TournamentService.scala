package jitz.service

import com.google.inject.{Inject, Singleton}
import jitz.model.entities.{Tournament, TournamentId, TournamentModel}
import slick.jdbc.PostgresProfile.api._

import scala.concurrent.{ExecutionContext, Future}

/**
  * @author robertquinlivan
  * @since 2020-01-31.
  */
@Singleton
class TournamentService @Inject() (db: Database)  {
  def createTournament(name: String): Future[Int] = {
    val insert = Tournament.tournament.forUpdate += TournamentModel(TournamentId(-1), name)
    db.run(insert)
  }

  def getTournament(tournanmentId: TournamentId)(implicit ec: ExecutionContext): Future[Option[TournamentModel]] = {
    val query = Tournament.tournament.filter(t => t.id === tournanmentId).result
    db.run(query).map { result => result.headOption }
  }
}
