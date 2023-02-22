package com.game.ballsofwool.feature.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.ComposeView
import androidx.fragment.app.Fragment
import com.game.ballsofwool.ui.theme.BallsOfWoolTheme

abstract class ComposeFragment : Fragment() {

    private var composeView: ComposeView? = null

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
        composeView!!.setContent {
            BallsOfWoolTheme(content = content)
        }
    }
}
