package org.noamm.render

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.CacheDrawScope
import androidx.compose.ui.draw.drawWithCache
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.geometry.Size
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import org.noamm.models.*
import org.noamm.render.color.IColorPalette

@Composable
fun Grid(game: IGame, theme: IColorPalette) = Column(
    modifier = Modifier.drawWithCache {
        val drawGridLines = gridLines(game.gridSize, theme)

        onDrawWithContent {
            (game.status as? GameStatus.Won)?.let { drawWinHighlight(it.line, game.gridSize, theme) }
            drawContent()
            drawGridLines()
        }
    }
) {
    for (row in 0 until game.gridSize) Row {
        for (col in 0 until game.gridSize) Cell(game.get(row, col), theme) {
            game.play(row, col)
        }
    }
}

private fun CacheDrawScope.gridLines(gridSize: Int, theme: IColorPalette): DrawScope.() -> Unit {
    val cell = size.width / gridSize
    val width = Dimensions.GRID_LINE_WIDTH.toPx()
    val outlineTopLeft = Offset(width / 2, width / 2)
    val outlineSize = Size(size.width - width, size.height - width)
    val outline = Stroke(width)
    val lines = (1 until gridSize).flatMap { i ->
        val offset = i * cell
        listOf(
            Offset(offset, 0f) to Offset(offset, size.height),
            Offset(0f, offset) to Offset(size.width, offset)
        )
    }

    return {
        drawRect(theme.GRID_LINES, outlineTopLeft, outlineSize, style = outline)
        for ((start, end) in lines) drawLine(theme.GRID_LINES, start, end, width)
    }
}

private fun DrawScope.drawWinHighlight(line: WinLine, gridSize: Int, theme: IColorPalette) {
    val cell = size.width / gridSize
    val cellSize = Size(cell, cell)

    for ((row, col) in line.cells(gridSize)) {
        drawRect(theme.WIN_HIGHLIGHT, Offset(col * cell, row * cell), cellSize)
    }
}