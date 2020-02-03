package jitz.feature

import com.twitter.finagle.http.Status

class HealthCheckFeatureTest extends BaseFeatureTest {
  test("Calling /health") {
    server.httpGet(
      path = "/health",
      andExpect = Status.Ok
    ).contentString shouldBe "imok!"
  }
}
