package com.techullurgy.sudoku.domain.core

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

object SudokuGenerator {
    private val sudokuBoard: MutableList<MutableList<Int>> = ArrayList()
    private const val GRID_SIZE = 9

    private fun initialize() {
        for (i in 0 until GRID_SIZE) {
            val temp: MutableList<Int> = ArrayList(listOf(0, 0, 0, 0, 0, 0, 0, 0, 0))
            sudokuBoard.add(temp)
        }
    }

    fun generate() {
        initialize()
        solveSudokuPrivately()
    }

    private fun solveSudokuPrivately(): Boolean {
        for (row in 0 until GRID_SIZE) {
            for (column in 0 until GRID_SIZE) {
                if (sudokuBoard[row][column] == 0) {
                    val guesses = mutableListOf(1, 2, 3, 4, 5, 6, 7, 8, 9)
                    guesses.shuffle()
                    for (guessIndex in 0 until GRID_SIZE) {
                        if (validMove(guesses[guessIndex], row, column)) {
                            sudokuBoard[row][column] = guesses[guessIndex]
                            if (solveSudokuPrivately()) {
                                return true
                            }
                            sudokuBoard[row][column] = 0
                        }
                    }
                    return false
                }
            }
        }
        return true
    }

    suspend fun solveSudoku(): Boolean = withContext(Dispatchers.Default) {
        if(!isValidBoard()) false
        else solveSudokuPrivately()
    }

    private fun isValidBoard(): Boolean {
        for(i in 0 until GRID_SIZE) {
            if(!notInRow(i)) return false
        }
        for(i in 0 until GRID_SIZE) {
            if(!notInColumn(i)) return false
        }
        for(i in 0 until GRID_SIZE) {
            for (j in 0 until GRID_SIZE) {
                if(!notInBox(i, j)) return false
            }
        }
        return true
    }

    private fun notInRow(row: Int): Boolean {
        val hashSet = HashSet<Int>()
        for(i in 0 until 9) {

            if(hashSet.contains(sudokuBoard[row][i])) return false

            if(sudokuBoard[row][i] != 0) {
                hashSet.add(sudokuBoard[row][i])
            }
        }
        return true
    }

    private fun notInColumn(col: Int): Boolean {
        val hashSet = HashSet<Int>()
        for(i in 0 until 9) {

            if(hashSet.contains(sudokuBoard[i][col])) return false

            if(sudokuBoard[i][col] != 0) {
                hashSet.add(sudokuBoard[i][col])
            }
        }
        return true
    }

    private fun notInBox(row: Int, col: Int): Boolean {
        val hashSet = HashSet<Int>()
        val rowStart = row - row % 3
        val colStart = col - col % 3

        for(i in rowStart until rowStart+3) {
            for (j in colStart until colStart+3) {
                if(hashSet.contains(sudokuBoard[i][j])) return false

                if(sudokuBoard[i][j] != 0) {
                    hashSet.add(sudokuBoard[i][j])
                }
            }

        }
        return true
    }

    private fun validMove(guess: Int, row: Int, column: Int): Boolean {
        return isGuessValidInRow(guess, row) &&
                isGuessValidInColumn(guess, column) &&
                isGuessValidInBox(guess, row, column)
    }

    private fun isGuessValidInBox(guess: Int, row: Int, column: Int): Boolean {
        val localBoxRowStart = row - row % 3
        val localBoxColumnStart = column - column % 3
        for (i in localBoxRowStart until localBoxRowStart + 3) {
            for (j in localBoxColumnStart until localBoxColumnStart + 3) {
                if (sudokuBoard[i][j] == guess) return false
            }
        }
        return true
    }

    private fun isGuessValidInColumn(guess: Int, column: Int): Boolean {
        for (i in 0 until GRID_SIZE) {
            if (sudokuBoard[i][column] == guess) return false
        }
        return true
    }

    private fun isGuessValidInRow(guess: Int, row: Int): Boolean {
        for (i in 0 until GRID_SIZE) {
            if (sudokuBoard[row][i] == guess) return false
        }
        return true
    }

    fun getSudokuBoard(): List<MutableList<Int>> {
        return sudokuBoard
    }

    fun flattenSudokuBoard(): List<Int> {
        val result: MutableList<Int> = mutableListOf()
        for (i in 0 until GRID_SIZE) {
            for (j in 0 until GRID_SIZE) {
                result.add(sudokuBoard[i][j])
            }
        }
        return result.toList()
    }

    fun updateOriginalBoardFromFlattenedBoard(flattenedBoard: List<Int>) {
        initialize()
        flattenedBoard.forEachIndexed { index, value ->
            val row = index / GRID_SIZE
            val column = index % GRID_SIZE

            sudokuBoard[row][column] = value
        }
        println(sudokuBoard)
    }
}