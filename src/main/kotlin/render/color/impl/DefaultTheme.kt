package org.noamm.render.color.impl

import androidx.compose.ui.graphics.Color
import org.noamm.render.color.IColorPalette

object DefaultTheme: IColorPalette {
    override val BACKGROUND = Color(0xFFFFFFFF)
    override val TEXT = Color(0xFF000000)
    override val BUTTON = Color(0xFF6750A4)
    override val BUTTON_TEXT = Color(0xFFFFFFFF)
    override val GRID_LINES = Color(0xFF000000)
    override val X_MARK = Color(0xFFE53935)
    override val O_MARK = Color(0xFF1E88E5)
    override val WIN_HIGHLIGHT = Color(0xFF6FB371)
}