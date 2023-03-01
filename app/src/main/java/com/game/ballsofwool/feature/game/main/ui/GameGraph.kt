package com.game.ballsofwool.feature.game.main.ui

import androidx.compose.foundation.Canvas
import androidx.compose.foundation.Image
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ImageBitmap
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.res.imageResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.IntOffset
import com.game.ballsofwool.R
import com.game.ballsofwool.data.model.Ball
import com.game.ballsofwool.data.model.Level
import kotlin.math.roundToInt

private const val WIDTH_LINE = 10F
private val CROSSING_COLOR = Color(51, 83, 95)
private val NOT_CROSSING_COLOR = Color(193, 113, 191)

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
        val image = ImageBitmap.imageResource(R.drawable.img_ball)
        val xOffset = image.width / 2
        val yOffset = image.height / 2
        Canvas(
            modifier = Modifier.fillMaxSize()
        ) {
            level.lines.forEach { line ->
                val color = when {
                    line.isCrossing -> CROSSING_COLOR
                    else -> NOT_CROSSING_COLOR
                }
                val xStart = line.firstBall.x + xOffset
                val yStart = line.firstBall.y + yOffset
                val xEnd = line.secondBall.x + xOffset
                val yEnd = line.secondBall.y + yOffset
                drawLine(
                    start = Offset(x = xStart, y = yStart),
                    end = Offset(x = xEnd, y = yEnd),
                    color = color,
                    strokeWidth = WIDTH_LINE,
                )
            }
        }
        level.balls.forEach { ball ->
            Image(
                modifier = Modifier
                    .offset { IntOffset(ball.x.roundToInt(), ball.y.roundToInt()) }
                    .pointerInput(Unit) {
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
