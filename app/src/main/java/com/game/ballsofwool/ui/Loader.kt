package com.game.ballsofwool.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier

@Composable
fun Loader(
    modifier: Modifier = Modifier,
) {
    Box(modifier) {
        ProgressBar(Modifier.align(Alignment.Center))
    }
}
