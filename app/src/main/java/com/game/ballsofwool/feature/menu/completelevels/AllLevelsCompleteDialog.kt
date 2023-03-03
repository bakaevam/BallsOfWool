package com.game.ballsofwool.feature.menu.completelevels

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.game.ballsofwool.ext.boolArgument
import com.game.ballsofwool.ext.clickSound
import com.game.ballsofwool.feature.base.ComposeDialogFragment
import com.game.ballsofwool.feature.menu.completelevels.ui.AllLevelsCompleteContent

class AllLevelsCompleteDialog : ComposeDialogFragment() {

    private var soundOn: Boolean by boolArgument()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent {
            AllLevelsCompleteContent(
                onClick = ::onClick,
            )
        }
    }

    private fun onClick() {
        if (soundOn) {
            clickSound()
        }
        dismiss()
    }

    companion object {

        private val TAG = AllLevelsCompleteDialog::class.qualifiedName!!

        fun show(
            manager: FragmentManager,
            soundOn: Boolean,
        ) {
            if (manager.findFragmentByTag(TAG) != null) {
                return
            }
            AllLevelsCompleteDialog().apply {
                this.soundOn = soundOn
            }.show(manager, TAG)
        }
    }
}