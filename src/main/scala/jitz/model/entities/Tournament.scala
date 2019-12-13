package jitz.model.entities

import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

case class TournamentId(value: Long) extends MappedTo[Long]

case class TournamentModel(
  id: TournamentId,
  name: String
)

class Tournament(tag: Tag) extends Table[TournamentModel](tag, "tournament") {
  def id = column[TournamentId]("id")
  def name = column[String]("name")
  def * = (id, name) <> (TournamentModel.tupled, TournamentModel.unapply)
}

object Tournament {
  val tournament = TableQuery[Tournament]
}