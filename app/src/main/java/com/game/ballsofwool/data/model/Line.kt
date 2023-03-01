package com.game.ballsofwool.data.model

data class Line(
    val firstBall: Ball,
    val secondBall: Ball,
    val isCrossing: Boolean = false,
)