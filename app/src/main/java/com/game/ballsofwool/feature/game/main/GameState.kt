package com.game.ballsofwool.feature.game.main

import com.game.ballsofwool.data.model.Level

data class GameState(
    val loading: Boolean = true,
    val allLevelsError: Throwable? = null,
    val loadError: Throwable? = null,
    val currentLevel: Level? = null,
    val allLevels: Int? = null,
)