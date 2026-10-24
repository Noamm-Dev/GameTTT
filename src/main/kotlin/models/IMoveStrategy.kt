package org.noamm.models

interface IMoveStrategy {
    fun chooseMove(cells: List<CellState>, gridSize: Int, player: CellState): Pair<Int, Int>
}
