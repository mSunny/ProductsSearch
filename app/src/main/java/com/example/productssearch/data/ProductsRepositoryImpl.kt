package com.example.productssearch.data

import androidx.paging.Pager
import androidx.paging.PagingConfig
import androidx.paging.PagingData
import androidx.paging.rxjava3.flowable
import com.example.productssearch.data.models.Product
import com.example.productssearch.data.network.ProductsApiService
import io.reactivex.rxjava3.core.Flowable
import javax.inject.Inject

class ProductsRepositoryImpl @Inject constructor(
    private val productsApiService: ProductsApiService): ProductsRepository {
    override fun getProductsPage(query: String, page: Int): Flowable<PagingData<Product>> {
        return Pager(
            config = PagingConfig(
                enablePlaceholders = false,
                pageSize = 20,
            ),
            pagingSourceFactory = { ProductsPagingSource(productsApiService, query) }
        ).flowable
    }
}
