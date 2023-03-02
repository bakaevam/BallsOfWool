package com.game.ballsofwool.feature.levels

sealed interface LevelsEffect {

    object ClickSound : LevelsEffect

    data class PlayLevel(
        val level: Int,
    ) : LevelsEffect
}