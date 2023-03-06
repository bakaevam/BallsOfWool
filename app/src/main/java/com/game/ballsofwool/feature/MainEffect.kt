package com.game.ballsofwool.feature

sealed interface MainEffect {

    data class ChangeLanguage(
        val language: String,
    ) : MainEffect
}