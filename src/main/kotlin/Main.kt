package org.noamm

import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.unit.DpSize
import androidx.compose.ui.window.*
import org.noamm.config.PropertiesConfig
import org.noamm.logic.Game
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
        val config = remember(::PropertiesConfig)
        var theme by remember { mutableStateOf(config.theme) }

        Board(remember(::Game), theme.palette) {
            config.theme = theme.next()
            theme = config.theme
        }
    }
}