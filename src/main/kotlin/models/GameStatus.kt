package org.noamm.models

sealed interface GameStatus {
    data object Playing: GameStatus
    data object Draw: GameStatus
    data class Won(val player: CellState, val line: WinLine): GameStatus
}