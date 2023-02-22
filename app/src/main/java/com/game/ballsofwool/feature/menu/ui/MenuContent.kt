package com.game.ballsofwool.feature.menu.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.ui.theme.RoseE2ABF5

@Preview(
    showBackground = true,
    device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360,
)
@Composable
private fun Preview() {
    MenuContent(
        onPlayClick = {},
        onLevelsClick = {},
        onSettingsClick = {},
    )
}

@Composable
fun MenuContent(
    onPlayClick: () -> Unit,
    onLevelsClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxSize()
            .background(color = RoseE2ABF5),
        verticalAlignment = Alignment.CenterVertically,
    ) {
        Spacer(Modifier.weight(1f))
        Image(
            painter = painterResource(R.drawable.img_menu_grandma),
            contentDescription = null,
            alignment = Alignment.CenterStart,
            modifier = Modifier.size(288.dp, 258.dp),
        )
        Spacer(Modifier.weight(0.2f))
        Box {
            Image(
                painter = painterResource(R.drawable.img_menu_balls),
                contentDescription = null,
                modifier = Modifier
                    .padding(start = 210.dp, top = 35.dp)
                    .size(width = 63.dp, height = 49.dp)
            )
            Buttons(
                onPlayClick = onPlayClick,
                onLevelsClick = onLevelsClick,
                onSettingsClick = onSettingsClick,
            )
        }
        Spacer(Modifier.weight(1f))
    }
}
