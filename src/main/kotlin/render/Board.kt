package org.noamm.render

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import org.noamm.models.IGame
import org.noamm.render.color.IColorPalette

@Composable
fun Board(game: IGame, theme: IColorPalette, onToggleTheme: () -> Unit) = Column(
    modifier = Modifier
        .background(theme.BACKGROUND)
        .padding(Dimensions.WINDOW_PADDING),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    FpsCounter(theme, Modifier.align(Alignment.Start))
    StatusText(game, theme)
    Grid(game, theme)

    Row(
        modifier = Modifier.padding(top = Dimensions.RESET_BUTTON_SPACING),
        horizontalArrangement = Arrangement.spacedBy(Dimensions.BUTTON_GAP)
    ) {
        val colors = ButtonDefaults.buttonColors(containerColor = theme.BUTTON, contentColor = theme.BUTTON_TEXT)

        Button(onClick = game::reset, colors = colors) {
            Text("Reset")
        }

        Button(onClick = onToggleTheme, colors = colors) {
            Text("Theme")
        }
    }
}