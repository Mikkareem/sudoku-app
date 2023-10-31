package com.techullurgy.sudoku.presentation.components

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techullurgy.sudoku.domain.core.SudokuColors
import com.techullurgy.sudoku.domain.core.SudokuGame
import com.techullurgy.sudoku.domain.models.SudokuGridItemList

@Composable
fun GridLineView(
    gridSize: Int
) {
    Canvas(modifier = Modifier.size(gridSize.dp)) {
        val cellWidth = size.width / 9
        val cellHeight = size.height / 9

        repeat(10) { column ->
            drawLine(
                color = SudokuColors.primary,
                start = Offset(x = column * cellWidth, y = 0f),
                end = Offset(x = column * cellWidth, y = size.height),
                strokeWidth = getGridStrokeWidth(column, 1.dp.toPx(), 3.dp.toPx())
            )
        }

        repeat(10) { row ->
            drawLine(
                color = SudokuColors.primary,
                start = Offset(x = 0f, y = row * cellHeight),
                end = Offset(x = size.width, y = row * cellHeight),
                strokeWidth = getGridStrokeWidth(row, 1.dp.toPx(), 3.dp.toPx())
            )
        }
    }
}

@Composable
fun GridMainView(
    gridSize: Int,
    sudokuGrid: SudokuGridItemList,
    onCellFocusChanged: (Int) -> Unit,
    currentSelectedIndex: Int = -1
) {
    Box(modifier = Modifier.size(gridSize.dp)) {
        val cellSize = gridSize.dp / 9
        Column(modifier = Modifier.fillMaxSize()) {
            repeat(9) { row ->
                Row(modifier = Modifier.fillMaxWidth()) {
                    repeat(9) { column ->
                        val index = (row * 9) + column
                        Box(
                            modifier = Modifier
                                .size(cellSize)
                                .background(
                                    color = if (sudokuGrid[index].isFocused) {
                                        if(currentSelectedIndex == index) SudokuColors.secondary else SudokuColors.tertiary
                                    } else Color.Transparent
                                )
                                .clickable {
                                    onCellFocusChanged(index)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if(sudokuGrid[index].value != 0) {
                                Text(
                                    text = "${sudokuGrid[index].value}",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if(sudokuGrid[index].canChange) Color.Black else SudokuColors.primary
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun SudokuGridView(
    modifier: Modifier = Modifier,
    gridSize: Int = 300,
    sudokuGrid: SudokuGridItemList = SudokuGame.initializeSudokuGrid(),
    onCellFocusChanged: (Int) -> Unit = {},
    currentSelectedIndex: Int = -1
) {
    Box(
        modifier = modifier.size(gridSize.dp)
    ) {
        GridMainView(
            gridSize = gridSize,
            sudokuGrid = sudokuGrid,
            onCellFocusChanged = onCellFocusChanged,
            currentSelectedIndex = currentSelectedIndex
        )
        GridLineView(gridSize = gridSize)
    }
}

private fun getGridStrokeWidth(index: Int, minWidth: Float, maxWidth: Float): Float {
    return if(index % 3 == 0) maxWidth else minWidth
}