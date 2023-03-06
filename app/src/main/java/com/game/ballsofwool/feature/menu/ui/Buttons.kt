package com.game.ballsofwool.feature.menu.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.game.ballsofwool.R
import com.game.ballsofwool.ui.TitleText
import com.game.ballsofwool.ui.theme.RoseE2ABF5
import com.game.ballsofwool.ui.theme.Typography

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Buttons(
        modifier = Modifier.background(color = RoseE2ABF5),
        onSettingsClick = {},
        onLevelsClick = {},
        onPlayClick = {},
    )
}

@Composable
fun Buttons(
    modifier: Modifier = Modifier,
    onPlayClick: () -> Unit,
    onLevelsClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    Column(
        modifier = modifier.verticalScroll(rememberScrollState()),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Spacer(Modifier.weight(1f))
        Spacer(Modifier.height(8.dp))
        Box {
            Image(
                painter = painterResource(R.drawable.img_menu_balls),
                contentDescription = null,
                modifier = Modifier
                    .align(Alignment.TopEnd)
                    .size(width = 63.dp, height = 49.dp)
            )
            TitleText(
                text = R.string.game_name,
                fontSize = 50.sp,
                style = Typography.h1,
            )
        }
        Spacer(Modifier.height(17.dp))
        MenuButton(
            text = R.string.play_button_text,
            onClick = onPlayClick,
        )
        MenuButton(
            text = R.string.levels_button_text,
            onClick = onLevelsClick,
        )
        MenuButton(
            text = R.string.settings_button_text,
            onClick = onSettingsClick,
        )
        Spacer(Modifier.weight(1f))
        Spacer(Modifier.height(16.dp))
    }
}