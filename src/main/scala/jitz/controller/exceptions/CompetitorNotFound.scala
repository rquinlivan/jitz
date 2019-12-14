package jitz.controller.exceptions

import com.google.inject.{Inject, Singleton}
import com.twitter.finagle.http.{Request, Response}
import com.twitter.finatra.http.exceptions.ExceptionMapper
import com.twitter.finatra.http.response.ResponseBuilder

class CompetitorNotFound (id: Long) extends Exception {
  override def getMessage: String = s"Can't find competitor with ID ${id}"
}

@Singleton
class CompetitorNotFoundMapper @Inject() (response: ResponseBuilder) extends ExceptionMapper[CompetitorNotFound] {
  override def toResponse(request: Request, throwable: CompetitorNotFound): Response = response.badRequest(s"Can't find competitor")
}
