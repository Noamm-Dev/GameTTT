package org.noamm.logic.ai

import org.noamm.models.*

object RandomStrategy: IMoveStrategy {
    override fun chooseMove(cells: List<CellState>, gridSize: Int, player: CellState): Pair<Int, Int> {
        val index = cells.indices.filter { cells[it] == CellState.EMPTY }.random()
        return index / gridSize to index % gridSize
    }
}
