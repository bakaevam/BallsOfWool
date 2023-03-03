package com.game.ballsofwool.feature.levels.ui

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
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.ui.theme.*

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Row(
        modifier = Modifier
            .background(color = RoseE2ABF5)
            .padding(50.dp),
    ) {
        LevelItem(
            level = 21,
            enabled = true,
            onClick = {},
        )
        Spacer(Modifier.width(12.dp))
        LevelItem(
            level = 21,
            enabled = false,
            onClick = {},
        )
    }
}

@Composable
fun LevelItem(
    level: Int,
    enabled: Boolean,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
) {
    when {
        enabled -> {
            EnabledLevel(
                modifier = modifier,
                level = level,
                onClick = onClick,
            )
        }
        else -> {
            CloseLevel()
        }
    }
}

@Composable
fun EnabledLevel(
    level: Int,
    modifier: Modifier = Modifier,
    onClick: (Int) -> Unit,
) {
    Box(
        modifier = modifier
            .shadow(elevation = 45.dp)
            .size(50.dp)
            .clip(RoundedCorner)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                role = Role.Button,
                onClick = { onClick(level) },
            )
            .background(shape = RoundedCorner, color = White),
    ) {
        Text(
            modifier = Modifier.align(Alignment.Center),
            text = level.toString(),
            style = Typography.button,
        )
    }
}

@Composable
fun CloseLevel(
    modifier: Modifier = Modifier,
) {
    Box(
        modifier = modifier
            .size(50.dp)
            .background(shape = RoundedCorner, color = GrayE8E8E8),
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(R.drawable.ic_lock_level),
            contentDescription = null,
            tint = PurpleB561B7,
        )
    }
}