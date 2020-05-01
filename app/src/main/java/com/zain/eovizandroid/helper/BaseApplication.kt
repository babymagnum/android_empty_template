package com.zain.eovizandroid.helper

import android.app.Application
import com.zain.eovizandroid.view.main.MainViewModel
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin
import org.koin.dsl.module

class BaseApplication: Application() {

    val appModule = module {
        single { MainViewModel() }
    }

    override fun onCreate() {
        super.onCreate()

        startKoin{
            androidContext(this@BaseApplication)
            modules(appModule)
        }
    }
}