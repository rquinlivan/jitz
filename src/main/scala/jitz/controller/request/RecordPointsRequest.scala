package jitz.controller.request

/**
  * @author robertquinlivan
  * @since 2019-12-13.
  */
case class RecordPointsRequest(
  match_id: Long,
  competitor_id: Long,
  points: Int
)
