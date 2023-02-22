package com.game.ballsofwool.feature.game

data class Level(
    val levelNumber: Int,
    val balls: List<Ball>,
    val lines: List<Line>,
)