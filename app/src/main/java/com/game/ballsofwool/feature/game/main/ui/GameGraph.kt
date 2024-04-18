package com.game.ballsofwool.feature.game.main.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.data.model.Ball
import com.game.ballsofwool.data.model.Level
import com.game.ballsofwool.ext.dpToPx
import kotlin.math.roundToInt

private const val WIDTH_LINE = 10F
private val CROSSING_COLOR = Color(51, 83, 95)
private val NOT_CROSSING_COLOR = Color(193, 113, 191)
private val BALL_WIDTH = 41.dp
private val BALL_HEIGHT = 40.dp

@Composable
fun GameGraph(
    level: Level,
    modifier: Modifier = Modifier,
    onBallDrag: (Ball, Offset) -> Unit,
    onDragEnd: () -> Unit,
) {
    Box(
        modifier = modifier,
    ) {
        val xOffset = (BALL_WIDTH.dpToPx() / 2)
        val yOffset = (BALL_HEIGHT.dpToPx() / 2)
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            level.lines.forEach { line ->
                val color = when {
                    line.isCrossing -> CROSSING_COLOR
                    else -> NOT_CROSSING_COLOR
                }
                val firstBall = level.balls.find { it.id == line.firstBall }
                val secondBall = level.balls.find { it.id == line.secondBall }
                if (firstBall != null && secondBall != null) {
                    val xStart = firstBall.x + xOffset
                    val yStart = firstBall.y + yOffset
                    val xEnd = secondBall.x + xOffset
                    val yEnd = secondBall.y + yOffset
                    drawLine(
                        start = Offset(x = xStart, y = yStart),
                        end = Offset(x = xEnd, y = yEnd),
                        color = color,
                        strokeWidth = WIDTH_LINE,
                    )
                }
            }
        }
        level.balls.forEach { ball ->
            Image(
                modifier = Modifier
                    .size(width = BALL_WIDTH, height = BALL_HEIGHT)
                    .offset { IntOffset(ball.x.roundToInt(), ball.y.roundToInt()) }
                    .pointerInput(level.levelNumber) {
                        detectDragGestures(
                            onDrag = { _, delta ->
                                onBallDrag(ball, delta)
                            },
                            onDragEnd = onDragEnd,
                        )
                    },
                painter = painterResource(R.drawable.img_ball),
                contentDescription = null,
            )
        }
    }
}
