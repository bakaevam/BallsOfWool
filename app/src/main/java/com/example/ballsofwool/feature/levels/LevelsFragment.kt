package com.example.ballsofwool.feature.levels

import android.os.Bundle
import android.view.View
import com.example.ballsofwool.feature.base.MviFragment
import com.example.ballsofwool.feature.levels.ui.LevelsContent
import org.koin.androidx.viewmodel.ext.android.viewModel

class LevelsFragment : MviFragment<LevelsState, Nothing, LevelsViewModel>() {

    override val viewModel by viewModel<LevelsViewModel>()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setContent { state ->
            LevelsContent(
                state = state,
                onLevelClick = viewModel::onLevelClick,
                onPreviousClick = viewModel::onPreviousClick,
                onNextClick = viewModel::onNextClick,
            )
        }
    }

    companion object {

        val TAG = LevelsFragment::class.qualifiedName!!

        fun newInstance() = LevelsFragment()
    }
}