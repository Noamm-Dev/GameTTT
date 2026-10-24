package org.noamm.render

import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import org.noamm.render.color.IColorPalette

private const val NANOS_PER_SECOND = 1_000_000_000L

@Composable
fun FpsCounter(theme: IColorPalette, modifier: Modifier = Modifier) {
    var fps by remember { mutableIntStateOf(0) }

    LaunchedEffect(Unit) {
        var frames = 0
        var start = withFrameNanos { it }

        while (true) {
            val now = withFrameNanos { it }
            frames ++
            if (now - start < NANOS_PER_SECOND) continue

            fps = frames
            frames = 0
            start = now
        }
    }

    Text("$fps FPS", modifier, theme.TEXT, fontSize = Dimensions.FPS_SIZE)
}