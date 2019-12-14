package jitz.controller.request

import com.fasterxml.jackson.annotation.JsonProperty
import com.twitter.finatra.validation.NotEmpty

case class NewCompetitorRequest(
  @JsonProperty("first_name") @NotEmpty firstName: String,
  @JsonProperty("last_name") @NotEmpty lastName: String
)
