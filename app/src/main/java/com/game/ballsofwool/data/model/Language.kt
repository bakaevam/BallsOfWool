package com.game.ballsofwool.data.model

import androidx.annotation.StringRes
import com.game.ballsofwool.R

enum class Language(
    val code: String,
    @StringRes val text: Int,
) {

    ENGLISH(
        code = "en",
        text = R.string.settings_english,
    ),

    RUSSIAN(
        code = "ru",
        text = R.string.settings_russian,
    );

    companion object {

        fun getLanguage(code: String): Language {
            return values().find { it.code == code } ?: ENGLISH
        }
    }
}