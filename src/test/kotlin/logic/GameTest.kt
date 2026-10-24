package org.noamm.logic

import org.noamm.logic.ai.GameMode
import org.noamm.models.*
import kotlin.test.*

class GameTest {
    private val winningMoves = listOf(0 to 0, 1 to 0, 0 to 1, 1 to 1, 0 to 2)

    private fun IGame.playAll(moves: List<Pair<Int, Int>>) = moves.forEach { (row, col) -> play(row, col) }

    private fun IGame.markedCells() = (0 until gridSize).flatMap { row -> (0 until gridSize).map { row to it } }
        .filter { (row, col) -> get(row, col) != CellState.EMPTY }

    @Test
    fun xStartsAndTurnsAlternate() {
        val game = Game()
        assertEquals(CellState.X, game.currentPlayer)

        game.play(0, 0)
        assertEquals(CellState.X, game[0, 0])
        assertEquals(CellState.O, game.currentPlayer)

        game.play(1, 1)
        assertEquals(CellState.O, game[1, 1])
        assertEquals(CellState.X, game.currentPlayer)
    }

    @Test
    fun occupiedCellIsIgnored() {
        val game = Game()
        game.play(0, 0)
        game.play(0, 0)

        assertEquals(CellState.X, game[0, 0])
        assertEquals(CellState.O, game.currentPlayer)
    }

    @Test
    fun winSetsStatus() {
        val game = Game()
        game.playAll(winningMoves)

        assertEquals(GameStatus.Won(CellState.X, WinLine.Horizontal(0)), game.status)
    }

    @Test
    fun fullBoardIsDraw() {
        val game = Game()
        game.playAll(listOf(0 to 0, 0 to 1, 0 to 2, 1 to 1, 1 to 0, 1 to 2, 2 to 1, 2 to 0, 2 to 2))

        assertEquals(GameStatus.Draw, game.status)
    }

    @Test
    fun noMovesAfterGameOver() {
        val game = Game()
        game.playAll(winningMoves)
        game.play(2, 2)

        assertEquals(CellState.EMPTY, game[2, 2])
    }

    @Test
    fun resetRestoresState() {
        val game = Game()
        game.playAll(winningMoves)
        game.reset()

        assertTrue(game.markedCells().isEmpty())
        assertEquals(CellState.X, game.currentPlayer)
        assertEquals(GameStatus.Playing, game.status)
    }

    @Test
    fun aiRepliesImmediately() {
        val game = Game(GameMode.HARD, CellState.O)
        game.play(0, 0)

        assertEquals(2, game.markedCells().size)
        assertEquals(CellState.X, game.currentPlayer)
    }

    @Test
    fun aiAsXMovesFirst() {
        val game = Game(GameMode.EASY, CellState.X)
        assertEquals(1, game.markedCells().size)
        assertEquals(CellState.O, game.currentPlayer)

        game.reset()
        assertEquals(1, game.markedCells().size)
    }

    @Test
    fun aiDoesNotReplyToInvalidMove() {
        val game = Game(GameMode.HARD, CellState.X)
        val (row, col) = game.markedCells().single()
        game.play(row, col)

        assertEquals(1, game.markedCells().size)
    }
}
