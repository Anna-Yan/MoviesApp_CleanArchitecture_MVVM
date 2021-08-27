package com.annazaqaryan.moviesbyaz

import android.app.Application
import com.annazaqaryan.moviesbyaz.di.AppModule
import com.annazaqaryan.moviesbyaz.di.NetworkModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import org.koin.core.logger.Level


class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger(Level.DEBUG)
            androidContext(this@MainApplication)
            modules(listOf(AppModule, NetworkModule))
        }
    }
}