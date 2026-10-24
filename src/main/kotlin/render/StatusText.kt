package org.noamm.render

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import org.noamm.models.GameStatus
import org.noamm.models.IGame
import org.noamm.render.color.IColorPalette

@Composable
fun IGame.StatusText(theme: IColorPalette) = Text(
    when (val status = status) {
        GameStatus.Playing -> "${currentPlayer.name}'s turn"
        GameStatus.Draw -> "Draw!"
        is GameStatus.Won -> "${status.player.name} wins!"
    },
    color = theme.TEXT,
    fontSize = Dimensions.STATUS_SIZE
)