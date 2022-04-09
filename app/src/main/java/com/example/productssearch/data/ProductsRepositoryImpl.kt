package com.example.productssearch.data

import com.example.productssearch.data.models.ProductsPage
import io.reactivex.rxjava3.core.Single

class ProductsRepositoryImpl: ProductsRepository {
    override fun getProductsPage(query: String, page: Int): Single<ProductsPage> {
        TODO("Not yet implemented")
    }
}
