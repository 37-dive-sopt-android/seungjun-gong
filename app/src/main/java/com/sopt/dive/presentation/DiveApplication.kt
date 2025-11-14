package com.sopt.dive.presentation

import android.app.Application
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class DiveApplication: Application() {

    override fun onCreate() {
        super.onCreate()
    }
}
