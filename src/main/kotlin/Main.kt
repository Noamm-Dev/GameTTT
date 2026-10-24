package org.noamm

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import org.noamm.config.Config
import org.noamm.logic.Game
import org.noamm.models.CellState
import org.noamm.render.Board

fun main() = application {
    Window(
        title = "Tic Tac Toe",
        resizable = false,
        focusable = true,
        state = rememberWindowState(
            size = DpSize.Unspecified,
            position = WindowPosition(Alignment.Center)
        ),
        onCloseRequest = ::exitApplication
    ) {
        val config = remember(::Config)
        var theme by remember { mutableStateOf(config.theme) }
        var mode by remember { mutableStateOf(config.mode) }
        var aiSide by remember { mutableStateOf(config.aiSide) }

        Board(
            game = remember(mode, aiSide) { Game(mode, aiSide) },
            theme = theme.palette,
            themeName = theme.label,
            onToggleTheme = {
                config.theme = theme.next()
                theme = config.theme
            },
            onToggleMode = {
                config.mode = mode.next()
                mode = config.mode
            },
            onToggleSide = {
                config.aiSide = if (aiSide == CellState.X) CellState.O else CellState.X
                aiSide = config.aiSide
            }
        )
    }
}