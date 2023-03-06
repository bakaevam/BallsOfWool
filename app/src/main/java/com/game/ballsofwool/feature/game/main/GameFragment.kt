package com.game.ballsofwool.feature.game.main

import android.os.Bundle
import android.util.DisplayMetrics
import android.view.View
import com.game.ballsofwool.R
import com.game.ballsofwool.ext.*
import com.game.ballsofwool.feature.base.MviFragment
import com.game.ballsofwool.feature.game.completedialog.CompleteLevelDialog
import com.game.ballsofwool.feature.game.main.ui.GameContent
import com.game.ballsofwool.feature.menu.completelevels.InfoDialog
import com.game.ballsofwool.router.router
import kotlinx.parcelize.Parcelize
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf

class GameFragment : MviFragment<GameState, GameEffect, GameViewModel>() {

    override val viewModel by viewModel<GameViewModel> { parametersOf(levelNumber) }
    private var levelNumber: Int? by nullableIntArgument()
    private lateinit var displayMetrics: DisplayMetrics

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        displayMetrics = resources.displayMetrics
        setResultListener(::onCompleteLevelResult)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent { state ->
            GameContent(
                state = state,
                onBackClick = ::pressBack,
                onRestartLoadClick = viewModel::onRestartLoadClick,
                onRestartAllLevelsClick = viewModel::onRestartAllLevelsClick,
                onBallDrag = viewModel::onBallDrag,
                onDragEnd = viewModel::onDragEnd,
            )
        }
    }

    override fun onEffect(effect: GameEffect) {
        when (effect) {
            is GameEffect.ShowComplete -> showCompleteLevelDialog(effect.soundOn)
            GameEffect.ShowToastAllLevels -> showAllLevelsComplete()
            GameEffect.BallsLoaded -> viewModel.onBallsLoaded(displayMetrics)
            is GameEffect.ShowManualDialog -> showManualDialog(effect.soundOn)
        }
    }

    private fun showCompleteLevelDialog(soundOn: Boolean) {
        CompleteLevelDialog.show(parentFragmentManager, soundOn)
    }

    private fun onCompleteLevelResult(result: CompleteLevelDialog.Result) {
        when {
            result.restart -> viewModel.onRestartClick()
            else -> viewModel.onNextLevelClick()
        }
    }

    private fun showAllLevelsComplete() {
        setResult(Result())
        router.popToRoot()
    }

    private fun showManualDialog(soundOn: Boolean) {
        InfoDialog.show(
            manager = parentFragmentManager,
            soundOn = soundOn,
            text = R.string.game_manual,
        )
    }

    @Parcelize
    data class Result(
        val allLevelsComplete: Boolean = true,
    ) : FragmentResult

    companion object {

        val TAG = GameFragment::class.qualifiedName!!

        fun newInstance(
            levelNumber: Int?,
        ) = GameFragment().apply {
            this.levelNumber = levelNumber
        }
    }
}