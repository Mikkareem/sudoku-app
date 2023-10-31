package com.techullurgy.sudoku

import com.techullurgy.sudoku.domain.core.SudokuGenerator
import kotlinx.coroutines.test.runTest
import org.junit.Test

import org.junit.Assert.*

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class BusinessLogicTest {
    @Test
    fun `sudoku solve happen for success case`() = runTest {
        val board = Array(81) { 0 }
        board[7] = 7
        SudokuGenerator.updateOriginalBoardFromFlattenedBoard(board.asList())
        val isValid = SudokuGenerator.solveSudoku()
        assertTrue(isValid)

        val list = SudokuGenerator.flattenSudokuBoard()
        list.forEach {
            assertNotEquals(0, it)
        }
    }

    @Test
    fun `sudoku solver happen for failure case, same column`() = runTest {
        val board = Array(81) { 0 }
        board[7] = 7
        board[25] = 7
        SudokuGenerator.updateOriginalBoardFromFlattenedBoard(board.asList())
        val isValid = SudokuGenerator.solveSudoku()
        assertFalse(isValid)

        val sudokuBoard = SudokuGenerator.flattenSudokuBoard()
        sudokuBoard.forEachIndexed { index, it ->
            if(index != 7 && index != 25) {
                assertEquals(0, it)
            } else {
                assertEquals(7, it)
            }
        }
    }

    @Test
    fun `sudoku solver happen for failure case, same box`() = runTest {
        val board = Array(81) { 0 }
        board[29] = 7
        board[45] = 7
        SudokuGenerator.updateOriginalBoardFromFlattenedBoard(board.asList())
        val isValid = SudokuGenerator.solveSudoku()
        assertFalse(isValid)

        val sudokuBoard = SudokuGenerator.flattenSudokuBoard()
        sudokuBoard.forEachIndexed { index, it ->
            if(index != 29 && index != 45) {
                assertEquals(0, it)
            } else {
                assertEquals(7, it)
            }
        }
    }

    @Test
    fun `sudoku solver happen for failure case, same row`() = runTest {
        val board = Array(81) { 0 }
        board[54] = 7
        board[61] = 7
        SudokuGenerator.updateOriginalBoardFromFlattenedBoard(board.asList())
        val isValid = SudokuGenerator.solveSudoku()
        assertFalse(isValid)

        val sudokuBoard = SudokuGenerator.flattenSudokuBoard()
        sudokuBoard.forEachIndexed { index, it ->
            if(index != 54 && index != 61) {
                assertEquals(0, it)
            } else {
                assertEquals(7, it)
            }
        }
    }
}