package com.game.ballsofwool.di

import com.game.ballsofwool.feature.levels.LevelsViewModel
import com.game.ballsofwool.feature.menu.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MenuViewModel()
    }
    viewModel {
        LevelsViewModel()
    }
}