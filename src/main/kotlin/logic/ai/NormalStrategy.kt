package org.noamm.logic.ai

import org.noamm.models.CellState
import org.noamm.models.IMoveStrategy

// plays perfectly most of the time but randomly makes a random move so its still somewhat winnable
object NormalStrategy: IMoveStrategy {
    private const val MISTAKE_CHANCE = 0.3

    override fun chooseMove(cells: List<CellState>, gridSize: Int, player: CellState): Pair<Int, Int> {
        val strategy = if (Math.random() < MISTAKE_CHANCE) RandomStrategy else MinimaxStrategy
        return strategy.chooseMove(cells, gridSize, player)
    }
}
