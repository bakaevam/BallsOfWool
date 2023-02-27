package com.game.ballsofwool.feature.levels

import android.os.Bundle
import android.view.View
import com.game.ballsofwool.ext.clickSound
import com.game.ballsofwool.ext.pressBack
import com.game.ballsofwool.feature.base.MviFragment
import com.game.ballsofwool.feature.levels.ui.LevelsContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class LevelsFragment : MviFragment<LevelsState, LevelsEffect, LevelsViewModel>() {

    override val viewModel by viewModel<LevelsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent { state ->
            LevelsContent(
                state = state,
                onBackClick = ::pressBack,
                onLevelClick = viewModel::onLevelClick,
                onPreviousClick = viewModel::onPreviousClick,
                onNextClick = viewModel::onNextClick,
            )
        }
    }

    override fun onEffect(effect: LevelsEffect) {
        when (effect) {
            LevelsEffect.ClickSound -> clickSound()
        }
    }

    companion object {

        val TAG = LevelsFragment::class.qualifiedName!!

        fun newInstance() = LevelsFragment()
    }
}