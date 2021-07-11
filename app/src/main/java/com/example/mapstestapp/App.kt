package com.example.mapstestapp

import android.app.Application
import com.example.mapstestapp.di.apiModule
import com.example.mapstestapp.di.networkModule
import com.example.mapstestapp.di.utilModule
import com.example.mapstestapp.di.viewModelModule
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin

class App: Application() {
    override fun onCreate() {
        super.onCreate()

        startKoin {
            androidLogger()
            androidContext(this@App)
            modules(
                apiModule,
                viewModelModule,
                networkModule,
                utilModule
            )
        }
    }
}