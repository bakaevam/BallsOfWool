package com.game.ballsofwool.feature.base

import android.app.Dialog
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.DialogFragment
import com.game.ballsofwool.ui.theme.BallsOfWoolTheme

abstract class ComposeDialogFragment : DialogFragment() {

    private var composeView: ComposeView? = null

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog =
        super.onCreateDialog(savedInstanceState).apply {
            window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
        }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? = ComposeView(requireContext()).also { composeView = it }

    override fun onDestroyView() {
        super.onDestroyView()
        composeView = null
    }

    protected fun setContent(content: @Composable () -> Unit) {
        dialog?.setCanceledOnTouchOutside(false)
        dialog?.window?.decorView?.systemUiVisibility = (
            View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY
                or View.SYSTEM_UI_FLAG_HIDE_NAVIGATION)
        dialog?.window?.setBackgroundDrawableResource(android.R.color.transparent)
        composeView!!.setContent {
            BallsOfWoolTheme(content = content)
        }
    }
}