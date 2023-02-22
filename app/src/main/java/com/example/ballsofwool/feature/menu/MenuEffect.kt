package com.example.ballsofwool.feature.menu

sealed interface MenuEffect {

    object NavigateToGame : MenuEffect
    object NavigateToLevels : MenuEffect
    object NavigateToSettings : MenuEffect
}