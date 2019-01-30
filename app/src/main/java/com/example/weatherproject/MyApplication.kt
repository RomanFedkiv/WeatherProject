package com.example.weatherproject

import android.app.Application
import com.example.weatherproject.di.*

import org.koin.android.ext.android.startKoin

class MyApplication : Application() {

    override fun onCreate() {
        super.onCreate()


        startKoin(this, listOf(viewModelModule, cacheModule, remoteModule, repositoryModule, useCaseModule))
    }


}