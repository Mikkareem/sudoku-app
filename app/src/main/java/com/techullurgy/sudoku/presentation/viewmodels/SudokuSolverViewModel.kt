package com.techullurgy.sudoku.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.techullurgy.sudoku.domain.core.SudokuGenerator
import com.techullurgy.sudoku.domain.models.SudokuSolverState
import kotlinx.coroutines.launch

class SudokuSolverViewModel : ViewModel() {

    var sudokuGameState: MutableState<SudokuSolverState> = mutableStateOf(
        value = SudokuSolverState(
            solveRequested = false,
            sudokuGridItemList = initialise(),
            originality = (Array(81){false}).asList()
        ),
        policy = SudokuSolverState.snapshotMutationPolicy
    )
        private set

    private fun initialise(): List<Int> = (Array(81){0}).asList()

    fun solve() {
        if(sudokuGameState.value.sudokuGridItemList.all { it == 0 }) {
            sudokuGameState.value = sudokuGameState.value.copy(errorMsg = "Please fill at least one cell in the grid")
            return
        }

        viewModelScope.launch {
            SudokuGenerator.updateOriginalBoardFromFlattenedBoard(sudokuGameState.value.sudokuGridItemList)
            val isValid = SudokuGenerator.solveSudoku()

            sudokuGameState.value = sudokuGameState.value.copy(
                errorMsg = "",
                solveRequested = true,
                sudokuGridItemList = SudokuGenerator.flattenSudokuBoard()
            )

            if(!isValid) {
                sudokuGameState.value = sudokuGameState.value.copy(
                    errorMsg = "Invalid Grid"
                )
            }
        }
    }

    fun onValueChange(currentIndex: Int, currentValue: Int) {
        sudokuGameState.value = sudokuGameState.value.copy(
            sudokuGridItemList = sudokuGameState.value.sudokuGridItemList.mapIndexed { index, value -> if(index == currentIndex) currentValue else value },
            originality = sudokuGameState.value.originality.mapIndexed { index, value -> if(index == currentIndex) true else value }
        )
    }

    fun clearStates() {
        sudokuGameState.value = sudokuGameState.value.copy(
            sudokuGridItemList = sudokuGameState.value.sudokuGridItemList.map { 0 },
            originality = sudokuGameState.value.originality.map { false },
            errorMsg = "",
            solveRequested = false
        )
    }
}