package jitz.model.entities

import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

class TournamentCompetitor(tag: Tag) extends Table[(TournamentId, CompetitorId)](tag, "tourn_comp") {
  def tournamentId = column[TournamentId]("tournament_id")
  def competitorId = column[CompetitorId]("competitor_id")
  def * = (tournamentId, competitorId)
}

object TournamentCompetitor {
  val tournamentCompetitor = TableQuery[TournamentCompetitor]
}