/*
 * Copyright (C) 2021 Square, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package app.cash.paparazzi.accessibility

import android.view.View
import java.awt.Color

internal object RenderSettings {
  const val DEFAULT_RENDER_ALPHA = 40
  val DEFAULT_RENDER_COLORS = listOf(
    Color.RED,
    Color.GREEN,
    Color.BLUE,
    Color.YELLOW,
    Color.ORANGE,
    Color.MAGENTA,
    Color.CYAN,
    Color.PINK
  )
  val DEFAULT_TEXT_COLOR: Color = Color.BLACK
  val DEFAULT_DESCRIPTION_BACKGROUND_COLOR: Color = Color.WHITE
  const val DEFAULT_TEXT_SIZE: Float = 10f
  const val DEFAULT_RECT_SIZE: Int = 16

  private val colorMap = mutableMapOf<View, Color>()

  fun getColor(view: View): Color {
    return colorMap.getOrPut(view) {
      nextColor(view).withAlpha(DEFAULT_RENDER_ALPHA)
    }
  }

  private fun nextColor(view: View): Color {
    val viewId = if (view.id != View.NO_ID) view.id else System.identityHashCode(view)
    return DEFAULT_RENDER_COLORS[viewId % DEFAULT_RENDER_COLORS.size]
  }

  internal fun Color.toColorInt(): Int =
    android.graphics.Color.argb(alpha, red, green, blue)

  internal fun Color.withAlpha(alpha: Int): Color {
    return Color(red, green, blue, alpha)
  }
}