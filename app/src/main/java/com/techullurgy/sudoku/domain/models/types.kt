package com.techullurgy.sudoku.domain.models

import androidx.compose.runtime.SnapshotMutationPolicy

typealias SudokuGridItemList = List<SudokuGridItem>
typealias SudokuGameStateSnapshotMutationPolicy = SnapshotMutationPolicy<SudokuGameState>
typealias SudokuSolverStateSnapshotMutationPolicy = SnapshotMutationPolicy<SudokuSolverState>
