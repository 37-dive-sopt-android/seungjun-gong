package com.sopt.dive.presentation

import android.app.Application
import com.sopt.dive.core.local.datastore.UserDataStore

class DiveApplication: Application() {

    lateinit var userDataStore: UserDataStore
        private set

    override fun onCreate() {
        super.onCreate()
        userDataStore = UserDataStore(this)
    }
}