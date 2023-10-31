package com.techullurgy.sudoku.presentation.screens

import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onSizeChanged
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.techullurgy.sudoku.domain.core.StopWatch
import com.techullurgy.sudoku.presentation.components.ControlsView
import com.techullurgy.sudoku.presentation.components.SudokuGridView
import com.techullurgy.sudoku.presentation.components.SudokuTimerView
import com.techullurgy.sudoku.presentation.viewmodels.SudokuGameViewModel
import kotlin.math.min

@Composable
fun SudokuGameScreen(
    viewModel: SudokuGameViewModel,
    navController: NavController
) {
    var gridSize by remember {
        mutableStateOf(IntSize.Zero)
    }

    var isDismissed by remember { mutableStateOf(false) }

    val currentTime = viewModel.stopWatchFlow.collectAsState(initial = "00:00")

    LaunchedEffect(key1 = Unit) {
        StopWatch.start()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(horizontal = 16.dp, vertical = 32.dp)
            .onSizeChanged {
                gridSize = it
            }
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = null
            ) {
                viewModel.changeFocus(-1)
            },
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        val newGridSize = with(LocalDensity.current) {
            min(gridSize.width.toDp().value.toInt(), gridSize.height.toDp().value.toInt())
        }

        SudokuTimerView(
            modifier = Modifier.fillMaxWidth(),
            currentTime = currentTime.value
        )
        SudokuGridView(
            gridSize = newGridSize,
            sudokuGrid = viewModel.sudokuGameState.value.sudokuGrid,
            onCellFocusChanged = viewModel::changeFocus,
            currentSelectedIndex = viewModel.sudokuGameState.value.currentSelectedIndex
        )
        ControlsView(
            isBoardFocused = viewModel.sudokuGameState.value.isBoardFocused,
            bannedControls = viewModel.getBannedControls(),
            onControlSelected = viewModel::onControlSelected,
            modifier = Modifier.padding(top = 32.dp)
        )
        
        if(viewModel.sudokuGameState.value.isFinished && !isDismissed) {
            navController.navigate("dialog_done")
        }
    }
}

