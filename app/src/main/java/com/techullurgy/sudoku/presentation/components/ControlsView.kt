package com.techullurgy.sudoku.presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
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
    Column(modifier = modifier) {
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for(control in 1..3) {
                if (bannedControls != null && bannedControls.contains(control)) {
                    Spacer(modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp))
                } else {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(32.dp))
                            .background(SudokuColors.primary)
                            .then(
                                if (!isBoardFocused)
                                    Modifier
                                else {
                                    Modifier.clickable {
                                        onControlSelected(control)
                                    }
                                }
                            )
                            .padding(horizontal = 32.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "$control",
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for(control in 4..6) {
                if (bannedControls != null && bannedControls.contains(control)) {
                    Spacer(modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp))
                } else {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(32.dp))
                            .background(SudokuColors.primary)
                            .then(
                                if (!isBoardFocused)
                                    Modifier
                                else {
                                    Modifier.clickable {
                                        onControlSelected(control)
                                    }
                                }
                            )
                            .padding(horizontal = 32.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "$control",
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            for(control in 7..9) {
                if (bannedControls != null && bannedControls.contains(control)) {
                    Spacer(modifier = Modifier.padding(horizontal = 32.dp, vertical = 8.dp))
                } else {
                    Box(
                        modifier = Modifier
                            .clip(RoundedCornerShape(32.dp))
                            .background(SudokuColors.primary)
                            .then(
                                if (!isBoardFocused)
                                    Modifier
                                else {
                                    Modifier.clickable {
                                        onControlSelected(control)
                                    }
                                }
                            )
                            .padding(horizontal = 32.dp, vertical = 8.dp)
                    ) {
                        Text(
                            text = "$control",
                            fontWeight = FontWeight.Bold,
                            fontSize = 32.sp,
                            color = Color.White
                        )
                    }
                }
            }
        }
        Row(
            modifier = Modifier
                .padding(16.dp)
                .fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceAround
        ) {
            Box(
                modifier = Modifier
                    .clip(RoundedCornerShape(32.dp))
                    .background(SudokuColors.primary)
                    .then(
                        if (!isBoardFocused)
                            Modifier
                        else {
                            Modifier.clickable {
                                onControlSelected(0)
                            }
                        }
                    )
                    .padding(horizontal = 32.dp, vertical = 8.dp)
            ) {
                Icon(painter = painterResource(id = R.drawable.baseline_backspace_24), contentDescription = null, tint = Color.White)
            }
        }
    }
}
