package com.game.ballsofwool.feature.game.completedialog.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Icon
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
import com.game.ballsofwool.ui.theme.PurpleB561B7
import com.game.ballsofwool.ui.theme.RoseE2ABF5
import com.game.ballsofwool.ui.theme.RoundedCorner
import com.game.ballsofwool.ui.theme.White

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Row(
        modifier = Modifier
            .background(color = RoseE2ABF5)
            .padding(50.dp),
    ) {
        CompleteLevelButton(
            icon = R.drawable.ic_restart,
            onClick = {},
        )
    }
}

@Composable
fun CompleteLevelButton(
    icon: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .shadow(elevation = 45.dp)
            .size(43.dp)
            .clip(RoundedCorner)
            .clickable(
                interactionSource = remember { MutableInteractionSource() },
                indication = rememberRipple(),
                role = Role.Button,
                onClick = onClick,
            )
            .background(shape = RoundedCorner, color = White),
    ) {
        Icon(
            modifier = Modifier.align(Alignment.Center),
            painter = painterResource(icon),
            contentDescription = null,
            tint = PurpleB561B7,
        )
    }
}