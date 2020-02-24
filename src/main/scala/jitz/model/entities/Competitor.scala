package jitz.model.entities

import jitz.controller.response.{Competitor => Presentation}
import slick.jdbc.PostgresProfile.api._
import slick.lifted.Tag

case class CompetitorId(value: Long) extends MappedTo[Long]

object CompetitorId { val NewRecord = CompetitorId(-1) }

case class CompetitorModel(
  id: Option[CompetitorId],
  firstName: String,
  lastName: String
) {
  def toPresentation(): Presentation = {
    Presentation(id = id.map(_.value).getOrElse(-1), firstName = firstName, lastName = lastName)
  }
}

class Competitor(tag: Tag) extends Table[CompetitorModel](tag, "competitor") {
  def id = column[CompetitorId]("id", O.PrimaryKey, O.AutoInc)
  def firstName = column[String]("first_name")
  def lastName = column[String]("last_name")
  def * = (id.?, firstName, lastName) <> (CompetitorModel.tupled, CompetitorModel.unapply)
}

object Competitor {
  val competitorTable = TableQuery[Competitor]
}