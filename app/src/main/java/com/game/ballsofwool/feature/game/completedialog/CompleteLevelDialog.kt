package com.game.ballsofwool.feature.game.completedialog

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.game.ballsofwool.ext.FragmentResult
import com.game.ballsofwool.ext.boolArgument
import com.game.ballsofwool.ext.clickSound
import com.game.ballsofwool.ext.setResult
import com.game.ballsofwool.feature.base.ComposeDialogFragment
import com.game.ballsofwool.feature.game.completedialog.ui.CompleteLevelContent
import kotlinx.parcelize.Parcelize

class CompleteLevelDialog : ComposeDialogFragment() {

    private var soundOn: Boolean by boolArgument()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent {
            CompleteLevelContent(
                onRestartClick = ::onRestartClick,
                onNextLevelClick = ::onNextLevelClick,
            )
        }
    }

    private fun onRestartClick() {
        checkSoundClick()
        setResult(Result(true))
        dismiss()
    }

    private fun onNextLevelClick() {
        checkSoundClick()
        setResult(Result(false))
        dismiss()
    }

    private fun checkSoundClick() {
        if (soundOn) {
            clickSound()
        }
    }

    @Parcelize
    data class Result(
        val restart: Boolean,
    ) : FragmentResult

    companion object {

        private val TAG = CompleteLevelDialog::class.qualifiedName!!

        fun show(
            manager: FragmentManager,
            soundOn: Boolean,
        ) {
            if (manager.findFragmentByTag(TAG) != null) {
                return
            }
            CompleteLevelDialog().apply {
                this.soundOn = soundOn
            }.show(manager, TAG)
        }
    }
}