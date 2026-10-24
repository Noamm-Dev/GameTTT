package org.noamm.models

interface IWinChecker {
    fun record(row: Int, col: Int, player: CellState): WinLine?

    fun reset()
}