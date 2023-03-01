package com.game.ballsofwool.feature.game.main

import android.os.Bundle
import android.view.View
import com.game.ballsofwool.ext.pressBack
import com.game.ballsofwool.ext.setResultListener
import com.game.ballsofwool.feature.base.MviFragment
import com.game.ballsofwool.feature.game.completedialog.CompleteLevelDialog
import com.game.ballsofwool.feature.game.main.ui.GameContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class GameFragment : MviFragment<GameState, GameEffect, GameViewModel>() {

    override val viewModel by viewModel<GameViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setResultListener(::onCompleteLevelResult)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent { state ->
            GameContent(
                state = state,
                onBackClick = ::pressBack,
                onBallDrag = viewModel::onBallDrag,
                onDragEnd = viewModel::onDragEnd,
            )
        }
    }

    override fun onEffect(effect: GameEffect) {
        when (effect) {
            GameEffect.ShowComplete -> showCompleteLevelDialog()
        }
    }

    private fun showCompleteLevelDialog() {
        CompleteLevelDialog.show(parentFragmentManager)
    }

    private fun onCompleteLevelResult(result: CompleteLevelDialog.Result) {
        when {
            result.restart -> viewModel.onRestartClick()
            else -> viewModel.onNextLevelClick()
        }
    }

    companion object {

        val TAG = GameFragment::class.qualifiedName!!

        fun newInstance() = GameFragment()
    }
}