package com.game.ballsofwool.feature.menu.completelevels

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.game.ballsofwool.ext.boolArgument
import com.game.ballsofwool.ext.clickSound
import com.game.ballsofwool.ext.intArgument
import com.game.ballsofwool.feature.base.ComposeDialogFragment
import com.game.ballsofwool.feature.menu.completelevels.ui.InfoContent

class InfoDialog : ComposeDialogFragment() {

    private var soundOn: Boolean by boolArgument()
    private var text: Int by intArgument()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent {
            InfoContent(
                text = text,
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

        private val TAG = InfoDialog::class.qualifiedName!!

        fun show(
            manager: FragmentManager,
            soundOn: Boolean,
            text: Int,
        ) {
            if (manager.findFragmentByTag(TAG) != null) {
                return
            }
            InfoDialog().apply {
                this.soundOn = soundOn
                this.text = text
            }.show(manager, TAG)
        }
    }
}