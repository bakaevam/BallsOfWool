package com.game.ballsofwool.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.game.ballsofwool.R
import com.game.ballsofwool.feature.menu.ui.MenuButton
import com.game.ballsofwool.ui.theme.RoseE2ABF5

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Box(
        modifier = Modifier
            .background(color = RoseE2ABF5)
            .padding(50.dp),
    ) {
        ErrorLayout(
            modifier = Modifier.fillMaxSize(),
            onClick = { }
        )
    }
}

@Composable
fun ErrorLayout(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Column(
        modifier = modifier,
        verticalArrangement = Arrangement.Center,
    ) {
        TitleText(
            modifier = Modifier
                .padding(top = 20.dp)
                .align(Alignment.CenterHorizontally),
            text = R.string.error_title,
            fontSize = 40.sp,
        )
        Spacer(Modifier.height(24.dp))
        MenuButton(
            modifier = Modifier.align(Alignment.CenterHorizontally),
            text = R.string.error_repeat,
            onClick = onClick,
        )
    }
}