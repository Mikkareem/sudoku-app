package com.techullurgy.sudoku.presentation.screens

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentHeight
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Preview
@Composable
fun GameDialog(
    onContinueClick: () -> Unit = {}
) {
    Card(
        modifier = Modifier
            .fillMaxWidth(0.8f)
            .wrapContentHeight(),
        shape = RoundedCornerShape(10),
    ) {
        Text(text = "Success", fontSize = 40.sp, modifier = Modifier.padding(10.dp))
        Button(
            onClick = onContinueClick,
            modifier = Modifier.padding(10.dp)
        ) {
            Text(text = "Main Menu")
        }
    }
}