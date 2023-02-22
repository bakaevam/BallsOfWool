package com.game.ballsofwool.feature.menu

import com.game.ballsofwool.feature.base.MviViewModel

class MenuViewModel : MviViewModel<MenuState, MenuEffect>(MenuState()) {
    fun onPlayClick() {
        postEffect(MenuEffect.NavigateToGame)
    }

    fun onLevelsClick() {
        postEffect(MenuEffect.NavigateToLevels)
    }

    fun onSettingsClick() {
        postEffect(MenuEffect.NavigateToSettings)
    }
}