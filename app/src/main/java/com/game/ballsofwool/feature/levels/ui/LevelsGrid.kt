package com.game.ballsofwool.feature.levels.ui

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.data.model.Level
import com.game.ballsofwool.feature.levels.LevelsState

private const val HorizontalLevels = 8
private const val VerticalLevels = 3

@Composable
fun LevelsGrid(
    state: LevelsState,
    modifier: Modifier = Modifier,
    levelContent: @Composable BoxScope.(Level) -> Unit,
) {
    val iterator = state.levels.listIterator(state.firstLevelIndex)
    var level = iterator.next()

    Column(
        modifier = modifier.height(174.dp),
        verticalArrangement = Arrangement.spacedBy(12.dp),
    ) {
        repeat(VerticalLevels) {
            Row(
                modifier = Modifier.fillMaxWidth(),
            ) {
                repeat(HorizontalLevels) {
                    if (iterator.hasNext()) {
                        Box {
                            levelContent(level)
                        }
                        level = iterator.next()
                    }
                }
            }
        }
    }
}