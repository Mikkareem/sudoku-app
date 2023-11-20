package com.techullurgy.sudoku.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.techullurgy.sudoku.R
import com.techullurgy.sudoku.domain.core.SudokuColors

@Preview
@Composable
fun ControlsView(
    modifier: Modifier = Modifier,
    isBoardFocused: Boolean = true,
    bannedControls: List<Int>? = null,
    onControlSelected: (Int) -> Unit = {}
) {
    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        verticalArrangement = Arrangement.spacedBy(20.dp),
        horizontalArrangement = Arrangement.spacedBy(20.dp),
        modifier = modifier
    ) {
        items(12) { index ->
            if(index < 9) {
                val control = index + 1

                if (bannedControls != null && bannedControls.contains(control)) {
                    Spacer(Modifier)
                } else {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(32.dp))
                            .background(SudokuColors.primary)
                            .padding(16.dp)
                            .then(
                                if (!isBoardFocused) {
                                    Modifier
                                } else {
                                    Modifier.clickable {
                                        onControlSelected(control)
                                    }
                                }
                            )
                    ) {
                        Text(
                            text = "$control",
                            fontWeight = FontWeight.Bold,
                            fontSize = 24.sp,
                            color = Color.White
                        )
                    }
                }
            } else {
                if(index == 10) {
                    Box(
                        contentAlignment = Alignment.Center,
                        modifier = Modifier
                            .clip(RoundedCornerShape(32.dp))
                            .background(SudokuColors.primary)
                            .padding(16.dp)
                            .then(
                                if (!isBoardFocused)
                                    Modifier
                                else {
                                    Modifier.clickable {
                                        onControlSelected(0)
                                    }
                                }
                            )
                    ) {
                        Icon(painter = painterResource(id = R.drawable.baseline_backspace_24), contentDescription = null, tint = Color.White)
                    }
                } else {
                    Spacer(Modifier)
                }
            }
        }
    }
}