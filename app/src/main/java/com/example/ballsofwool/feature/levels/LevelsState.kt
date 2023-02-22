package com.example.ballsofwool.feature.levels

import com.example.ballsofwool.feature.game.Level

data class LevelsState(
    val levels: List<Level> = emptyList(),
    val openLevels: List<Level> = emptyList(),
    val firstLevelIndex: Int = 0,
    val previousVisible: Boolean = false,
    val nextVisible: Boolean = false,
)