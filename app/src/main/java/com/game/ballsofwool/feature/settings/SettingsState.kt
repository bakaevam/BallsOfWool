package com.game.ballsofwool.feature.settings

import com.game.ballsofwool.data.model.Language

data class SettingsState(
    val musicOn: Boolean = false,
    val soundOn: Boolean = false,
    val currentLanguage: Language? = null,
)