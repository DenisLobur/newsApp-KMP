package com.example.dailypulse

import android.app.Application
import com.example.dailypulse.di.databaseModule
import com.example.dailypulse.di.sharedKoinModules
import com.example.dailypulse.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.startKoin

class DailyPulseApp: Application() {

    override fun onCreate() {
        super.onCreate()

        initKoin()
    }

    private fun initKoin() {
        val modules = sharedKoinModules + viewModelModule + databaseModule
        startKoin {
            androidContext(this@DailyPulseApp)
            modules(modules)
        }
    }
}