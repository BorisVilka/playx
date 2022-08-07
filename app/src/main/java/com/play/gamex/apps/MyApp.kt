package com.play.gamex.apps

import android.app.Application
import com.google.firebase.analytics.FirebaseAnalytics
import com.google.firebase.auth.FirebaseAuth

class MyApp: Application() {


    override fun onCreate() {
        super.onCreate()
        FirebaseAnalytics.getInstance(this)
        FirebaseAuth.getInstance()
    }
}