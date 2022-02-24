package com.rockstreamer.koinandroidexample.utils

import android.app.Application
import androidx.multidex.MultiDex
import com.rockstreamer.koinandroidexample.di.module.appModule
import com.rockstreamer.koinandroidexample.di.module.repoModule
import com.rockstreamer.koinandroidexample.di.module.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin


class AppController: Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        startKoin {
            androidContext(this@AppController)
            modules(listOf(appModule , repoModule , viewModelModule))
        }
    }

}