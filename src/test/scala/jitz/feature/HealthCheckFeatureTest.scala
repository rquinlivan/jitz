package jitz.feature

class HealthCheckFeatureTest extends BaseFeatureTest {
  test("Calling /health") {
    await {
      for {
        res <- get[String]("/health")
      } yield {
        res shouldBe "imok!"
      }
    }
  }
}
