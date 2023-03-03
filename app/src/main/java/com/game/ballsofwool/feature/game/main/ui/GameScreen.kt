package com.game.ballsofwool.feature.game.main.ui

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.data.model.Ball
import com.game.ballsofwool.feature.game.main.GameState
import com.game.ballsofwool.ui.theme.Typography
import com.game.ballsofwool.ui.theme.White

@Composable
fun GameScreen(
    state: GameState,
    modifier: Modifier = Modifier,
    onBallDrag: (Ball, Offset) -> Unit,
    onDragEnd: () -> Unit,
) {
    Box(modifier = modifier) {
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