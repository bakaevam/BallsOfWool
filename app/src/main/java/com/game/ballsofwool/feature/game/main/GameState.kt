package com.game.ballsofwool.feature.game.main

import com.game.ballsofwool.data.model.Level

data class GameState(
    val currentLevel: Level? = null,
    val allLevels: Int? = null,
)