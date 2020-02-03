package jitz.module

import com.fasterxml.jackson.databind.PropertyNamingStrategy
import com.twitter.finatra.json.modules.FinatraJacksonModule

/**
 * Disable some more annoying aspects of Jackson.
 */
object JacksonModule extends FinatraJacksonModule {
  override val propertyNamingStrategy: PropertyNamingStrategy = PropertyNamingStrategy.LOWER_CAMEL_CASE
}
