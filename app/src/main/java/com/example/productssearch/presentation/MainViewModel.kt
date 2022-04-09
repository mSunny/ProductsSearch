package com.example.productssearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.example.productssearch.domain.ProductsInteractor
import com.example.productssearch.domain.models.Product
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi

class MainViewModel(private val productsInteractor: ProductsInteractor): ViewModel() {
    @ExperimentalCoroutinesApi
    fun queryProducts(query: String): Flowable<PagingData<Product>> {
        return productsInteractor.getProducts(query)
            .subscribeOn(Schedulers.io())
            .cachedIn(viewModelScope)
    }
}