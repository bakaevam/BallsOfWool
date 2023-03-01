package com.game.ballsofwool.data.model

data class Level(
    val levelNumber: Int,
    val balls: List<Ball>,
    val lines: List<Line>,
)