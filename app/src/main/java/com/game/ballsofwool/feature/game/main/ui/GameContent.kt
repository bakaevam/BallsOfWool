package com.game.ballsofwool.feature.game.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.data.model.Ball
import com.game.ballsofwool.data.model.Level
import com.game.ballsofwool.feature.game.main.GameState
import com.game.ballsofwool.ui.ErrorLayout
import com.game.ballsofwool.ui.Loader
import com.game.ballsofwool.ui.theme.RoseE2ABF5
import com.game.ballsofwool.ui.theme.White

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column(
        modifier = Modifier
            .background(color = RoseE2ABF5)
    ) {
        GameContent(
            state = GameState(
                currentLevel = Level(
                    levelNumber = 3,
                    balls = emptyList(),
                    lines = emptyList(),
                ),
                allLevels = 30,
            ),
            onBackClick = {},
            onBallDrag = { _, _ -> },
            onDragEnd = {},
            onRestartAllLevelsClick = {},
            onRestartLoadClick = {},
        )
    }
}

@Composable
fun GameContent(
    state: GameState,
    modifier: Modifier = Modifier,
    onRestartLoadClick: () -> Unit,
    onRestartAllLevelsClick: () -> Unit,
    onBackClick: () -> Unit,
    onBallDrag: (Ball, Offset) -> Unit,
    onDragEnd: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = RoseE2ABF5),
    ) {
        IconButton(
            modifier = Modifier.padding(20.dp),
            onClick = onBackClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null,
                tint = White,
            )
        }
        when {
            state.loading -> {
                Loader(Modifier.fillMaxSize())
            }
            state.loadError != null -> {
                ErrorLayout(
                    modifier = Modifier.fillMaxSize(),
                    onClick = onRestartLoadClick,
                )
            }
            state.allLevelsError != null -> {
                ErrorLayout(
                    modifier = Modifier.fillMaxSize(),
                    onClick = onRestartAllLevelsClick,
                )
            }
            else -> {
                GameScreen(
                    state = state,
                    modifier = Modifier.fillMaxSize(),
                    onBallDrag = onBallDrag,
                    onDragEnd = onDragEnd,
                )
            }
        }
    }
}