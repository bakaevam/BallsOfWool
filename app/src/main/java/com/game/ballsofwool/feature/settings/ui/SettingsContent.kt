package com.game.ballsofwool.feature.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.game.ballsofwool.R
import com.game.ballsofwool.feature.settings.SettingsState
import com.game.ballsofwool.ui.TitleText
import com.game.ballsofwool.ui.theme.RoseE2ABF5
import com.game.ballsofwool.ui.theme.White

@Composable
fun SettingsContent(
    state: SettingsState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onSoundClick: () -> Unit,
    onMusicClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = RoseE2ABF5),
    ) {
        IconButton(
            modifier = Modifier.padding(20.dp),
            onClick = onBackClick
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null,
                tint = White,
            )
        }
        Column(
            modifier = Modifier.fillMaxWidth(),
        ) {
            TitleText(
                modifier = Modifier
                    .padding(top = 20.dp)
                    .align(CenterHorizontally),
                text = R.string.settings_title,
                fontSize = 40.sp,
            )
            Spacer(Modifier.height(50.dp))
            Row(modifier = Modifier.align(CenterHorizontally)) {
                val musicIcon = when {
                    state.musicOn -> R.drawable.ic_music_on
                    else -> R.drawable.ic_music_off
                }
                SettingButton(
                    text = R.string.settings_music,
                    icon = musicIcon,
                    onClick = onMusicClick,
                )
                Spacer(Modifier.width(32.dp))
                val soundIcon = when {
                    state.soundOn -> R.drawable.ic_sound_on
                    else -> R.drawable.ic_sound_off
                }
                SettingButton(
                    text = R.string.settings_sound,
                    icon = soundIcon,
                    onClick = onSoundClick,
                )
            }
        }
    }
}