package com.game.ballsofwool.feature.levels.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.feature.levels.LevelsState

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
            ) { level: Int ->
                LevelItem(
                    level = level,
                    enabled = level <= state.lastOpenLevel,
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