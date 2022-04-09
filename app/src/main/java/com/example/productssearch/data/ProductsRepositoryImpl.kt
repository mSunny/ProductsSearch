package com.example.productssearch.data

import com.example.productssearch.data.models.ProductsPage
import com.example.productssearch.data.network.ProductsApiService
import io.reactivex.rxjava3.core.Single
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsApiService: ProductsApiService): ProductsRepository {
    override fun getProductsPage(query: String, page: Int): Single<ProductsPage> {
        return productsApiService.getProductsPage(query, page)
    }
}
