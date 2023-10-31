package com.techullurgy.sudoku.domain.models

data class SudokuGameState(
    val sudokuGrid: SudokuGridItemList,
    val isBoardFocused: Boolean,
    val currentSelectedIndex: Int,
    val isFinished: Boolean
) {
    companion object {
        val snapshotMutationPolicy: SudokuGameStateSnapshotMutationPolicy = object : SudokuGameStateSnapshotMutationPolicy {
            override fun equivalent(a: SudokuGameState, b: SudokuGameState): Boolean {
                if(a.sudokuGrid !== b.sudokuGrid) return false
                if(a.isBoardFocused != b.isBoardFocused) return false
                if(a.currentSelectedIndex != b.currentSelectedIndex) return false
                if(a.isFinished != b.isFinished) return false
                return true
            }
        }
    }
}

