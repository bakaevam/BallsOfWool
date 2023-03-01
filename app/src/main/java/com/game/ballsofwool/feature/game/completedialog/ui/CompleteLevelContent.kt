package com.game.ballsofwool.feature.game.completedialog.ui

import android.graphics.Color
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.feature.menu.ui.H2_STROKE_SIZE
import com.game.ballsofwool.feature.menu.ui.StrokeText
import com.game.ballsofwool.ui.theme.RoseE2ABF5
import com.game.ballsofwool.ui.theme.RoundedCornerDialog

@Composable
fun CompleteLevelContent(
    modifier: Modifier = Modifier,
    onRestartClick: () -> Unit,
    onNextLevelClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .wrapContentWidth()
            .background(color = RoseE2ABF5, shape = RoundedCornerDialog)
            .padding(start = 32.dp, end = 32.dp, bottom = 32.dp, top = 64.dp),
    ) {
        StrokeText(
            text = stringResource(R.string.game_level_complete),
            strokeColor = Color.WHITE,
            textColor = Color.rgb(193, 113, 191),
            size = H2_STROKE_SIZE,
        )
        Spacer(Modifier.height(32.dp))
        Row() {
            Spacer(Modifier.weight(1f))
            CompleteLevelButton(
                icon = R.drawable.ic_restart,
                onClick = onRestartClick,
            )
            Spacer(Modifier.width(36.dp))
            CompleteLevelButton(
                icon = R.drawable.ic_next_level,
                onClick = onNextLevelClick,
            )
            Spacer(Modifier.weight(1f))
        }
    }
}