package com.game.ballsofwool.feature.menu.completelevels.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.feature.menu.ui.MenuButton
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
        Text(
            text = stringResource(R.string.game_all_level_complete),
            style = Typography.h2,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.height(24.dp))
        MenuButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = R.string.action_ok,
            onClick = onClick,
        )
    }
}