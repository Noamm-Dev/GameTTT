package org.noamm.config

import org.noamm.logic.ai.GameMode
import org.noamm.models.CellState
import org.noamm.render.color.Theme

interface IConfig {
    var theme: Theme
    var mode: GameMode
    var aiSide: CellState
}