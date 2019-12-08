package jitz.model

import slick.lifted.Tag
import slick.jdbc.PostgresProfile.api._

case class CompetitorId(value: Long) extends MappedTo[Long]

case class CompetitorModel(
  id: CompetitorId,
  firstName: String,
  lastName: String
)

class Competitor(tag: Tag) extends Table[CompetitorModel](tag, "competitor") {
  def id = column[CompetitorId]("id")
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")
  def * = (id, firstName, lastName) <> (CompetitorModel.tupled, CompetitorModel.unapply)
}

object Competitor {
  val competitor = TableQuery[Competitor]
}