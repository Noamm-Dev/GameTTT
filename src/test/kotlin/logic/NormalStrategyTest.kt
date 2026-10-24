package org.noamm.logic

import org.noamm.logic.ai.NormalStrategy
import org.noamm.models.CellState
import kotlin.test.*

class NormalStrategyTest {
    @Test
    fun picksEmptyCell() {
        val cells = List(9) { if (it == 4 || it == 8) CellState.EMPTY else CellState.X }

        repeat(20) {
            assertContains(listOf(1 to 1, 2 to 2), NormalStrategy.chooseMove(cells, 3, CellState.O))
        }
    }
}
