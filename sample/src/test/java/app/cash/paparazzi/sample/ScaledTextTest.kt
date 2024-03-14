package app.cash.paparazzi.sample

import androidx.compose.material.Text

import org.junit.Rule
import org.junit.Test

class ScaledTextTest {

  @get:Rule
  val paparazzi = MyPaparazziRule()

  @Test
  fun compositeItems() {
    paparazzi.snapshot {
      Text("Text")
    }
  }
}
