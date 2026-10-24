package org.noamm.logic

import org.noamm.models.*
import kotlin.math.abs

class CounterWinChecker(private val size: Int): IWinChecker {
    // X adds 1 and O subtracts 1, so a line is won when its sum reaches +-size
    private val rows = IntArray(size)
    private val cols = IntArray(size)
    private var diag = 0
    private var antiDiag = 0

    override fun record(row: Int, col: Int, player: CellState): WinLine? {
        val delta = if (player == CellState.X) 1 else - 1
        rows[row] += delta
        cols[col] += delta
        if (row == col) diag += delta
        if (row + col == size - 1) antiDiag += delta

        return when (size) {
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