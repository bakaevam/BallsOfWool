package com.game.ballsofwool

import android.app.Application
import com.game.ballsofwool.di.repositoryModule
import com.game.ballsofwool.di.viewModelModule
import com.yariksoffice.lingver.Lingver
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
        Lingver.init(this)
    }
}