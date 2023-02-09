package com.example.ballsofwool.feature.menu.ui

import android.graphics.Color
import android.graphics.Typeface
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Paint
import androidx.compose.ui.graphics.drawscope.drawIntoCanvas
import androidx.compose.ui.graphics.nativeCanvas
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.ballsofwool.R
import com.example.ballsofwool.ui.theme.RoseE2ABF5

@Preview(showBackground = true)
@Composable
private fun Preview() {
    StrokeText(
        modifier = Modifier.background(color = RoseE2ABF5),
        text = stringResource(R.string.game_name),
        strokeColor = Color.WHITE,
        textColor = Color.rgb(255, 193, 113),
    )
}

@Composable
fun StrokeText(
    text: String,
    strokeColor: Int,
    textColor: Int,
    modifier: Modifier = Modifier,
) {
    val textPaintStroke = Paint().asFrameworkPaint().apply {
        isAntiAlias = true
        style = android.graphics.Paint.Style.STROKE
        textSize = 150f
        color = strokeColor
        strokeWidth = 30f
        strokeMiter= 10f
        strokeJoin = android.graphics.Paint.Join.ROUND
        typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
    }

    val textPaint = Paint().asFrameworkPaint().apply {
        style = android.graphics.Paint.Style.FILL_AND_STROKE
        textSize = 150f
        color = textColor
        typeface = Typeface.create(Typeface.SANS_SERIF, Typeface.BOLD)
    }
    Canvas(
        modifier = modifier,
        onDraw = {
            drawIntoCanvas { canvas ->
                canvas.nativeCanvas.drawText(
                    text,
                    0f,
                    0.dp.toPx(),
                    textPaintStroke
                )
                canvas.nativeCanvas.drawText(
                    text,
                    0f,
                    0.dp.toPx(),
                    textPaint
                )
            }
        }
    )
}