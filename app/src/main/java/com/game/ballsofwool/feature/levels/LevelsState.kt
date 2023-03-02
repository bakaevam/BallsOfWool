package com.game.ballsofwool.feature.levels

data class LevelsState(
    val levels: Int? = null,
    val lastOpenLevel: Int = 1,
    val firstLevelIndex: Int = 0,
    val previousVisible: Boolean = false,
    val nextVisible: Boolean = false,
)