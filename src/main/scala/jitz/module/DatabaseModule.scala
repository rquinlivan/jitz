package jitz.module

import com.google.inject.Provides
import com.twitter.inject.TwitterModule
import slick.jdbc.PostgresProfile
import slick.jdbc.PostgresProfile.api._

object DatabaseModule extends TwitterModule {

  @Provides
  def database(): Database = {
    val connectionUrl = "jdbc:postgresql://localhost/jitz?user=postgres&password=postgres"

    Database.forURL(connectionUrl, driver = "org.postgresql.Driver")
//    Database.forURL(url = "localhost:5432", prop = Map(
//      ("user", "postgres"),
//      ("password","postgres"),
//      ("database", "jitz")
//    ))
  }

}
