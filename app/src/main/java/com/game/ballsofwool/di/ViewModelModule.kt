package com.game.ballsofwool.di

import com.game.ballsofwool.feature.levels.LevelsViewModel
import com.game.ballsofwool.feature.menu.MenuViewModel
import com.game.ballsofwool.feature.settings.SettingsViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {

    viewModel {
        MenuViewModel(get())
    }
    viewModel {
        LevelsViewModel(get())
    }
    viewModel {
        SettingsViewModel(get())
    }
}