package com.game.ballsofwool.feature.levels.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.feature.game.Level
import com.game.ballsofwool.feature.levels.LevelsState
import com.game.ballsofwool.feature.menu.ui.StrokeText
import com.game.ballsofwool.ui.theme.RoseE2ABF5

@Preview(
    showBackground = true,
    device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360,
)
@Composable
private fun Preview() {
    LevelsContent(
        state = LevelsState(
            levels = listOf(
                Level(
                    levelNumber = 1,
                    balls = emptyList(),
                    lines = emptyList(),
                ),
                Level(
                    levelNumber = 2,
                    balls = emptyList(),
                    lines = emptyList(),
                ),
                Level(
                    levelNumber = 3,
                    balls = emptyList(),
                    lines = emptyList(),
                ),
            ),
            openLevels = listOf(
                Level(
                    levelNumber = 1,
                    balls = emptyList(),
                    lines = emptyList(),
                ),
                Level(
                    levelNumber = 2,
                    balls = emptyList(),
                    lines = emptyList(),
                ),
            )
        ),
        onLevelClick = {},
        onPreviousClick = {},
        onNextClick = {},
    )
}

@Composable
fun LevelsContent(
    state: LevelsState,
    modifier: Modifier = Modifier,
    onLevelClick: (Level) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .fillMaxSize()
            .background(color = RoseE2ABF5),
    ) {
        StrokeText(
            modifier = Modifier.padding(start = 300.dp, top = 85.dp),
            text = stringResource(R.string.levels_title),
            strokeColor = android.graphics.Color.WHITE,
            textColor = android.graphics.Color.rgb(193, 113, 191),
        )
        Spacer(Modifier.height(50.dp))
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            if (state.previousVisible) {
                Spacer(Modifier.weight(63f))
                LevelButton(
                    modifier = Modifier,
                    icon = R.drawable.ic_previous_button,
                    onClick = onPreviousClick,
                )
                Spacer(Modifier.width(24.dp))
            } else {
                Spacer(Modifier.weight(120f))
            }
            LevelsGrid(
                modifier = Modifier.weight(428f),
                state = state,
            ) { level: Level ->
                LevelItem(
                    level = level,
                    enabled = state.openLevels.contains(level),
                    onClick = onLevelClick,
                )
            }
            if (state.nextVisible) {
                Spacer(Modifier.width(24.dp))
                LevelButton(
                    modifier = Modifier,
                    icon = R.drawable.ic_next_button,
                    onClick = onNextClick,
                )
                Spacer(Modifier.weight(62f))
            } else {
                Spacer(Modifier.weight(119f))
            }
        }
    }
}