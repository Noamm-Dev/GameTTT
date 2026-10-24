package org.noamm.render

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import org.noamm.models.GameStatus
import org.noamm.models.IGame
import org.noamm.render.color.IColorPalette

@Composable
fun StatusText(game: IGame, theme: IColorPalette) = Text(
    modifier = Modifier.padding(bottom = 12.dp),
    text = when (val status = game.status) {
        GameStatus.Playing -> "${game.currentPlayer.name}'s turn"
        GameStatus.Draw -> "Draw!"
        is GameStatus.Won -> "${status.player.name} wins!"
    },
    color = theme.TEXT,
    fontSize = Dimensions.STATUS_SIZE
)