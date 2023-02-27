package com.game.ballsofwool.feature.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.ripple.rememberRipple
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.ui.theme.*

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Column(
        modifier = Modifier
            .background(color = RoseE2ABF5)
            .padding(30.dp)
    ) {
        SettingButton(
            text = R.string.settings_music,
            icon = R.drawable.ic_music_off,
            onClick = {},
        )
        Spacer(Modifier.height(8.dp))
        SettingButton(
            text = R.string.settings_music,
            icon = R.drawable.ic_music_on,
            onClick = {},
        )
    }
}

@Composable
fun SettingButton(
    text: Int,
    icon: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Text(
            text = stringResource(text),
            style = Typography.h2,
            color = White,
        )
        Spacer(Modifier.height(8.dp))
        Icon(
            modifier = Modifier
                .background(color = PurpleC171BF, shape = RoundedCorner)
                .size(45.dp)
                .clip(RoundedCorner)
                .clickable(
                    interactionSource = remember { MutableInteractionSource() },
                    indication = rememberRipple(),
                    role = Role.Button,
                    onClick = onClick,
                )
                .padding(4.dp),
            painter = painterResource(icon),
            contentDescription = null,
            tint = White,
        )
    }
}