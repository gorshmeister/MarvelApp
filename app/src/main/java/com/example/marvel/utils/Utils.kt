package com.example.marvel.utils

import android.content.Context
import com.example.marvel.MarvelApp
import com.example.marvel.di.AppComponent

object Utils {
    val Context.appComponent: AppComponent
        get() = when (this) {
            is MarvelApp -> appComponent
            else -> this.applicationContext.appComponent
        }
}