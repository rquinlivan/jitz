package jitz.model.entities

import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

case class MatchId(value: Long) extends MappedTo[Long]

case class MatchModel(
  id: MatchId,
  competitorA: CompetitorId,
  competitorB: CompetitorId,
  tournamentId: TournamentId
)

class Match(tag: Tag) extends Table[MatchModel](tag, "competitor") {
  def id = column[MatchId]("id")
  def competitorA = column[CompetitorId]("comp_a")
  def competitorB = column[CompetitorId]("comp_b")
  def tournamentId = column[TournamentId]("tournament_id")
  def * = (id, competitorA, competitorB, tournamentId) <> (MatchModel.tupled, MatchModel.unapply)
}

object Match {
  val matchTable = TableQuery[Match]
}