package com.techullurgy.sudoku.domain.models

data class SudokuSolverState(
    val sudokuGridItemList: List<Int>,
    val originality: List<Boolean>,
    val solveRequested: Boolean,
    val errorMsg: String = "",
) {
    companion object {
        val snapshotMutationPolicy: SudokuSolverStateSnapshotMutationPolicy = object : SudokuSolverStateSnapshotMutationPolicy {
            override fun equivalent(a: SudokuSolverState, b: SudokuSolverState): Boolean {
                if(a.sudokuGridItemList != b.sudokuGridItemList) return false
                if(a.originality != b.originality) return false
                if(a.solveRequested != b.solveRequested) return false
                if(a.errorMsg != b.errorMsg) return false
                return true
            }
        }
    }
}
