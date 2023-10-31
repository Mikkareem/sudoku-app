package com.techullurgy.sudoku.domain.models

data class SudokuGridItem(
    var value: Int,
    val row: Int,
    val column: Int,
    val box: Int,
    var isFocused: Boolean,
    var canChange: Boolean
)