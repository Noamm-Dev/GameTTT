package org.noamm.models

interface IGame {
    val gridSize: Int
    val currentPlayer: CellState
    val status: GameStatus

    operator fun get(row: Int, col: Int): CellState

    fun play(row: Int, col: Int)

    fun reset()
}