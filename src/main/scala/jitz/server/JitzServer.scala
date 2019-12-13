package jitz.server

import com.google.inject.Module
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter
import jitz.controller.{HealthCheckController, MatchController}
import jitz.module.{ConfigurationModule, DatabaseModule}

class JitzServer extends HttpServer {

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .add[HealthCheckController]
      .add[MatchController]
  }

  override val modules: Seq[Module] = Seq(
    DatabaseModule,
    ConfigurationModule
  )

}
