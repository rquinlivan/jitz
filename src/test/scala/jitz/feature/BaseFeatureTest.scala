package jitz.feature

import com.twitter.finagle.http.{Response, Status}
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.inject.server.FeatureTest
import jitz.server.JitzServer
import org.scalatest.WordSpec

trait BaseFeatureTest extends FeatureTest {
  override protected def server = {
    new EmbeddedHttpServer(new JitzServer())
  }

  def get(path: String): Response = {
    server.httpGet(
      path = path,
      andExpect = Status.Ok
    )
  }

}
