package com.game.ballsofwool.di

import com.game.ballsofwool.data.DefaultLocalDatasource
import com.game.ballsofwool.data.DefaultRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<DefaultRepository> {
        DefaultRepository(datasource = get())
    }
    single {
        DefaultLocalDatasource(context = get())
    }
}