package com.example.ballsofwool.di

import com.example.ballsofwool.feature.menu.MenuViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel {
        MenuViewModel()
    }
}