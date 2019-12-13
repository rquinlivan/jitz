package jitz.module

import com.google.inject.Provides
import com.twitter.inject.TwitterModule
import slick.jdbc.PostgresProfile.api._

object DatabaseModule extends TwitterModule {

  @Provides
  def database(): Database = {
    Database.forURL("localhost:5432", "postgres", "postgres")
  }

}
