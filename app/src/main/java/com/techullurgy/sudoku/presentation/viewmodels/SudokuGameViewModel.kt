package com.techullurgy.sudoku.presentation.viewmodels

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.techullurgy.sudoku.domain.models.GameLevel
import com.techullurgy.sudoku.domain.core.StopWatch
import com.techullurgy.sudoku.domain.core.StopWatchListener
import com.techullurgy.sudoku.domain.core.SudokuGame
import com.techullurgy.sudoku.domain.models.SudokuGameState
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.flow.distinctUntilChanged

class SudokuGameViewModel: ViewModel() {

    var sudokuGameState: MutableState<SudokuGameState> = mutableStateOf(
        value = SudokuGameState(
            sudokuGrid = SudokuGame.createNewGame(GameLevel.EASY).run { SudokuGame.sudokuGrid },
            isBoardFocused = false,
            currentSelectedIndex = -1,
            isFinished = false
        ),
        policy = SudokuGameState.snapshotMutationPolicy
    )
        private set

    val stopWatchFlow = callbackFlow {
        val listener = object: StopWatchListener {
            override fun onTick(elapsedTimeValue: String) {
                trySend(elapsedTimeValue)
            }
            override fun onStop() {
                trySend("Stopped")
            }
        }
        StopWatch.addListener(listener)
        awaitClose {
            StopWatch.cancel()
        }
    }.distinctUntilChanged()

    fun changeFocus(index: Int) {
        if(index < 0) {
            sudokuGameState.value = sudokuGameState.value.copy(
                sudokuGrid = SudokuGame.sudokuGrid.map {
                    it.copy(
                        isFocused = false
                    )
                },
                isBoardFocused = false,
                currentSelectedIndex = -1
            )
            return
        }
        val row = index / 9
        val column = index % 9
        val box = SudokuGame.getBox(index)

        sudokuGameState.value = sudokuGameState.value.copy(
            sudokuGrid = SudokuGame.sudokuGrid.map {
                it.copy(
                    isFocused = it.row == row || it.column == column || it.box == box
                )
            },
            isBoardFocused = true,
            currentSelectedIndex = if(SudokuGame.sudokuGrid[index].canChange) index else -1
        )
    }

    fun getBannedControls(): List<Int>? {
        return null
    }

    fun onControlSelected(control: Int) {
        val index = sudokuGameState.value.currentSelectedIndex
        if(index < 0) return

        if(sudokuGameState.value.sudokuGrid[index].value == 0) {
            if(SudokuGame.checkEntryIsValid(index, control)) {
                SudokuGame.putEntry(index, control)
                sudokuGameState.value = sudokuGameState.value.copy(
                    sudokuGrid = SudokuGame.sudokuGrid,
                    currentSelectedIndex = -1,
                    isBoardFocused = false
                )
                if(SudokuGame.isFinished()) {
                    stopStopWatch()
                    sudokuGameState.value = sudokuGameState.value.copy(
                        isFinished = true
                    )
                }
            } else {
                changeFocus(-1)
            }
        }
    }

    fun startStopWatch() = StopWatch.start()
    private fun stopStopWatch() = StopWatch.stop()
    fun pauseStopWatch() = StopWatch.pause()
    fun cancelStopWatch() = StopWatch.cancel()
}