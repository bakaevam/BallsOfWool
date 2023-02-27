package com.game.ballsofwool.di

import com.game.ballsofwool.data.PreferencesDataSource
import com.game.ballsofwool.data.Repository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {

    single {
        PreferencesDataSource(androidApplication())
    }
    single {
        Repository(get())
    }
}