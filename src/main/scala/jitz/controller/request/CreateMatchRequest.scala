package jitz.controller.request

/**
  * @author robertquinlivan
  * @since 2020-01-31.
  */
case class CreateMatchRequest(
                             competitor_id_a: Long,
                             competitor_id_b: Long,
                             tournament_id: Long
                             )
