package com.game.ballsofwool.feature.game.main

import android.os.Bundle
import android.view.View
import com.game.ballsofwool.R
import com.game.ballsofwool.ext.nullableIntArgument
import com.game.ballsofwool.ext.pressBack
import com.game.ballsofwool.ext.setResultListener
import com.game.ballsofwool.ext.showToast
import com.game.ballsofwool.feature.base.MviFragment
import com.game.ballsofwool.feature.game.completedialog.CompleteLevelDialog
import com.game.ballsofwool.feature.game.main.ui.GameContent
import com.game.ballsofwool.router.router
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GameFragment : MviFragment<GameState, GameEffect, GameViewModel>() {

    override val viewModel by viewModel<GameViewModel> { parametersOf(levelNumber) }
    private var levelNumber: Int? by nullableIntArgument()

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
            GameEffect.ShowToastAllLevels -> showAllLevelsComplete()
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

    private fun showAllLevelsComplete() {
        showToast(getString(R.string.game_all_level_complete))
        router.popToRoot()
    }

    companion object {

        val TAG = GameFragment::class.qualifiedName!!

        fun newInstance(
            levelNumber: Int?,
        ) = GameFragment().apply {
            this.levelNumber = levelNumber
        }
    }
}