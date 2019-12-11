package jitz.app

import com.twitter.inject.Logging
import jitz.server.JitzServer

object JitzApp extends JitzServer with Logging {
  info("Starting up Jitz server...")
}
