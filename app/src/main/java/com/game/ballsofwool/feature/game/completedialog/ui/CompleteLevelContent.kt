package com.game.ballsofwool.feature.game.completedialog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.game.ballsofwool.R
import com.game.ballsofwool.ui.TitleText
import com.game.ballsofwool.ui.theme.RoseE2ABF5
import com.game.ballsofwool.ui.theme.RoundedCornerDialog
import com.game.ballsofwool.ui.theme.Typography

@Composable
fun CompleteLevelContent(
    modifier: Modifier = Modifier,
    onRestartClick: () -> Unit,
    onNextLevelClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .background(color = RoseE2ABF5, shape = RoundedCornerDialog)
            .padding(start = 32.dp, end = 32.dp, bottom = 32.dp, top = 16.dp),
    ) {
        TitleText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = R.string.game_level_complete,
            fontSize = 24.sp,
            style = Typography.h2,
        )
        Spacer(Modifier.height(16.dp))
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