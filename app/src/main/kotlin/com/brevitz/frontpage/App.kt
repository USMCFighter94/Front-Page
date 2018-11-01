package com.brevitz.frontpage

import android.app.Application
import com.brevitz.frontpage.modules.appModule
import org.koin.android.ext.android.startKoin

class App : Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin(this, listOf(appModule))
    }
}