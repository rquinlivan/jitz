package jitz.feature

class HealthCheckFeatureTest extends BaseFeatureTest {
  test("Calling /health") {
    get("/health").contentString shouldBe "imok!"
  }
}
