package com.game.ballsofwool.ui

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.feature.menu.ui.H2_STROKE_SIZE
import com.game.ballsofwool.feature.menu.ui.MenuButton
import com.game.ballsofwool.feature.menu.ui.StrokeText
import com.game.ballsofwool.ui.theme.RoseE2ABF5

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Box(
        modifier = Modifier
            .background(color = RoseE2ABF5)
            .padding(50.dp),
    ) {
        ErrorLayout(
            modifier = Modifier.fillMaxSize(),
            onClick = { }
        )
    }
}

@Composable
fun ErrorLayout(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        StrokeText(
            modifier = Modifier.padding(start = 200.dp),
            text = stringResource(R.string.error_title),
            strokeColor = Color.WHITE,
            textColor = Color.rgb(193, 113, 191),
            size = H2_STROKE_SIZE
        )
        Spacer(Modifier.height(45.dp))
        MenuButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = R.string.error_repeat,
            onClick = onClick,
        )
    }
}