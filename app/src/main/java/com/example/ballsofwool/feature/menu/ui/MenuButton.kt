package com.example.ballsofwool.feature.menu.ui

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.ballsofwool.ui.theme.RoundedCorner
import com.example.ballsofwool.ui.theme.Typography
import com.example.ballsofwool.ui.theme.White

@Composable
fun MenuButton(
    text: Int,
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
) {
    Button(
        modifier = modifier
            .padding(vertical = 6.dp)
            .size(height = 43.dp, width = 157.dp),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(backgroundColor = White),
        shape = RoundedCorner,
        elevation = ButtonDefaults.elevation(45.dp),
    ) {
        Text(
            modifier = Modifier.fillMaxSize(),
            text = stringResource(text),
            style = Typography.button,
            textAlign = TextAlign.Center,
        )
    }
}