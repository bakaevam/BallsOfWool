package com.game.ballsofwool.feature.menu

import android.os.Bundle
import android.view.View
import com.game.ballsofwool.MainActivity
import com.game.ballsofwool.R
import com.game.ballsofwool.ext.clickSound
import com.game.ballsofwool.ext.setResultListener
import com.game.ballsofwool.feature.base.MviFragment
import com.game.ballsofwool.feature.game.main.GameFragment
import com.game.ballsofwool.feature.menu.completelevels.InfoDialog
import com.game.ballsofwool.feature.menu.ui.MenuContent
import com.game.ballsofwool.router.router
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : MviFragment<MenuState, MenuEffect, MenuViewModel>() {

    override val viewModel by viewModel<MenuViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setResultListener(::onGameFragmentResult)
    }

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
            is MenuEffect.NavigateToGame -> router.toGame()
            is MenuEffect.NavigateToLevels -> router.toLevels()
            is MenuEffect.NavigateToSettings -> router.toSettings()
            MenuEffect.PlayMusic -> playMusic()
            MenuEffect.ClickSound -> clickSound()
            is MenuEffect.ShowAllLevelsComplete ->
                InfoDialog.show(
                    manager = parentFragmentManager,
                    soundOn = effect.soundOn,
                    text = R.string.game_all_level_complete,
                )
        }
    }

    private fun playMusic() {
        (requireActivity() as MainActivity).playMusic()
    }

    private fun onGameFragmentResult(result: GameFragment.Result) {
        viewModel.onGameFragmentResult()
    }

    companion object {

        val TAG = MenuFragment::class.qualifiedName!!

        fun newInstance() = MenuFragment()
    }
}