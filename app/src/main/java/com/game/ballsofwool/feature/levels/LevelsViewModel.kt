package com.game.ballsofwool.feature.levels

import androidx.lifecycle.viewModelScope
import com.game.ballsofwool.data.db.FirebaseDatabaseRepositoryImpl
import com.game.ballsofwool.data.source.Repository
import com.game.ballsofwool.feature.base.MviViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch
import timber.log.Timber

class LevelsViewModel(
    private val repository: Repository,
    private val db: FirebaseDatabaseRepositoryImpl,
) : MviViewModel<LevelsState, LevelsEffect>(LevelsState()) {

    fun onResume() {
        setLastOpenLevel()
        loadLevels()
    }

    fun onRestartClick() = loadLevels()

    fun onLevelClick(level: Int) {
        postEffect(LevelsEffect.PlayLevel(level))
        clickSound()
    }

    fun onPreviousClick() {
        setState {
            copy(firstLevelIndex = firstLevelIndex - LEVELS_PAGE_COUNT)
        }
        validate()
    }

    fun onNextClick() {
        setState {
            copy(firstLevelIndex = firstLevelIndex + LEVELS_PAGE_COUNT)
        }
        validate()
    }

    private fun setLastOpenLevel() {
        viewModelScope.launch {
            val level = repository.lastOpenLevel.first()
            setState {
                copy(lastOpenLevel = level)
            }
        }
    }

    private fun getAllLevelsCount() {
        viewModelScope.launch {
            db.getLevelsCount()
                .addOnFailureListener {
                    Timber.e(it, "error load levels count")
                    setState { copy(loadError = it, loading = false) }
                }
                .addOnCompleteListener { task ->
                    setState { copy(loading = true, loadError = null) }
                    if (task.isSuccessful) {
                        val allLevels = task.result.count.toInt()
                        setState {
                            copy(
                                levels = allLevels,
                                loading = false,
                            )
                        }
                        validate()
                    } else {
                        Timber.e(task.exception, "error load all levels count")
                        setState { copy(loadError = task.exception, loading = false) }
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

    companion object {

        private const val LEVELS_PAGE_COUNT = 24
    }
}