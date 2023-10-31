package com.techullurgy.sudoku.presentation.screens

import androidx.activity.compose.BackHandler
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.techullurgy.sudoku.domain.core.SudokuColors
import com.techullurgy.sudoku.presentation.components.ControlsView
import com.techullurgy.sudoku.presentation.components.GridLineView
import com.techullurgy.sudoku.presentation.viewmodels.SudokuSolverViewModel


@Composable
fun SudokuSolverScreen(
    viewModel: SudokuSolverViewModel,
    navController: NavController,
) {
    var currentSelectedIndex by remember { mutableIntStateOf(-1) }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        SudokuSolverView(
            values = viewModel.sudokuGameState.value.sudokuGridItemList,
            originality = viewModel.sudokuGameState.value.originality,
            onCellFocusChanged = { currentSelectedIndex = it },
            currentSelectedIndex = currentSelectedIndex
        )
        ControlsView(
            onControlSelected = {
                viewModel.onValueChange(currentSelectedIndex, it)
            }
        )
        Row {
            Button(
                onClick = { viewModel.solve() },
                enabled = !viewModel.sudokuGameState.value.solveRequested
            ) {
                Text(text = "Solve")
            }
            Button(onClick = { navController.popBackStack() }) {
                Text(text = "Back")
            }
            Button(
                onClick = { viewModel.clearStates() }
            ) {
                Text(text = "Clear")
            }
        }
        AnimatedVisibility(visible = viewModel.sudokuGameState.value.errorMsg.isNotBlank()) {
            Text(text = viewModel.sudokuGameState.value.errorMsg)
        }

        BackHandler {
            println("Back is Pressed")
        }
    }
}

@Composable
fun SudokuSolverView(
    values: List<Int>,
    originality: List<Boolean>,
    onCellFocusChanged: (Int) -> Unit,
    currentSelectedIndex: Int,
    modifier: Modifier = Modifier,
    gridSize: Int = 300
) {
    Box(modifier = modifier) {
        GridLineView(gridSize = gridSize)
        GridMainSolverView(gridSize = gridSize, sudokuGrid = values, originality = originality, onCellFocusChanged = onCellFocusChanged, currentSelectedIndex = currentSelectedIndex)
    }
}

@Composable
fun GridMainSolverView(
    gridSize: Int,
    sudokuGrid: List<Int>,
    originality: List<Boolean>,
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
                                    color = if (currentSelectedIndex == index) SudokuColors.secondary else Color.Transparent
                                )
                                .clickable {
                                    onCellFocusChanged(index)
                                },
                            contentAlignment = Alignment.Center
                        ) {
                            if(sudokuGrid[index] != 0) {
                                Text(
                                    text = "${sudokuGrid[index]}",
                                    fontSize = 24.sp,
                                    fontWeight = FontWeight.Bold,
                                    color = if(originality[index]) SudokuColors.primary else Color.Black
                                )
                            }
                        }
                    }
                }
            }
        }
    }
}