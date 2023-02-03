package com.example.marvel

import android.app.Application
import android.util.Log
import com.example.marvel.di.AppComponent
import com.example.marvel.di.DaggerAppComponent

class MarvelApp : Application() {
    lateinit var appComponent: AppComponent

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder().context(this).build()
    }
}