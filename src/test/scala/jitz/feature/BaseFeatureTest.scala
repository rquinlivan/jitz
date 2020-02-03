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

import io.circe._
import io.circe.parser._
import io.circe.generic.auto._
import io.circe.syntax._

trait BaseFeatureTest extends FeatureTest {

  implicit val ec: ExecutionContext = ExecutionContext.fromExecutorService(Executors.newFixedThreadPool(5))
  implicit val objectMapper: FinatraObjectMapper = server.mapper

  override protected def server = {
    new EmbeddedHttpServer(new JitzServer())
  }

  def get[R](path: String)(implicit ec: ExecutionContext, decoder: Decoder[R]): Future[R] = {
    Future {
      val body = server.httpGet(
        path = path,
        andExpect = Status.Ok
      ).contentString

      decode[R](body) match {
        case Right(value) => value
        case Left(err)  => throw new RuntimeException(err) // Todo
      }
    }
  }

  def post[T, R](path: String, t: T)(implicit ec: ExecutionContext, encoder: Encoder[T], decoder: Decoder[R]): Future[R] = {
    Future {
      val json = t.asJson.toString()
      val response = server.httpPost(
        path = path,
        headers = Map("Content-Type" -> "application/json"),
        postBody = json
      ).contentString
      decode[R](response) match {
        case Right(value) => value
        case Left(err) => throw new RuntimeException(err)
      }
    }
  }

  def await[T](f: Future[T]): T = Await.result(f, Duration.Inf)
}
