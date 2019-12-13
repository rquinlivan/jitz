package jitz.module

import java.util.concurrent.Executors

import com.google.inject.Provides
import com.twitter.inject.TwitterModule

import scala.concurrent.ExecutionContext

/**
  * @author robertquinlivan
  * @since 2019-12-13.
  */
object ConfigurationModule extends TwitterModule {

  @Provides
  def executionContext(): ExecutionContext = {
    val es = Executors.newFixedThreadPool(10)
    ExecutionContext.fromExecutorService(es)
  }

}
