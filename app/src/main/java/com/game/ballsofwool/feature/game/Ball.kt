package com.game.ballsofwool.feature.game

class Ball(
    private var x: Int,
    private var y: Int,
) {
    fun setOffset(x: Int, y: Int) {
        this.x = x
        this.y = y
    }
}