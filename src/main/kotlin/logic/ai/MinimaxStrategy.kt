package org.noamm.logic.ai

import org.noamm.models.*

/**
 * Minimax algorithm for Tic-tac-toe
 * based off https://gnoht.com/til/ttt-with-minimax
 */
object MinimaxStrategy: IMoveStrategy {
    override fun chooseMove(cells: List<CellState>, gridSize: Int, player: CellState): Pair<Int, Int> {
        val board = cells.toMutableList()
        val lines = winLines(gridSize)
        val scores = emptyIndices(board).associateWith { score(board, it, player, lines, 0) }
        val bestScore = scores.values.max()
        val best = scores.filterValues { it == bestScore }.keys.random()
        return best / gridSize to best % gridSize
    }

    // Negamax variant of the minimax: one function for both players, the opponent's best reply is negated.
    // Like the article's 1/depth, wins score higher the sooner they happen, and losses are delayed as long as possible
    private fun score(board: MutableList<CellState>, index: Int, player: CellState, lines: List<List<Int>>, depth: Int): Int {
        board[index] = player
        val opponent = if (player == CellState.X) CellState.O else CellState.X
        val result = when {
            lines.any { line -> line.all { board[it] == player } } -> board.size - depth
            CellState.EMPTY !in board -> 0
            else -> - emptyIndices(board).maxOf { score(board, it, opponent, lines, depth + 1) }
        }
        board[index] = CellState.EMPTY
        return result
    }

    private fun emptyIndices(board: List<CellState>) = board.indices.filter { board[it] == CellState.EMPTY }

    private fun winLines(gridSize: Int) = WinLine.ofGrid(gridSize).map { line ->
        line.cells(gridSize).map { (row, col) -> row * gridSize + col }
    }
}
