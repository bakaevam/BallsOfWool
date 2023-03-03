package com.game.ballsofwool.feature.menu

import androidx.lifecycle.viewModelScope
import com.game.ballsofwool.data.source.Repository
import com.game.ballsofwool.feature.base.MviViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MenuViewModel(
    private val repository: Repository,
) : MviViewModel<MenuState, MenuEffect>(MenuState()) {

    init {
        viewModelScope.launch {
            repository.musicOn.collect {
                if (it) {
                    postEffect(MenuEffect.PlayMusic)
                }
            }
        }
    }

    fun onPlayClick() {
        clickSound()
        postEffect(MenuEffect.NavigateToGame)
    }

    fun onLevelsClick() {
        clickSound()
        postEffect(MenuEffect.NavigateToLevels)
    }

    fun onSettingsClick() {
        clickSound()
        postEffect(MenuEffect.NavigateToSettings)
    }

    fun onGameFragmentResult() {
        viewModelScope.launch {
            postEffect(MenuEffect.ShowAllLevelsComplete(repository.soundOn.first()))
        }
    }

    private fun clickSound() {
        viewModelScope.launch {
            val soundOn = repository.soundOn.first()
            if (soundOn) {
                postEffect(MenuEffect.ClickSound)
            }
        }
    }
}