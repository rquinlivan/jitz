package jitz.server

import com.google.inject.Module
import com.twitter.finatra.http.HttpServer
import com.twitter.finatra.http.routing.HttpRouter
import jitz.controller.{CompetitorController, HealthCheckController, MatchController}
import jitz.module.{ConfigurationModule, DatabaseModule, JacksonModule}

class JitzServer extends HttpServer {

  override protected def configureHttp(router: HttpRouter): Unit = {
    router
      .add[HealthCheckController]
      .add[MatchController]
      .add[CompetitorController]
  }

  override def jacksonModule: Module = JacksonModule

  override val modules: Seq[Module] = Seq(
    DatabaseModule,
    ConfigurationModule
  )

}
