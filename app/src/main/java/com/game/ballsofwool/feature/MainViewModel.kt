package com.game.ballsofwool.feature

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.game.ballsofwool.data.source.Repository
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.launch

class MainViewModel(
    private val repository: Repository,
) : ViewModel() {

    private val _effect = MutableSharedFlow<MainEffect>()
    val effect: SharedFlow<MainEffect> = _effect.asSharedFlow()

    init {
        viewModelScope.launch {
            val currentLanguage = repository.selectedLanguage.first()
            if (currentLanguage != "") {
                _effect.emit(MainEffect.ChangeLanguage(currentLanguage))
            }
        }
    }
}