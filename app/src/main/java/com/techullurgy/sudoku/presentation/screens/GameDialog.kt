package com.techullurgy.sudoku.presentation.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techullurgy.sudoku.domain.core.SudokuColors
import com.techullurgy.sudoku.presentation.components.SudokuButton

@Preview
@Composable
fun GameDialog(
    onContinueClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f),
        colors = CardDefaults.cardColors(
            containerColor = SudokuColors.primary,
            contentColor = SudokuColors.tertiary
        ),
        shape = RoundedCornerShape(10),
    ) {
        Column(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Text(text = "Success", fontSize = 40.sp, modifier = Modifier.padding(10.dp))
            SudokuButton(
                onClick = onContinueClick,
                modifier = Modifier.padding(10.dp),
                text = "Main Menu"
            )
        }
    }
}