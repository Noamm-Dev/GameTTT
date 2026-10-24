package org.noamm.logic

import org.noamm.logic.ai.GameMode
import org.noamm.logic.ai.MinimaxStrategy
import org.noamm.models.CellState
import org.noamm.models.GameStatus
import kotlin.test.*

class MinimaxStrategyTest {
    private val x = CellState.X
    private val o = CellState.O
    private val e = CellState.EMPTY

    @Test
    fun takesImmediateWin() {
        val cells = listOf(
            x, x, e,
            o, o, e,
            x, e, e
        )
        assertEquals(1 to 2, MinimaxStrategy.chooseMove(cells, 3, o))
    }

    @Test
    fun blocksOpponentWin() {
        val cells = listOf(
            x, x, e,
            e, o, e,
            e, e, e
        )
        assertEquals(0 to 2, MinimaxStrategy.chooseMove(cells, 3, o))
    }

    @Test
    fun variesBetweenEquallyGoodMoves() {
        val cells = List(9) { e }
        val moves = List(20) { MinimaxStrategy.chooseMove(cells, 3, x) }.toSet()

        assertTrue(moves.size > 1)
    }

    @Test
    fun perfectPlayEndsInDraw() {
        val cells = MutableList(9) { e }
        val checker = WinChecker(3)
        var player = x

        repeat(cells.size) {
            val (row, col) = MinimaxStrategy.chooseMove(cells, 3, player)
            cells[row * 3 + col] = player
            assertNull(checker.record(row, col, player))
            player = if (player == x) o else x
        }
    }

    @Test
    fun neverLosesToRandomPlayer() {
        for (aiSide in listOf(x, o)) repeat(50) {
            val game = Game(GameMode.HARD, aiSide)
            while (game.status == GameStatus.Playing) {
                val (row, col) = (0 until 3).flatMap { row -> (0 until 3).map { row to it } }
                    .filter { (row, col) -> game[row, col] == e }
                    .random()
                game.play(row, col)
            }

            (game.status as? GameStatus.Won)?.let { assertEquals(aiSide, it.player) }
        }
    }
}
