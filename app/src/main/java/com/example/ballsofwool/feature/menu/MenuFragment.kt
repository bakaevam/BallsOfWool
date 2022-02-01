package com.example.ballsofwool.feature.menu

import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.example.ballsofwool.ext.collect
import com.example.ballsofwool.feature.base.ComposeFragment
import com.example.ballsofwool.uicontent.MenuContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class MenuFragment : ComposeFragment() {

    private val viewModel by viewModel<MenuViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent {
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
            is MenuEffect.NavigateToLevels -> createToast()
            is MenuEffect.NavigateToSettings -> createToast()
        }
    }

    private fun createToast() {
        Toast.makeText(requireContext(), "example text", Toast.LENGTH_LONG).show()
    }

    companion object {
        val TAG = MenuFragment::class.qualifiedName
        fun newInstance() = MenuFragment()
    }
}