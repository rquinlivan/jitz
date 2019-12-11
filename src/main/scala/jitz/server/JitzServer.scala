package jitz.server

import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter
import jitz.controller.HealthCheckController
import jitz.module.DatabaseModule

class JitzServer extends HttpServer {

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .add[HealthCheckController]
  }

}
