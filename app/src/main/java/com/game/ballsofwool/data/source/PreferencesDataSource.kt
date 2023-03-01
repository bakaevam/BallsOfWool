package com.game.ballsofwool.data.source

import android.content.Context
import androidx.datastore.preferences.core.MutablePreferences
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
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

    suspend fun setMusicOn(musicOn: Boolean) = edit { preferences ->
        preferences[KEY_MUSIC_ON] = musicOn
    }

    suspend fun setSoundOn(soundOn: Boolean) = edit { preferences ->
        preferences[KEY_SOUND_ON] = soundOn
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

        private val Context.dataStore by preferencesDataStore(name = STORE_NAME)
    }
}