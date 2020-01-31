package jitz.feature

import java.util.concurrent.Executors

import com.twitter.finagle.http.{Response, Status}
import com.twitter.finatra.http.EmbeddedHttpServer
import com.twitter.finatra.json.FinatraObjectMapper
import com.twitter.inject.server.FeatureTest
import jitz.server.JitzServer
import org.scalatest.WordSpec
import scala.concurrent._
import scala.concurrent.duration._

import scala.concurrent.{Await, ExecutionContext, Future}

trait BaseFeatureTest extends FeatureTest {

  implicit val objectMapper: FinatraObjectMapper = FinatraObjectMapper.create()
  implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(5))

  override protected def server = {
    new EmbeddedHttpServer(new JitzServer())
  }

  def get[R](path: String)(implicit ec: ExecutionContext): Future[R] = {
    Future {
      val body = server.httpGet(
        path = path,
        andExpect = Status.Ok
      ).contentString
      objectMapper.parse[R](body)
    }
  }

  def post[T, R](path: String, t: T)(implicit objectMapper: FinatraObjectMapper, ec: ExecutionContext): Future[R] = {
    Future {
      server.httpPostJson[R](
        path = path,
        headers = Map("Content-Type" -> "application/json"),
        postBody = objectMapper.convert(t)
      )
    }
  }

  def await[T](f: Future[T]): T = Await.result(f, Duration.Inf)
}
