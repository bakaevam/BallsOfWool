package com.game.ballsofwool.data.source

import kotlinx.coroutines.flow.Flow

class Repository(
    private val preferences: PreferencesDataSource,
) {

    val musicOn: Flow<Boolean>
        get() = preferences.musicOn

    val soundOn: Flow<Boolean>
        get() = preferences.soundOn

    suspend fun setMusicOn(musicOn: Boolean) =
        preferences.setMusicOn(musicOn)

    suspend fun setSoundOn(soundOn: Boolean) =
        preferences.setSoundOn(soundOn)
}