package com.game.ballsofwool.feature.settings.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.game.ballsofwool.R
import com.game.ballsofwool.data.model.Language
import com.game.ballsofwool.feature.levels.ui.LevelButton
import com.game.ballsofwool.ui.theme.*

@Preview(showBackground = true)
@Composable
private fun Preview() {
    Box(
        modifier = Modifier.background(color = RoseE2ABF5)
    ) {
        LanguageItem(
            language = Language.ENGLISH,
            onNextClick = {},
            onPreviousClick = {},
        )
    }
}

@Composable
fun LanguageItem(
    language: Language,
    modifier: Modifier = Modifier,
    onNextClick: () -> Unit,
    onPreviousClick: () -> Unit,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically,
    ) {
        LevelButton(
            modifier = Modifier,
            icon = R.drawable.ic_previous_button,
            onClick = onPreviousClick,
        )
        Spacer(Modifier.width(16.dp))
        Text(
            modifier = Modifier
                .background(color = PurpleC171BF, shape = RoundedCorner)
                .width(width = 200.dp)
                .padding(vertical = 12.dp),
            text = stringResource(language.text),
            style = Typography.h3,
            color = White,
            textAlign = TextAlign.Center,
        )
        Spacer(Modifier.width(16.dp))
        LevelButton(
            modifier = Modifier,
            icon = R.drawable.ic_next_button,
            onClick = onNextClick,
        )
    }
}