package com.example.ballsofwool.uicontent

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.ballsofwool.R

@Composable
fun MenuContent(
    onPlayClick: () -> Unit,
    onLevelsClick: () -> Unit,
    onSettingsClick: () -> Unit,
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(end = 70.dp),
        contentAlignment = Alignment.TopEnd,
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            GameTitle()
            PlayButton(onPlayClick)
            LevelsButton(onLevelsClick)
            SettingsButton(onSettingsClick)
        }
    }
}

@Composable
private fun GameTitle() {
    Text(
        modifier = Modifier
            .padding(top = 16.dp, bottom = 8.dp),
        text = stringResource(R.string.game_name),
        fontSize = 60.sp,
        fontWeight = FontWeight(500),
    )
}

@Composable
private fun PlayButton(onPlayClick: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .size(150.dp, 50.dp),
        onClick = onPlayClick,
    ) {
        Text(text = stringResource(R.string.play_button_text))
    }
}

@Composable
private fun LevelsButton(onLevelsClick: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .size(150.dp, 50.dp),
        onClick = onLevelsClick,
    ) {
        Text(text = stringResource(R.string.levels_button_text))
    }
}

@Composable
private fun SettingsButton(onSettingsClick: () -> Unit) {
    Button(
        modifier = Modifier
            .padding(vertical = 8.dp)
            .size(150.dp, 50.dp),
        onClick = onSettingsClick,
    ) {
        Text(text = stringResource(R.string.settings_button_text))
    }
}