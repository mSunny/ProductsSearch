package com.example.productssearch.di

import com.example.productssearch.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    DataModule::class,
    NetworkModule::class,
])

@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
}