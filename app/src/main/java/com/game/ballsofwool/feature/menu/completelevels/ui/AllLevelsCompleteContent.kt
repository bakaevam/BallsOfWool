package com.game.ballsofwool.feature.menu.completelevels.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.game.ballsofwool.R
import com.game.ballsofwool.feature.menu.ui.MenuButton
import com.game.ballsofwool.ui.TitleText
import com.game.ballsofwool.ui.theme.RoseE2ABF5
import com.game.ballsofwool.ui.theme.RoundedCornerDialog
import com.game.ballsofwool.ui.theme.Typography

@Composable
fun AllLevelsCompleteContent(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier
            .wrapContentWidth()
            .background(color = RoseE2ABF5, shape = RoundedCornerDialog)
            .padding(32.dp),
    ) {
        TitleText(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = R.string.game_all_level_complete,
            fontSize = 24.sp,
            style = Typography.h2,
        )
        Spacer(Modifier.height(16.dp))
        MenuButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = R.string.action_ok,
            onClick = onClick,
        )
    }
}