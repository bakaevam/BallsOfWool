package com.game.ballsofwool.feature.settings

import android.os.Bundle
import android.view.View
import com.game.ballsofwool.MainActivity
import com.game.ballsofwool.ext.clickSound
import com.game.ballsofwool.ext.pressBack
import com.game.ballsofwool.feature.base.MviFragment
import com.game.ballsofwool.feature.settings.ui.SettingsContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class SettingsFragment : MviFragment<SettingsState, SettingsEffect, SettingsViewModel>() {

    override val viewModel by viewModel<SettingsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent { state ->
            SettingsContent(
                state = state,
                onBackClick = ::pressBack,
                onSoundClick = viewModel::onSoundClick,
                onMusicClick = viewModel::onMusicClick,
            )
        }
    }

    override fun onEffect(effect: SettingsEffect) {
        when (effect) {
            SettingsEffect.PlayMusic -> (requireActivity() as MainActivity).playMusic()
            SettingsEffect.StopMusic -> (requireActivity() as MainActivity).stopMusic()
            SettingsEffect.ClickSound -> clickSound()
        }
    }

    companion object {

        val TAG = SettingsFragment::class.qualifiedName!!

        fun newInstance() = SettingsFragment()
    }
}