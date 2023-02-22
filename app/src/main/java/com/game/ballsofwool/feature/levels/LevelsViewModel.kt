package com.game.ballsofwool.feature.levels

import com.game.ballsofwool.feature.base.MviViewModel
import com.game.ballsofwool.feature.game.Level

class LevelsViewModel : MviViewModel<LevelsState, Nothing>(LevelsState()) {

    init {
        loadLevels()
    }

    fun onLevelClick(level: Level) {

    }

    fun onPreviousClick() {
        setState {
            copy(firstLevelIndex = firstLevelIndex - 24)
        }
        validate()
    }

    fun onNextClick() {
        setState {
            copy(firstLevelIndex = firstLevelIndex + 24)
        }
        validate()
    }

    private fun loadLevels() {
        val levels = mutableListOf<Level>()
        val openLevels = mutableListOf<Level>()
        for (level in 1..100) {
            levels.add(
                Level(
                    levelNumber = level,
                    balls = emptyList(),
                    lines = emptyList(),
                )
            )
        }
        openLevels.addAll(levels.subList(0, 49))
        setState {
            copy(
                levels = levels,
                openLevels = openLevels,
            )
        }
        validate()
    }

    private fun validate() {
        setState {
            copy(
                previousVisible = firstLevelIndex >= 24,
                nextVisible = levels.size >= firstLevelIndex + 24,
            )
        }
    }
}