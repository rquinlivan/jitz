package jitz.controller.response

import jitz.model.entities.CompetitorModel

case class Competitor(
  id: Long,
  firstName: String,
  lastName: String
)

object Competitor {
  def apply(model: CompetitorModel): Competitor = Competitor(
    id = model.id.map(_.value).getOrElse(-1),
    firstName = model.firstName,
    lastName = model.lastName
  )
}