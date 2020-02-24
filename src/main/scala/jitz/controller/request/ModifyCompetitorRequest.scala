package jitz.controller.request

import com.twitter.finatra.request.RouteParam

case class ModifyCompetitorRequest(
  firstName: Option[String] = None,
  lastName: Option[String] = None,
  @RouteParam id: Long
)
