package com.game.ballsofwool.feature.base

import android.os.Bundle
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.game.ballsofwool.ext.collectOnResume

abstract class MviFragment<S, E, VM : MviViewModel<S, E>> : ComposeFragment() {

    protected abstract val viewModel: VM

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        collectOnResume(viewModel.effect, ::onEffect)
    }

    protected fun setContent(
        content: @Composable (state: S) -> Unit,
    ) = super.setContent {
        val state by viewModel.state.collectAsState()
        content(state)
    }

    protected open fun onEffect(effect: E) {
    }
}