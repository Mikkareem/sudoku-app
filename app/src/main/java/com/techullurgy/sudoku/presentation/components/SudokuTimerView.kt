package com.techullurgy.sudoku.presentation.components

import androidx.compose.foundation.layout.Box
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp

@Composable
fun SudokuTimerView(
    modifier: Modifier = Modifier,
    currentTime: String
) {
    Box(modifier = modifier) {
        Text(text = currentTime, fontWeight = FontWeight.Bold, fontSize = 24.sp, modifier = Modifier.align(Alignment.CenterEnd))
    }
}