package org.noamm.render

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material3.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.graphics.RectangleShape
import org.noamm.logic.ai.GameMode
import org.noamm.models.IGame
import org.noamm.render.color.IColorPalette

@Composable
fun Board(
    game: IGame,
    theme: IColorPalette,
    themeName: String,
    onToggleTheme: () -> Unit,
    onToggleMode: () -> Unit,
    onToggleSide: () -> Unit
) = Column(
    modifier = Modifier
        .background(theme.BACKGROUND)
        .padding(Dimensions.WINDOW_PADDING),
    horizontalAlignment = Alignment.CenterHorizontally
) {
    FpsCounter(theme, Modifier.align(Alignment.Start))
    StatusText(game, theme)
    Grid(game, theme)

    Row(
        modifier = Modifier
            .padding(top = Dimensions.RESET_BUTTON_SPACING)
            .width(Dimensions.CELL_SIZE * game.gridSize),
        horizontalArrangement = Arrangement.spacedBy(Dimensions.BUTTON_GAP)
    ) {
        val colors = ButtonDefaults.buttonColors(containerColor = theme.BUTTON, contentColor = theme.BUTTON_TEXT)
        val padding = PaddingValues(Dimensions.BUTTON_PADDING, ButtonDefaults.ContentPadding.calculateTopPadding())

        Button(
            onClick = onToggleTheme,
            modifier = Modifier.weight(1f),
            colors = colors,
            shape = RectangleShape,
            contentPadding = padding
        ) {
            Text("Theme: $themeName")
        }

        Button(
            onClick = game::reset,
            colors = colors,
            shape = RectangleShape,
            contentPadding = padding
        ) {
            Text("Reset")
        }

        Row(
            Modifier.weight(1f),
            horizontalArrangement = Arrangement.spacedBy(Dimensions.BUTTON_GAP)
        ) {
            Button(
                onClick = onToggleMode,
                modifier = Modifier.width(Dimensions.MODE_BUTTON_WIDTH),
                colors = colors,
                shape = RectangleShape,
                contentPadding = padding
            ) {
                Text(game.mode.label)
            }

            // hiding instead of removing in pvp because it will change the window size
            Button(
                onClick = onToggleSide,
                modifier = Modifier
                    .width(Dimensions.SIDE_BUTTON_WIDTH)
                    .alpha(if (game.mode == GameMode.PVP) 0f else 1f),
                enabled = game.mode != GameMode.PVP,
                colors = colors,
                shape = RectangleShape,
                contentPadding = padding
            ) {
                Text("AI: ${game.aiSide.name}")
            }
        }
    }
}