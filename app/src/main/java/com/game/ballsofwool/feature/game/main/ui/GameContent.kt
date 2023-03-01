package com.game.ballsofwool.feature.game.main.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.data.model.Ball
import com.game.ballsofwool.data.model.Level
import com.game.ballsofwool.feature.game.main.GameState
import com.game.ballsofwool.ui.theme.RoseE2ABF5
import com.game.ballsofwool.ui.theme.Typography
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
        )
    }
}

@Composable
fun GameContent(
    state: GameState,
    modifier: Modifier = Modifier,
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
        if (state.currentLevel != null && state.allLevels != null) {
            Text(
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .padding(32.dp),
                text = stringResource(
                    R.string.game_levels,
                    state.currentLevel.levelNumber,
                    state.allLevels,
                ),
                style = Typography.h2,
                color = White,
            )
        }
        if (state.currentLevel != null) {
            GameGraph(
                modifier = Modifier.fillMaxSize(),
                level = state.currentLevel,
                onBallDrag = onBallDrag,
                onDragEnd = onDragEnd,
            )
        }
    }
}