package com.game.ballsofwool.feature.settings

import androidx.lifecycle.viewModelScope
import com.game.ballsofwool.data.Repository
import com.game.ballsofwool.feature.base.MviViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: Repository,
) : MviViewModel<SettingsState, SettingsEffect>(SettingsState()) {

    init {
        viewModelScope.launch {
            repository.musicOn.collect {
                setState { copy(musicOn = it) }
            }
        }
        viewModelScope.launch {
            repository.soundOn.collect {
                setState { copy(soundOn = it) }
            }
        }
    }

    fun onSoundClick() {
        viewModelScope.launch {
            val soundOn = !state.value.soundOn
            repository.setSoundOn(soundOn)
            setState {
                clickSound(soundOn)
                copy(soundOn = soundOn)
            }
        }
    }

    fun onMusicClick() {
        viewModelScope.launch {
            clickSound(repository.soundOn.first())
            val musicOn = !state.value.musicOn
            repository.setMusicOn(musicOn)
            setState {
                copy(musicOn = musicOn)
            }
            when {
                musicOn -> postEffect(SettingsEffect.PlayMusic)
                else -> postEffect(SettingsEffect.StopMusic)
            }
        }
    }

    private fun clickSound(soundOn: Boolean) {
        if (soundOn) {
            postEffect(SettingsEffect.ClickSound)
        }
    }
}