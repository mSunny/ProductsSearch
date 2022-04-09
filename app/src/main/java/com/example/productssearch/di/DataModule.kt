package com.example.productssearch.di

import com.example.productssearch.domain.ProductsRepository
import com.example.productssearch.data.ProductsRepositoryImpl
import dagger.Binds
import dagger.Module

@Module
interface DataModule {
    @Binds
    fun bindProductsRepository(productsRepositoryImpl: ProductsRepositoryImpl): ProductsRepository
}