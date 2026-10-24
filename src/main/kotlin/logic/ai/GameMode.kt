package org.noamm.logic.ai

import org.noamm.models.IMoveStrategy

enum class GameMode(val label: String, val strategy: IMoveStrategy? = null) {
    PVP("PvP"),
    EASY("Easy", RandomStrategy),
    NORMAL("Normal", NormalStrategy),
    HARD("Hard", MinimaxStrategy);

    fun next() = entries[(ordinal + 1) % entries.size]
}
