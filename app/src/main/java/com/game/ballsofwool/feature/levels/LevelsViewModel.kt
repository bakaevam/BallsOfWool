package com.game.ballsofwool.feature.levels

import androidx.lifecycle.viewModelScope
import com.game.ballsofwool.data.db.FirebaseDatabaseRepositoryImpl
import com.game.ballsofwool.data.source.Repository
import com.game.ballsofwool.feature.base.MviViewModel
import kotlinx.coroutines.launch

class LevelsViewModel(
    private val repository: Repository,
    private val db: FirebaseDatabaseRepositoryImpl,
) : MviViewModel<LevelsState, LevelsEffect>(LevelsState()) {

    init {
        loadLevels()
    }

    fun onLevelClick(level: Int) {
        postEffect(LevelsEffect.PlayLevel(level))
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

    private fun getAllLevelsCount() {
        viewModelScope.launch {
            db.getLevelsCount()
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        val allLevels = task.result.count.toInt()
                        setState {
                            copy(levels = allLevels)
                        }
                    }
                }
        }
    }

    private fun loadLevels() {
        getAllLevelsCount()
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
                nextVisible = levels != null && levels >= firstLevelIndex + 24,
            )
        }
    }
}