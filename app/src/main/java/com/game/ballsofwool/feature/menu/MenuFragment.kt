package com.game.ballsofwool.feature.menu

import android.os.Bundle
import android.view.View
import com.game.ballsofwool.MainActivity
import com.game.ballsofwool.ext.clickSound
import com.game.ballsofwool.feature.base.MviFragment
import com.game.ballsofwool.feature.menu.ui.MenuContent
import com.game.ballsofwool.router.router
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : MviFragment<MenuState, MenuEffect, MenuViewModel>() {

    override val viewModel by viewModel<MenuViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent { state ->
            MenuContent(
                onPlayClick = viewModel::onPlayClick,
                onLevelsClick = viewModel::onLevelsClick,
                onSettingsClick = viewModel::onSettingsClick,
            )
        }
    }

    override fun onEffect(effect: MenuEffect) {
        when (effect) {
            is MenuEffect.NavigateToGame -> {}
            is MenuEffect.NavigateToLevels -> router.toLevels()
            is MenuEffect.NavigateToSettings -> router.toSettings()
            MenuEffect.PlayMusic -> playMusic()
            MenuEffect.ClickSound -> clickSound()
        }
    }

    private fun playMusic() {
        (requireActivity() as MainActivity).playMusic()
    }

    companion object {

        val TAG = MenuFragment::class.qualifiedName!!

        fun newInstance() = MenuFragment()
    }
}