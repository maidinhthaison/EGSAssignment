package com.example.movedb.main

import androidx.multidex.MultiDexApplication
import com.example.movedb.BuildConfig
import com.example.movedb.config.AppConfig
import dagger.hilt.android.HiltAndroidApp
import timber.log.Timber

@HiltAndroidApp
class MovieApp : MultiDexApplication() {

    companion object {
        lateinit var instance: MovieApp
            private set
    }

    override fun onCreate() {
        super.onCreate()
        AppConfig.setBackendEnvironment(BuildConfig.FLAVOR)
        instance = this
        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        } else {
            Timber.plant(ReleaseTree())
        }
    }



}