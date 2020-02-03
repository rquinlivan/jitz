package jitz.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.twitter.finatra.validation.NotEmpty

case class NewCompetitorRequest(
  @JsonProperty("firstName") @NotEmpty firstName: String,
  @JsonProperty("lastName") @NotEmpty lastName: String
)
