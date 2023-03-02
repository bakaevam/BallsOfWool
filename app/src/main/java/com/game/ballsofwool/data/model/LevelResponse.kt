package com.game.ballsofwool.data.model

class LevelResponse(
    var balls: List<Ball> = emptyList(),
    var levelNumber: Int = 0,
    var lines: List<Line> = emptyList(),
)