package com.game.ballsofwool.feature.settings

import android.os.Bundle
import android.view.View
import androidx.core.app.ActivityCompat.recreate
import com.game.ballsofwool.MainActivity
import com.game.ballsofwool.ext.clickSound
import com.game.ballsofwool.ext.pressBack
import com.game.ballsofwool.feature.base.MviFragment
import com.game.ballsofwool.feature.settings.ui.SettingsContent
import com.yariksoffice.lingver.Lingver
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
                onNextClick = viewModel::onNextClick,
                onPreviousClick = viewModel::onPreviousClick,
            )
        }
    }

    override fun onEffect(effect: SettingsEffect) {
        when (effect) {
            SettingsEffect.PlayMusic -> (requireActivity() as MainActivity).playMusic()
            SettingsEffect.StopMusic -> (requireActivity() as MainActivity).stopMusic()
            SettingsEffect.ClickSound -> clickSound()
            is SettingsEffect.UpdateLanguage -> updateLanguage(effect.language)
        }
    }

    private fun updateLanguage(language: String) {
        Lingver.getInstance().setLocale(requireContext(), language)
        recreate(requireActivity())
    }


    companion object {

        val TAG = SettingsFragment::class.qualifiedName!!

        fun newInstance() = SettingsFragment()
    }
}