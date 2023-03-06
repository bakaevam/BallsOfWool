package com.game.ballsofwool.feature.settings

import androidx.lifecycle.viewModelScope
import com.game.ballsofwool.data.model.Language
import com.game.ballsofwool.data.source.Repository
import com.game.ballsofwool.feature.base.MviViewModel
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class SettingsViewModel(
    private val repository: Repository,
) : MviViewModel<SettingsState, SettingsEffect>(SettingsState()) {

    private val languages = Language.values()

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
        viewModelScope.launch {
            repository.selectedLanguage.collect {
                setState { copy(currentLanguage = Language.getLanguage(it)) }
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

    fun onNextClick() {
        val indexLanguage = languages.indexOf(state.value.currentLanguage)
        val newLanguage = when (indexLanguage) {
            languages.size - 1 -> languages.first()
            else -> languages[indexLanguage + 1]
        }
        changeApplicationLanguage(newLanguage.code)
    }

    fun onPreviousClick() {
        val indexLanguage = languages.indexOf(state.value.currentLanguage)
        val newLanguage = when (indexLanguage) {
            0 -> languages.last()
            else -> languages[indexLanguage - 1]
        }
        changeApplicationLanguage(newLanguage.code)
    }

    private fun changeApplicationLanguage(language: String) {
        viewModelScope.launch {
            repository.setSelectedLanguage(language)
            postEffect(SettingsEffect.UpdateLanguage(language))
        }
    }

    private fun clickSound(soundOn: Boolean) {
        if (soundOn) {
            postEffect(SettingsEffect.ClickSound)
        }
    }
}