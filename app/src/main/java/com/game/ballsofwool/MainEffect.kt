package com.game.ballsofwool

sealed interface MainEffect {

    data class ChangeLanguage(
        val language: String,
    ) : MainEffect
}