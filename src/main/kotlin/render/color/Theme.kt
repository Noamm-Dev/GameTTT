package org.noamm.render.color

import org.noamm.render.color.impl.DefaultTheme
import org.noamm.render.color.impl.NordTheme

enum class Theme(val palette: IColorPalette) {
    DEFAULT(DefaultTheme),
    NORD(NordTheme);

    fun next() = entries[(ordinal + 1) % entries.size]
}