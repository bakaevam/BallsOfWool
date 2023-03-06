package com.game.ballsofwool.data.source

import kotlinx.coroutines.flow.Flow

class Repository(
    private val preferences: PreferencesDataSource,
) {

    val musicOn: Flow<Boolean>
        get() = preferences.musicOn

    val soundOn: Flow<Boolean>
        get() = preferences.soundOn

    val lastOpenLevel: Flow<Int>
        get() = preferences.lastOpenLevel

    val selectedLanguage: Flow<String>
        get() = preferences.selectedLanguage

    val showedManual: Flow<Boolean>
        get() = preferences.showedManual

    suspend fun setMusicOn(musicOn: Boolean) =
        preferences.setMusicOn(musicOn)

    suspend fun setSoundOn(soundOn: Boolean) =
        preferences.setSoundOn(soundOn)

    suspend fun setLastOpenLevel(number: Int) =
        preferences.setLastOpenLevel(number)

    suspend fun setSelectedLanguage(language: String) =
        preferences.setSelectedLanguage(language)

    suspend fun setShowedManual(showedManual: Boolean) =
        preferences.setShowedManual(showedManual)
}