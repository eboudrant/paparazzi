package app.cash.paparazzi.sample

import androidx.compose.runtime.Composable
import app.cash.paparazzi.DeviceConfig
import app.cash.paparazzi.Paparazzi
import org.junit.rules.TestRule
import org.junit.runner.Description
import org.junit.runners.model.Statement

class MyPaparazziRule : TestRule {

  private val normal = Paparazzi(
    deviceConfig = DeviceConfig.PIXEL,
  )

  private val scaled = Paparazzi(
    deviceConfig = DeviceConfig.PIXEL
      .copy(fontScale = 2f),
  )

  override fun apply(base: Statement, description: Description): Statement {
    val normalStatement = normal.apply(base, description)
    val scaledStatement = scaled.apply(base, description)
    return object : Statement() {
      override fun evaluate() {
        normalStatement.evaluate()
        scaledStatement.evaluate()
      }
    }
  }

  fun snapshot(
    name: String? = null,
    content: @Composable () -> Unit,
  ) {
    normal.snapshot("${name.orEmpty()}-normal", content)
    scaled.snapshot("${name.orEmpty()}-scaled", content)
  }
}
