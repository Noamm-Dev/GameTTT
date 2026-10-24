package org.noamm.logic

import androidx.compose.runtime.*
import org.noamm.models.*

class Game(
    override val gridSize: Int = GRID_SIZE,
    private val winChecker: IWinChecker = CounterWinChecker(gridSize)
): IGame {
    private val cells = List(gridSize * gridSize) { CellState.EMPTY }.toMutableStateList()
    private var moves = 0

    override var currentPlayer by mutableStateOf(CellState.X)
        private set

    override var status by mutableStateOf<GameStatus>(GameStatus.Playing)
        private set

    override operator fun get(row: Int, col: Int) = cells[index(row, col)]

    override fun play(row: Int, col: Int) {
        if (status != GameStatus.Playing) return
        if (get(row, col) != CellState.EMPTY) return

        cells[index(row, col)] = currentPlayer
        moves ++

        val winLine = winChecker.record(row, col, currentPlayer)
        status = when {
            winLine != null -> GameStatus.Won(currentPlayer, winLine)
            moves == cells.size -> GameStatus.Draw
            else -> GameStatus.Playing
        }

        currentPlayer = if (currentPlayer == CellState.X) CellState.O else CellState.X
    }

    override fun reset() {
        cells.replaceAll { CellState.EMPTY }
        winChecker.reset()
        moves = 0
        currentPlayer = CellState.X
        status = GameStatus.Playing
    }

    private fun index(row: Int, col: Int) = row * gridSize + col

    companion object {
        const val GRID_SIZE = 3
    }
}