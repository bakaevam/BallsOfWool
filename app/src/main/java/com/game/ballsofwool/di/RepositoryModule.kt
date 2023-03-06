package com.game.ballsofwool.di

import com.game.ballsofwool.data.db.FirebaseDatabaseRepositoryImpl
import com.game.ballsofwool.data.source.PreferencesDataSource
import com.game.ballsofwool.data.source.Repository
import org.koin.android.ext.koin.androidApplication
import org.koin.dsl.module

val repositoryModule = module {

    single {
        PreferencesDataSource(androidApplication())
    }
    factory {
        Repository(get())
    }
    single {
        FirebaseDatabaseRepositoryImpl()
    }
}