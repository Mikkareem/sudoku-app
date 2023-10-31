package com.techullurgy.sudoku.domain.core

import com.techullurgy.sudoku.domain.models.GameLevel
import com.techullurgy.sudoku.domain.models.SudokuGridItem
import com.techullurgy.sudoku.domain.models.SudokuGridItemList
import kotlin.random.Random

object SudokuGame {

    var sudokuGrid: SudokuGridItemList = initializeSudokuGrid()
    private set

    fun createNewGame(level: GameLevel) {
        SudokuGenerator.generate()

        val sudokuBoard = SudokuGenerator.getSudokuBoard()
        makeSudokuGame(sudokuBoard, level)

        val flattenSudokuBoard: List<Int> = SudokuGenerator.flattenSudokuBoard()
        sudokuGrid = initializeSudokuGrid(flattenSudokuBoard)
    }

    private fun makeSudokuGame(sudokuBoard: List<MutableList<Int>>, level: GameLevel) {
        val randomThreshold = when (level) {
            GameLevel.EASY -> 0.95
            GameLevel.MEDIUM -> 0.75
            GameLevel.HARD -> 0.5
        }
        for (i in 0 until 9) {
            for (j in 0 until 9) {
                if (Random.nextDouble(1.0) >= randomThreshold) {
                    sudokuBoard[i][j] = 0
                }
            }
        }
    }

    fun getBox(index: Int): Int {
        return when(index) {
            0,1,2,9,10,11,18,19,20 -> 0
            3,4,5,12,13,14,21,22,23 -> 1
            6,7,8,15,16,17,24,25,26 -> 2
            27,28,29,36,37,38,45,46,47 -> 3
            30,31,32,39,40,41,48,49,50 -> 4
            33,34,35,42,43,44,51,52,53 -> 5
            54,55,56,63,64,65,72,73,74 -> 6
            57,58,59,66,67,68,75,76,77 -> 7
            60,61,62,69,70,71,78,79,80 -> 8
            else -> 0
        }
    }

    fun initializeSudokuGrid(
        initialValues: List<Int>? = null
    ): SudokuGridItemList {
        val initialGrid = mutableListOf<SudokuGridItem>()

        (0..80).forEach { index ->
            val row = index / 9
            val column = index % 9
            val box = getBox(index)

            val value = initialValues?.get(index) ?: 0
            val canChange = (initialValues?.get(index) ?: 0) == 0

            val sudokuGridItem = SudokuGridItem(value, row, column, box, false, canChange)
            initialGrid.add(sudokuGridItem)
        }

        return initialGrid.toList()
    }

    fun checkEntryIsValid(index: Int, givenValue: Int): Boolean {
        val row = index / 9
        val column = index % 9

        return checkEntryIsValid(row, column, givenValue)
    }

    fun putEntry(index: Int, value: Int) {
        if(!sudokuGrid[index].canChange) return
        sudokuGrid[index].value = value
    }

    private fun checkEntryIsValid(row: Int, column: Int, givenValue: Int): Boolean {
        sudokuGrid.filter { item -> item.row == row }.forEach {
            if(it.value == givenValue) return false
        }
        sudokuGrid.filter { item -> item.column == column }.forEach {
            if(it.value == givenValue) return false
        }

        val box = getBox(index = (row * 9) + column)
        sudokuGrid.filter { item -> item.box == box }.forEach {
            if(it.value == givenValue) return false
        }

        return true
    }

    fun isFinished(): Boolean {
        return sudokuGrid.all { item -> item.value != 0 }
    }
}
