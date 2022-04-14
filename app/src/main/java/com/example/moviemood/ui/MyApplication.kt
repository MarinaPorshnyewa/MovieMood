package com.example.moviemood.ui

import android.app.Application
import com.example.moviemood.di.AppComponent
import com.example.moviemood.di.DaggerAppComponent
import com.example.moviemood.di.FireBaseModule

class MyApplication : Application() {

    companion object {
        lateinit var appComponent: AppComponent
    }

    override fun onCreate() {
        super.onCreate()
        initAppComponent()
    }

    private fun initAppComponent() {
        appComponent = DaggerAppComponent
            .builder()
            .fireBaseModule(FireBaseModule(this))
            .build()
    }
}