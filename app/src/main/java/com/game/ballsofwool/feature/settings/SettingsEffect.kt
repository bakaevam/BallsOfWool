package com.game.ballsofwool.feature.settings

sealed interface SettingsEffect {

    object PlayMusic : SettingsEffect
    object StopMusic : SettingsEffect
    object ClickSound : SettingsEffect
    data class UpdateLanguage(
        val language: String,
    ) : SettingsEffect
}