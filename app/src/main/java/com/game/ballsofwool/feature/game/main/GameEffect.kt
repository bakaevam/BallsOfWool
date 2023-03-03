package com.game.ballsofwool.feature.game.main

sealed interface GameEffect {

    data class ShowComplete(
        val soundOn: Boolean,
    ) : GameEffect

    object ShowToastAllLevels : GameEffect

    object BallsLoaded : GameEffect
}