package org.noamm.render

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.StrokeCap
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.graphics.drawscope.Stroke
import org.noamm.models.CellState
import org.noamm.render.color.IColorPalette

@Composable
fun Cell(state: CellState, theme: IColorPalette, onClick: () -> Unit) {
    Box(
        modifier = Modifier
            .size(Dimensions.CELL_SIZE)
            .clickable(interactionSource = null, indication = null, onClick = onClick)
            .drawBehind { drawMark(state, theme) }
    )
}

private fun DrawScope.drawMark(state: CellState, theme: IColorPalette) {
    val near = Dimensions.MARK_INSET.toPx()
    val far = size.width - near
    val width = Dimensions.MARK_LINE_WIDTH.toPx()

    when (state) {
        CellState.X -> {
            drawLine(theme.X_MARK, Offset(near, near), Offset(far, far), width, StrokeCap.Round)
            drawLine(theme.X_MARK, Offset(far, near), Offset(near, far), width, StrokeCap.Round)
        }

        CellState.O -> drawCircle(theme.O_MARK, size.width / 2 - near, style = Stroke(width))
        CellState.EMPTY -> Unit
    }
}