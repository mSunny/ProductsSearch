package com.example.productssearch

import android.app.Application
import com.example.productssearch.di.AppComponent
import com.example.productssearch.di.DaggerAppComponent

class App: Application() {
    lateinit var appComponent: AppComponent
        private set

    override fun onCreate() {
        super.onCreate()
        appComponent = DaggerAppComponent.builder()
            .build()
    }
}
