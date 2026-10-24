package org.noamm.models

sealed interface WinLine {
    data class Horizontal(val row: Int): WinLine
    data class Vertical(val col: Int): WinLine
    data object Diagonal: WinLine
    data object AntiDiagonal: WinLine

    fun cells(gridSize: Int) = (0 until gridSize).map { i ->
        when (this) {
            is Horizontal -> row to i
            is Vertical -> i to col
            Diagonal -> i to i
            AntiDiagonal -> i to gridSize - 1 - i
        }
    }
}