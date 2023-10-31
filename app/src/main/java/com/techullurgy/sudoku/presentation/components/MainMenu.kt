package com.techullurgy.sudoku.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.techullurgy.sudoku.domain.core.SudokuColors

@Preview
@Composable
fun MainMenu(navController: NavController = rememberNavController()) {
    Column(
        modifier = Modifier
            .fillMaxSize(),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box(
            modifier = Modifier
                .shadow(elevation = 40.dp, shape = RoundedCornerShape(60f))
                .background(SudokuColors.secondary)
                .padding(
                    horizontal = 32.dp,
                    vertical = 18.dp
                )
                .clickable {
                    navController.navigate("sudoku_game")
                }
        ) {
            Text(text = "Sudoku Game", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
        Spacer(modifier = Modifier.height(64.dp))
        Box(
            modifier = Modifier
                .shadow(elevation = 40.dp, shape = RoundedCornerShape(60f))
                .background(SudokuColors.secondary)
                .padding(
                    horizontal = 32.dp,
                    vertical = 18.dp
                )
                .clickable {
                    navController.navigate("sudoku_solver")
                }
        ) {
            Text(text = "Sudoku Solver", fontSize = 40.sp, fontWeight = FontWeight.Bold, color = Color.White)
        }
    }
}