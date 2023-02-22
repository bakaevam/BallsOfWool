package com.game.ballsofwool.feature.menu.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.ui.theme.RoseE2ABF5

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
        modifier = modifier,
    ) {
        Spacer(Modifier.weight(1f))
        StrokeText(
            text = stringResource(R.string.game_name),
            strokeColor = android.graphics.Color.WHITE,
            textColor = android.graphics.Color.rgb(193, 113, 191),
        )
        Spacer(Modifier.height(25.dp))
        MenuButton(
            modifier = Modifier.padding(start = 43.dp),
            text = R.string.play_button_text,
            onClick = onPlayClick,
        )
        MenuButton(
            modifier = Modifier.padding(start = 43.dp),
            text = R.string.levels_button_text,
            onClick = onLevelsClick,
        )
        MenuButton(
            modifier = Modifier.padding(start = 43.dp),
            text = R.string.settings_button_text,
            onClick = onSettingsClick,
        )
        Spacer(Modifier.weight(1f))
    }
}