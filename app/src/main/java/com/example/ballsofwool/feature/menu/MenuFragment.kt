package com.example.ballsofwool.feature.menu

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ballsofwool.ext.collect
import com.example.ballsofwool.feature.base.MviFragment
import com.example.ballsofwool.feature.menu.ui.MenuContent
import com.example.ballsofwool.router.router
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
        collect(viewModel.effect, ::applyEffect)
    }

    private fun applyEffect(effect: MenuEffect) {
        when (effect) {
            is MenuEffect.NavigateToGame -> createToast()
            is MenuEffect.NavigateToLevels -> router.toLevels()
            is MenuEffect.NavigateToSettings -> createToast()
        }
    }

    private fun createToast() {
        Toast.makeText(requireContext(), "example text", Toast.LENGTH_LONG).show()
    }

    companion object {

        val TAG = MenuFragment::class.qualifiedName!!

        fun newInstance() = MenuFragment()
    }
}