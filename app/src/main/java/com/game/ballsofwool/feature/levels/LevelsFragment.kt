package com.game.ballsofwool.feature.levels

import android.os.Bundle
import android.view.View
import com.game.ballsofwool.ext.clickSound
import com.game.ballsofwool.ext.pressBack
import com.game.ballsofwool.feature.base.MviFragment
import com.game.ballsofwool.feature.levels.ui.LevelsContent
import com.game.ballsofwool.router.router
import org.koin.androidx.viewmodel.ext.android.viewModel

class LevelsFragment : MviFragment<LevelsState, LevelsEffect, LevelsViewModel>() {

    override val viewModel by viewModel<LevelsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent { state ->
            LevelsContent(
                state = state,
                onBackClick = ::pressBack,
                onRestartClick = viewModel::onRestartClick,
                onLevelClick = viewModel::onLevelClick,
                onPreviousClick = viewModel::onPreviousClick,
                onNextClick = viewModel::onNextClick,
            )
        }
    }

    override fun onResume() {
        super.onResume()
        viewModel.onResume()
    }

    override fun onEffect(effect: LevelsEffect) {
        when (effect) {
            LevelsEffect.ClickSound -> clickSound()
            is LevelsEffect.PlayLevel -> router.toGame(effect.level)
        }
    }

    companion object {

        val TAG = LevelsFragment::class.qualifiedName!!

        fun newInstance() = LevelsFragment()
    }
}