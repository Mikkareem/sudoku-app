package com.techullurgy.sudoku.presentation.components

import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import com.techullurgy.sudoku.domain.core.SudokuColors

@Composable
fun SudokuButton(
    onClick: () -> Unit,
    text: String,
    modifier: Modifier = Modifier,
    enabled: Boolean = true
) {
    Button(
        onClick = onClick,
        modifier = modifier,
        colors = ButtonDefaults.buttonColors(
            containerColor = SudokuColors.tertiary,
            contentColor = SudokuColors.secondary
        ),
        enabled = enabled
    ) {
        Text(text = text)
    }
}