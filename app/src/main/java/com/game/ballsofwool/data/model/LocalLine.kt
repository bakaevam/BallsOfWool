package com.game.ballsofwool.data.model

data class LocalLine(
    val firstBall: Int,
    val secondBall: Int,
    val isCrossing: Boolean = false,
)