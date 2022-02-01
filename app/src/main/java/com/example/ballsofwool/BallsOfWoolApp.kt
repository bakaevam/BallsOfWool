package com.example.ballsofwool

import android.app.Application
import com.example.ballsofwool.di.repositoryModule
import com.example.ballsofwool.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class BallsOfWoolApp : Application() {

    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidContext(this@BallsOfWoolApp)
            modules(
                repositoryModule,
                viewModelModule,
            )
        }
    }
}