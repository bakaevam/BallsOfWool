package com.game.ballsofwool.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.game.ballsofwool.R
import com.game.ballsofwool.ui.theme.Purple893C9B
import com.game.ballsofwool.ui.theme.RoseE2ABF5
import com.game.ballsofwool.ui.theme.Typography

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Box(
        modifier = Modifier.background(color = RoseE2ABF5)
    ) {
        TitleText(text = R.string.game_name)
    }
}

@Composable
fun TitleText(
    text: Int,
    modifier: Modifier = Modifier,
    fontSize: TextUnit = 40.sp,
    style: TextStyle = Typography.h1,
) {
    Box(
        modifier = modifier
            //  .background(color = White, shape = RoundedCorner)
            .padding(8.dp)
    ) {
        Text(
            text = stringResource(text),
            style = style,
            fontSize = fontSize,
            color = Purple893C9B,
            textAlign = TextAlign.Center,
        )
    }
}