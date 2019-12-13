package jitz.model.entities

import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

case class MatchScoreId(value: Long) extends MappedTo[Long]

case class MatchScoreModel(
  matchId: MatchId,
  scoredBy: CompetitorId,
  points: Int
)

class MatchScore(tag: Tag) extends Table[MatchScoreModel](tag, "competitor") {
  def matchId = column[MatchId]("match_id")
  def scoredBy = column[CompetitorId]("scored_by")
  def points = column[Int]("points")
  def * = (matchId, scoredBy, points) <> (MatchScoreModel.tupled, MatchScoreModel.unapply)
}

object MatchScore {
  val matchScoreTable = TableQuery[MatchScore]
}