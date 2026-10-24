package org.noamm.logic

import org.noamm.logic.ai.RandomStrategy
import org.noamm.models.CellState
import kotlin.test.*

class RandomStrategyTest {
    @Test
    fun picksEmptyCell() {
        val cells = List(9) { if (it == 4) CellState.EMPTY else CellState.X }

        repeat(20) {
            assertEquals(1 to 1, RandomStrategy.chooseMove(cells, 3, CellState.O))
        }
    }
}
