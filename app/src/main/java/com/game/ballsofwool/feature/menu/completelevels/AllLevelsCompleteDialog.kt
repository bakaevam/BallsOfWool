package com.game.ballsofwool.feature.menu.completelevels

import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentManager
import com.game.ballsofwool.feature.base.ComposeDialogFragment
import com.game.ballsofwool.feature.menu.completelevels.ui.AllLevelsCompleteContent

class AllLevelsCompleteDialog : ComposeDialogFragment() {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent {
            AllLevelsCompleteContent(
                onClick = ::dismiss,
            )
        }
    }

    companion object {

        private val TAG = AllLevelsCompleteDialog::class.qualifiedName!!

        fun show(
            manager: FragmentManager,
        ) {
            if (manager.findFragmentByTag(TAG) != null) {
                return
            }
            AllLevelsCompleteDialog().show(manager, TAG)
        }
    }
}