package org.noamm.logic

import org.noamm.models.CellState
import org.noamm.models.WinLine
import kotlin.test.*

class WinCheckerTest {
    private val checker = WinChecker(3)

    @Test
    fun horizontalWin() {
        assertNull(checker.record(1, 0, CellState.X))
        assertNull(checker.record(1, 1, CellState.X))
        assertEquals(WinLine.Horizontal(1), checker.record(1, 2, CellState.X))
    }

    @Test
    fun verticalWin() {
        assertNull(checker.record(0, 2, CellState.O))
        assertNull(checker.record(1, 2, CellState.O))
        assertEquals(WinLine.Vertical(2), checker.record(2, 2, CellState.O))
    }

    @Test
    fun diagonalWin() {
        assertNull(checker.record(0, 0, CellState.X))
        assertNull(checker.record(1, 1, CellState.X))
        assertEquals(WinLine.Diagonal, checker.record(2, 2, CellState.X))
    }

    @Test
    fun antiDiagonalWin() {
        assertNull(checker.record(0, 2, CellState.O))
        assertNull(checker.record(1, 1, CellState.O))
        assertEquals(WinLine.AntiDiagonal, checker.record(2, 0, CellState.O))
    }

    @Test
    fun mixedLineIsNotWin() {
        checker.record(0, 0, CellState.X)
        checker.record(0, 1, CellState.O)
        assertNull(checker.record(0, 2, CellState.X))
    }

    @Test
    fun resetClearsCounters() {
        checker.record(0, 0, CellState.X)
        checker.record(0, 1, CellState.X)
        checker.reset()
        assertNull(checker.record(0, 2, CellState.X))
    }
}
