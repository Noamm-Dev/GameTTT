package org.noamm.logic

import org.noamm.models.*
import kotlin.math.abs

// X adds 1 and O removes 1 so a win is when its abs reaches gridSize
class WinChecker(private val gridSize: Int): IWinChecker {
    private val rows = IntArray(gridSize)
    private val cols = IntArray(gridSize)
    private var diag = 0
    private var antiDiag = 0

    override fun record(row: Int, col: Int, player: CellState): WinLine? {
        val delta = if (player == CellState.X) 1 else - 1
        rows[row] += delta
        cols[col] += delta
        if (row == col) diag += delta
        if (row + col == gridSize - 1) antiDiag += delta

        return when (gridSize) {
            abs(rows[row]) -> WinLine.Horizontal(row)
            abs(cols[col]) -> WinLine.Vertical(col)
            abs(diag) -> WinLine.Diagonal
            abs(antiDiag) -> WinLine.AntiDiagonal
            else -> null
        }
    }

    override fun reset() {
        rows.fill(0)
        cols.fill(0)
        diag = 0
        antiDiag = 0
    }
}