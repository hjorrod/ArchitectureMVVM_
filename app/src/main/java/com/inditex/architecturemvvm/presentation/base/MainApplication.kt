package com.inditex.architecturemvvm.presentation

import android.app.Application
import com.google.firebase.FirebaseApp
import dagger.hilt.android.HiltAndroidApp

@HiltAndroidApp
class MainApplication: Application() {

    override fun onCreate() {
        super.onCreate()
        initFirebase()
    }

    private fun initFirebase() {
        FirebaseApp.initializeApp(this)
    }
}