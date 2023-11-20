package com.techullurgy.sudoku.presentation.screens

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.window.DialogProperties
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.dialog
import androidx.navigation.compose.rememberNavController
import com.techullurgy.sudoku.presentation.components.MainMenu
import com.techullurgy.sudoku.presentation.layouts.GradientBackground
import com.techullurgy.sudoku.presentation.viewmodels.SudokuGameViewModel
import com.techullurgy.sudoku.presentation.viewmodels.SudokuSolverViewModel

@Preview
@Composable
fun MainScreen() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = "main_menu") {
        composable(route = "main_menu") {
            GradientBackground {
                MainMenu(navController = navController)
            }
        }

        composable(route = "sudoku_game") {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val viewModel = viewModel<SudokuGameViewModel>()
                GradientBackground {
                    SudokuGameScreen(viewModel = viewModel, navController = navController)
                }
            }
        }

        composable(route = "sudoku_solver") {
            Box(
                modifier = Modifier
                    .fillMaxSize(),
                contentAlignment = Alignment.Center
            ) {
                val viewModel = viewModel<SudokuSolverViewModel>()
                GradientBackground {
                    SudokuSolverScreen(viewModel = viewModel, navController = navController)
                }
            }
        }

        dialog(
            route = "dialog_done",
            dialogProperties = DialogProperties(
                dismissOnBackPress = false,
                dismissOnClickOutside = false
            )
        ) {
            GameDialog {
                navController.navigate("main_menu") {
                    popUpTo(navController.graph.findStartDestination().id) {
                        saveState = false
                    }
                    launchSingleTop = true
                }
            }
        }
    }
}