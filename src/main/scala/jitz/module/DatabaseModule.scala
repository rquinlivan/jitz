package jitz.module

import com.twitter.inject.TwitterModule
import slick.jdbc.PostgresProfile.api._

object DatabaseModule extends TwitterModule {

  def database(): Database = {
    Database.forURL("localhost", "postgres", "postgres")
  }

}
