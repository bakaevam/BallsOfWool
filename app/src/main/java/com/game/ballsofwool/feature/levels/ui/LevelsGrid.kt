package com.game.ballsofwool.feature.levels.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.feature.levels.LevelsState

private const val HorizontalLevels = 8
private const val VerticalLevels = 3

@Composable
fun LevelsGrid(
    state: LevelsState,
    modifier: Modifier = Modifier,
    levelContent: @Composable BoxScope.(Int) -> Unit,
) {
    Column(
        modifier = modifier.height(174.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
        horizontalAlignment = Alignment.Start,
    ) {
        if (state.levels != null) {
            var level = state.firstLevelIndex
            var verticalCounter = 1

            while (level <= state.levels && verticalCounter <= VerticalLevels) {
                var horizontalCounter = 1

                Row(
                    horizontalArrangement = Arrangement.spacedBy(12.dp),
                ) {
                    while (horizontalCounter <= HorizontalLevels && level <= state.levels) {
                        Box {
                            levelContent(level)
                        }
                        if (level <= state.levels) {
                            level++
                        }
                        horizontalCounter++
                    }
                }
                verticalCounter++
            }
        }
    }
}