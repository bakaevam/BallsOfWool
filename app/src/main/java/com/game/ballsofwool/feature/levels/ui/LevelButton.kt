package com.game.ballsofwool.feature.levels.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.ui.theme.RoseE2ABF5
import com.game.ballsofwool.ui.theme.White

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Box(
        modifier = Modifier.background(color = RoseE2ABF5)
    ) {
        LevelButton(
            icon = R.drawable.ic_previous_button,
            onClick = {},
        )
    }
}

@Composable
fun LevelButton(
    icon: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    IconButton(
        onClick = onClick,
        modifier = modifier.size(33.dp),
    ) {
        Icon(
            painter = painterResource(icon),
            contentDescription = null,
            tint = White,
        )
    }
}