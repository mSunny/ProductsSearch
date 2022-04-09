package com.example.productssearch.data.network

import com.example.productssearch.domain.models.ProductsPage
import io.reactivex.rxjava3.core.Single
import retrofit2.http.GET
import retrofit2.http.Query

interface ProductsApiService {
    @GET("search")
    fun getProductsPage(@Query("query")query: String,
                        @Query("page")page: Int): Single<ProductsPage>
}
