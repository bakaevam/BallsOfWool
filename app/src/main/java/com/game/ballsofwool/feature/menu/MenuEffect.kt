package com.game.ballsofwool.feature.menu

sealed interface MenuEffect {

    object NavigateToGame : MenuEffect
    object NavigateToLevels : MenuEffect
    object NavigateToSettings : MenuEffect
    object PlayMusic : MenuEffect
    object ClickSound : MenuEffect
}