package com.kikepb7.rickandmortyapp

import android.app.Application
import com.kikepb7.rickandmortyapp.di.initKoin
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger

class RickMortyApp : Application() {

    override fun onCreate() {
        super.onCreate()
        initKoin {
            androidLogger()
            androidContext(androidContext = this@RickMortyApp)
        }
    }
}