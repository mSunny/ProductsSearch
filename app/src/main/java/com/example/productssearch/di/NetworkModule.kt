package com.example.productssearch.di

import com.example.productssearch.data.network.BASE_URL
import com.example.productssearch.data.network.ProductsApiService
import com.example.productssearch.data.network.RetrofitServiceCreator
import dagger.Module
import dagger.Provides
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import javax.inject.Qualifier
import javax.inject.Singleton

@Module
class NetworkModule {
    @Singleton
    @Provides
    fun provideRetrofit(@BaseUrl baseUrl: String, okHttpClient: OkHttpClient): Retrofit {
        return RetrofitServiceCreator.getRetrofitForBaseUrl(baseUrl, okHttpClient)
    }

    @Singleton
    @Provides
    fun provideOkHttpClient(
        interceptors: ArrayList<Interceptor>): OkHttpClient =
        RetrofitServiceCreator.getOkHttpClient(interceptors)


    @Singleton
    @Provides
    fun provideInterceptors(): ArrayList<Interceptor> = RetrofitServiceCreator.getInterceptors()

    @Singleton
    @Provides
    fun provideFlickrApiService(retrofit: Retrofit): ProductsApiService =
        RetrofitServiceCreator.getApiService(retrofit)

    @Singleton
    @Provides
    @BaseUrl
    fun provideBaseUrl(): String = BASE_URL

    @Retention(AnnotationRetention.RUNTIME)
    @Qualifier
    annotation class BaseUrl
}
