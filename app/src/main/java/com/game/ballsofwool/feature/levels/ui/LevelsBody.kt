package com.game.ballsofwool.feature.levels.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.feature.levels.LevelsState

@Preview(
    showBackground = true,
    device = Devices.AUTOMOTIVE_1024p, widthDp = 1080, heightDp = 720,
)
@Composable
private fun Preview() {
    LevelsBody(
        state = LevelsState(
            levels = 3,
            lastOpenLevel = 2,
            nextVisible = true,
            previousVisible = true
        ),
        onLevelClick = {},
        onPreviousClick = {},
        onNextClick = {},
    )
}

@Composable
fun LevelsBody(
    state: LevelsState,
    modifier: Modifier = Modifier,
    onLevelClick: (Int) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Column(modifier = modifier) {
        Spacer(Modifier.height(135.dp))
        Box(
            modifier = Modifier
                .fillMaxWidth()
                .align(Alignment.CenterHorizontally),
        ) {
            if (state.previousVisible) {
                LevelButton(
                    modifier = Modifier
                        .align(Alignment.CenterStart)
                        .padding(start = 63.dp),
                    icon = R.drawable.ic_previous_button,
                    onClick = onPreviousClick,
                )
            }
            LevelsGrid(
                modifier = Modifier
                    .defaultMinSize(minWidth = 428.dp)
                    .align(Alignment.Center),
                state = state,
            ) { level: Int ->
                LevelItem(
                    level = level,
                    enabled = level <= state.lastOpenLevel,
                    onClick = onLevelClick,
                )
            }
            if (state.nextVisible) {
                LevelButton(
                    modifier = Modifier
                        .align(Alignment.CenterEnd)
                        .padding(end = 62.dp),
                    icon = R.drawable.ic_next_button,
                    onClick = onNextClick,
                )
            }
        }
    }
}