package com.techullurgy.sudoku

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import com.techullurgy.sudoku.presentation.layouts.GradientBackground
import com.techullurgy.sudoku.presentation.screens.MainScreen
import com.techullurgy.sudoku.ui.theme.SudokuTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SudokuTheme {
                GradientBackground {
                    MainScreen()
                }
            }
        }
    }
}