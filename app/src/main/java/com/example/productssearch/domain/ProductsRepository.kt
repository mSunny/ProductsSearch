package com.example.productssearch.domain

import androidx.paging.PagingData
import com.example.productssearch.domain.models.Product
import io.reactivex.rxjava3.core.Flowable

interface ProductsRepository {
    fun getProducts(query: String): Flowable<PagingData<Product>>
}
