package com.example.productssearch.di

import com.example.productssearch.presentation.MainActivity
import dagger.Component
import javax.inject.Singleton

@Component(modules = [
    DataModule::class,
    NetworkModule::class,
    ViewModelModule::class,
])

@Singleton
interface AppComponent {
    fun inject(activity: MainActivity)
}
