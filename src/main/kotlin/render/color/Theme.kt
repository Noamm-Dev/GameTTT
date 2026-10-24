package org.noamm.render.color

import org.noamm.render.color.impl.DefaultTheme
import org.noamm.render.color.impl.NordTheme

enum class Theme(val label: String, val palette: IColorPalette) {
    DEFAULT("Default", DefaultTheme),
    NORD("Nord", NordTheme);

    fun next() = entries[(ordinal + 1) % entries.size]
}