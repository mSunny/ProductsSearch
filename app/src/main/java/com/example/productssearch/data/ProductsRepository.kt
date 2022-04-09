package com.example.productssearch.data

import androidx.paging.PagingData
import com.example.productssearch.data.models.Product
import io.reactivex.rxjava3.core.Flowable

interface ProductsRepository {
    fun getProductsPage(query: String, page: Int): Flowable<PagingData<Product>>
}
