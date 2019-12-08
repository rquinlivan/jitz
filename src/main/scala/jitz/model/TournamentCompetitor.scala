package jitz.model

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

class TournamentCompetitor(tag: Tag) extends Table[(TournamentId, CompetitorId)](tag, "tourn_comp") {
  def tournamentId = column[TournamentId]("tournament_id")
  def competitorId = column[CompetitorId]("competitor_id")
  def * = (tournamentId, competitorId)
}

object TournamentCompetitor {
  val tournamentCompetitor = TableQuery[TournamentCompetitor]
}