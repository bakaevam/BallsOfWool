package com.example.ballsofwool.di

import com.example.ballsofwool.data.DefaultLocalDatasource
import com.example.ballsofwool.data.DefaultRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<DefaultRepository> {
        DefaultRepository(datasource = get())
    }
    single {
        DefaultLocalDatasource(context = get())
    }
}