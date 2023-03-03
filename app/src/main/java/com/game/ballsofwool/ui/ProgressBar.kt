package com.game.ballsofwool.ui

import androidx.compose.foundation.layout.size
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.ui.theme.Purple6B3179

@Composable
fun ProgressBar(
    modifier: Modifier = Modifier,
) {
    CircularProgressIndicator(
        modifier = modifier.size(80.dp),
        color = Purple6B3179,
        strokeWidth = 6.dp,
    )
}