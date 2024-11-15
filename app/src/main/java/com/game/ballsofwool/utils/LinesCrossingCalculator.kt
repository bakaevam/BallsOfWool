package com.game.ballsofwool.utils

import com.game.ballsofwool.data.model.Ball
import com.game.ballsofwool.data.model.LocalLine
import com.game.ballsofwool.utils.LinesCrossingCalculator.Vector.Companion.crs


object LinesCrossingCalculator {

    fun detectCrossing(lines: List<LocalLine>, balls: List<Ball>): List<LocalLine> {
        val resultLines = lines.toMutableList()
        lines.forEachIndexed { index, line ->
            lines.forEach { anotherLine ->
                val neighbour = line.firstBall == anotherLine.firstBall
                    || line.firstBall == anotherLine.secondBall
                    || line.secondBall == anotherLine.firstBall
                    || line.secondBall == anotherLine.secondBall
                val firstBall = balls.find { it.id == line.firstBall }
                val secondBall = balls.find { it.id == line.secondBall }
                val firstBallAnother = balls.find { it.id == anotherLine.firstBall }
                val secondBallAnother = balls.find { it.id == anotherLine.secondBall }

                if (!neighbour
                    && firstBall != null
                    && secondBall != null
                    && firstBallAnother != null
                    && secondBallAnother != null
                ) {
                    val crossing = isCrossing(
                        firstBall,
                        secondBall,
                        firstBallAnother,
                        secondBallAnother,
                    )
                    resultLines[index] = line.copy(isCrossing = crossing)
                    if (crossing) {
                        return@forEachIndexed
                    }
                }
            }
        }
        return resultLines
    }

    private fun isCrossing(a: Ball, b: Ball, c: Ball, d: Ball): Boolean {
        var line = Vector(a, b)
        var leftLine = Vector(a, c)
        var rightLine = Vector(a, d)

        if (fromDifferentSides(line, leftLine, rightLine)) {
            line = Vector(c, d)
            leftLine = Vector(c, a)
            rightLine = Vector(c, b)
            return fromDifferentSides(
                line,
                leftLine,
                rightLine,
            )
        }
        return false
    }

    private fun fromDifferentSides(main: Vector, left: Vector, right: Vector): Boolean {
        val leftProduct = crs(main, left)
        val rightProduct = crs(main, right)
        return leftProduct >= 0 && rightProduct <= 0
            || leftProduct <= 0 && rightProduct >= 0
    }

    private class Vector(start: Ball, end: Ball) {
        var x: Float
        var y: Float

        init {
            x = end.x - start.x
            y = end.y - start.y
        }

        companion object {

            // Векторное произведение
            fun crs(v1: Vector, v2: Vector): Float {
                return v1.x * v2.y - v1.y * v2.x
            }
        }
    }
}