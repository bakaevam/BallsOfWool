package com.game.ballsofwool.feature.levels.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.game.ballsofwool.R
import com.game.ballsofwool.feature.levels.LevelsState
import com.game.ballsofwool.ui.ErrorLayout
import com.game.ballsofwool.ui.Loader
import com.game.ballsofwool.ui.TitleText
import com.game.ballsofwool.ui.theme.RoseE2ABF5
import com.game.ballsofwool.ui.theme.White

@Preview(
    showBackground = true,
    device = Devices.AUTOMOTIVE_1024p, widthDp = 720, heightDp = 360,
)
@Composable
private fun Preview() {
    LevelsContent(
        state = LevelsState(
            levels = 5,
            lastOpenLevel = 3,
        ),
        onLevelClick = {},
        onPreviousClick = {},
        onNextClick = {},
        onBackClick = {},
        onRestartClick = {},
    )
}

@Composable
fun LevelsContent(
    state: LevelsState,
    modifier: Modifier = Modifier,
    onBackClick: () -> Unit,
    onRestartClick: () -> Unit,
    onLevelClick: (Int) -> Unit,
    onPreviousClick: () -> Unit,
    onNextClick: () -> Unit,
) {
    Box(
        modifier = modifier
            .fillMaxSize()
            .background(color = RoseE2ABF5),
    ) {
        IconButton(
            modifier = Modifier.padding(20.dp),
            onClick = onBackClick,
        ) {
            Icon(
                painter = painterResource(R.drawable.ic_back),
                contentDescription = null,
                tint = White,
            )
        }
        TitleText(
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.TopCenter),
            text = R.string.levels_title,
            fontSize = 40.sp,
        )
        when {
            state.loading -> {
                Loader(Modifier.fillMaxSize())
            }
            state.loadError != null -> {
                ErrorLayout(
                    modifier = Modifier.fillMaxSize(),
                    onClick = onRestartClick,
                )
            }
            else -> {
                LevelsBody(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center),
                    state = state,
                    onNextClick = onNextClick,
                    onPreviousClick = onPreviousClick,
                    onLevelClick = onLevelClick,
                )
            }
        }
    }
}