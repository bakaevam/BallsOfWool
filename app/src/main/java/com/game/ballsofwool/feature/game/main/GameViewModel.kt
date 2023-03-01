package com.game.ballsofwool.feature.game.main

import androidx.compose.ui.geometry.Offset
import com.game.ballsofwool.data.model.Ball
import com.game.ballsofwool.data.model.Level
import com.game.ballsofwool.data.model.Line
import com.game.ballsofwool.feature.base.MviViewModel
import com.game.ballsofwool.utils.LinesCrossingCalculator

class GameViewModel : MviViewModel<GameState, GameEffect>(
    GameState(
        allLevels = 30
    )
) {

    init {
        getLevel()
    }

    fun onRestartClick() = getLevel()

    fun onNextLevelClick() = getNextLevel()

    fun onBallDrag(ball: Ball, delta: Offset) {
        val level = state.value.currentLevel ?: return
        setState {
            val actualBalls = level.balls.toMutableList()
            val dragBallIndex = actualBalls.indexOfFirst { it.id == ball.id }

            val dragBall = actualBalls[dragBallIndex]
            val newBall = dragBall.copy(x = dragBall.x + delta.x, y = dragBall.y + delta.y)
            actualBalls[dragBallIndex] = newBall

            val actualLines = level.lines.toMutableList()
            actualLines.forEachIndexed { index, line ->
                if (line.firstBall.id == ball.id || line.secondBall.id == ball.id) {
                    val dragLine = actualLines[index]
                    actualLines[index] = when (dragLine.firstBall.id) {
                        ball.id -> dragLine.copy(firstBall = newBall)
                        else -> dragLine.copy(secondBall = newBall)
                    }
                }
            }

            copy(
                currentLevel = level.copy(balls = actualBalls, lines = actualLines)
            )
        }
        validate()
    }

    fun onDragEnd() = checkReadyLevel()

    private fun getLevel() {
        setState {
            val firstBall = Ball(1, 0F, 0F)
            val secondBall = Ball(2, 500F, 500F)
            val thirdBall = Ball(3, 250F, 50F)
            val crossingBall = Ball(4, 250F, 450F)
            copy(
                currentLevel = Level(
                    levelNumber = 3,
                    balls = listOf(
                        firstBall,
                        secondBall,
                        thirdBall,
                        crossingBall,
                    ),
                    lines = listOf(
                        Line(
                            firstBall,
                            secondBall,
                        ),
                        Line(
                            secondBall,
                            thirdBall,
                        ),
                        Line(
                            thirdBall,
                            crossingBall,
                        ),
                        Line(
                            firstBall,
                            crossingBall,
                        )
                    ),
                ),
            )
        }
        validate()
    }

    private fun getNextLevel() {
        getLevel()
    }

    private fun checkReadyLevel() {
        val crossingLine = state.value.currentLevel?.lines?.find { it.isCrossing }
        if (crossingLine == null) {
            postEffect(GameEffect.ShowComplete)
        }
    }

    private fun validate() {
        setState {
            val crossingLines = when {
                currentLevel != null -> LinesCrossingCalculator.detectCrossing(currentLevel.lines)
                else -> emptyList()
            }
            copy(currentLevel = currentLevel?.copy(lines = crossingLines))
        }
    }
}