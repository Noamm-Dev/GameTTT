package org.noamm.models

import org.noamm.logic.ai.GameMode

interface IGame {
    val gridSize: Int
    val mode: GameMode
    val aiSide: CellState
    val currentPlayer: CellState
    val status: GameStatus

    operator fun get(row: Int, col: Int): CellState

    fun play(row: Int, col: Int)

    fun reset()
}
