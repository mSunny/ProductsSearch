package com.example.productssearch.presentation

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import androidx.paging.rxjava3.cachedIn
import com.example.productssearch.domain.ProductsInteractor
import com.example.productssearch.domain.models.Product
import io.reactivex.rxjava3.android.schedulers.AndroidSchedulers
import io.reactivex.rxjava3.core.Flowable
import io.reactivex.rxjava3.schedulers.Schedulers
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
class MainViewModel(private val productsInteractor: ProductsInteractor): ViewModel() {
    var searchQuery: String = ""
    var searchIconified: Boolean = true

    fun onSearchClick() {
        searchIconified = false
    }

    fun onSearchClose() {
        searchIconified = true
    }

    fun onSearchQueryTextChange(query: String) {
        searchQuery = query
    }

    fun queryProducts(query: String): Flowable<PagingData<Product>> {
        return productsInteractor.getProducts(query)
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .cachedIn(viewModelScope)
    }
}