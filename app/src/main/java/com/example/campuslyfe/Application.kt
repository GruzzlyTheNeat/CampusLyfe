package com.example.campuslyfe

import android.app.Application
import com.example.campuslyfe.koin.KoinModule
import org.koin.android.ext.koin.androidContext
import org.koin.core.context.GlobalContext.startKoin

class Application : Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidContext(this@Application)
            modules(KoinModule.viewModelsModule)
        }
    }
}