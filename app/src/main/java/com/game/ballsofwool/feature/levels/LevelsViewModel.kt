package com.game.ballsofwool.feature.levels

import androidx.lifecycle.viewModelScope
import com.game.ballsofwool.data.Repository
import com.game.ballsofwool.feature.base.MviViewModel
import com.game.ballsofwool.feature.game.Level
import kotlinx.coroutines.launch

class LevelsViewModel(
    private val repository: Repository,
) : MviViewModel<LevelsState, LevelsEffect>(LevelsState()) {

    init {
        loadLevels()
    }

    fun onLevelClick(level: Level) {
        clickSound()
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

    private fun clickSound() {
        viewModelScope.launch {
            repository.soundOn.collect {
                if (it) {
                    postEffect(LevelsEffect.ClickSound)
                }
            }
        }
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