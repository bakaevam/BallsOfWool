package com.game.ballsofwool.data.source

import android.content.Context
import androidx.datastore.preferences.core.*
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

class PreferencesDataSource(
    private val context: Context,
) {

    val musicOn: Flow<Boolean> =
        flowOf(KEY_MUSIC_ON, false)

    val soundOn: Flow<Boolean> =
        flowOf(KEY_SOUND_ON, false)

    val lastOpenLevel: Flow<Int> =
        flowOf(KEY_LAST_OPEN_LEVEL, 1)

    suspend fun setMusicOn(musicOn: Boolean) = edit { preferences ->
        preferences[KEY_MUSIC_ON] = musicOn
    }

    suspend fun setSoundOn(soundOn: Boolean) = edit { preferences ->
        preferences[KEY_SOUND_ON] = soundOn
    }

    suspend fun setLastOpenLevel(levelNumber: Int) = edit { preferences ->
        preferences[KEY_LAST_OPEN_LEVEL] = levelNumber
    }

    private fun <T> flowOf(
        key: Preferences.Key<T>,
        defaultValue: T,
    ): Flow<T> = context.dataStore.data.map { it[key] ?: defaultValue }

    private suspend inline fun edit(
        crossinline transform: suspend (MutablePreferences) -> Unit
    ) {
        context.dataStore.edit { transform(it) }
    }

    private companion object {

        private const val STORE_NAME = "default"
        private val KEY_MUSIC_ON = booleanPreferencesKey("music_on")
        private val KEY_SOUND_ON = booleanPreferencesKey("sound_on")
        private val KEY_LAST_OPEN_LEVEL = intPreferencesKey("last_open_level")

        private val Context.dataStore by preferencesDataStore(name = STORE_NAME)
    }
}