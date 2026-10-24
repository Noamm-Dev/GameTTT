package org.noamm.logic

import androidx.compose.runtime.*
import org.noamm.logic.ai.GameMode
import org.noamm.models.*

class Game(override val mode: GameMode = GameMode.PVP, override val aiSide: CellState = CellState.O): IGame {
    override val gridSize = 3
    private val winChecker = WinChecker(gridSize)

    private val cells = List(gridSize * gridSize) { CellState.EMPTY }.toMutableStateList()
    private var moves = 0

    override var currentPlayer by mutableStateOf(CellState.X)
    override var status by mutableStateOf<GameStatus>(GameStatus.Playing)

    init {
        playAiMove()
    }

    override operator fun get(row: Int, col: Int) = cells[index(row, col)]

    override fun play(row: Int, col: Int) {
        place(row, col)
        playAiMove()
    }

    override fun reset() {
        cells.replaceAll { CellState.EMPTY }
        winChecker.reset()
        moves = 0
        currentPlayer = CellState.X
        status = GameStatus.Playing
        playAiMove()
    }

    private fun place(row: Int, col: Int) {
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

    private fun playAiMove() {
        val strategy = mode.strategy ?: return
        if (status != GameStatus.Playing) return
        if (currentPlayer != aiSide) return

        val (row, col) = strategy.chooseMove(cells, gridSize, aiSide)
        place(row, col)
    }

    private fun index(row: Int, col: Int) = row * gridSize + col

    companion object {
        const val GRID_SIZE = 3
    }
}
