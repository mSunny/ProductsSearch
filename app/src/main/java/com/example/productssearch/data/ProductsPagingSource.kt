package com.example.productssearch.data

import androidx.paging.PagingState
import androidx.paging.rxjava3.RxPagingSource
import com.example.productssearch.data.network.ProductsApiService
import com.example.productssearch.domain.models.Product
import com.example.productssearch.domain.models.ProductsPage
import io.reactivex.rxjava3.core.Single

class ProductsPagingSource(
    private val productsApiService: ProductsApiService,
    private val query: String,
): RxPagingSource<Int, Product>() {
    override fun getRefreshKey(state: PagingState<Int, Product>): Int? {
        return state.anchorPosition?.let { anchorPosition ->
            state.closestPageToPosition(anchorPosition)?.prevKey?.plus(1)
                ?: state.closestPageToPosition(anchorPosition)?.nextKey?.minus(1)
        }
    }

    override fun loadSingle(params: LoadParams<Int>): Single<LoadResult<Int, Product>> {
        val position = params.key ?: 1
        return productsApiService.getProductsPage(query, position)
            .map { toLoadResult(it, position) }
            .onErrorReturn { LoadResult.Error(it) }
    }

    private fun toLoadResult(data: ProductsPage, position: Int): LoadResult<Int, Product> {
        return LoadResult.Page(
            data = data.products,
            prevKey = if (position == 1) null else position - 1,
            nextKey = if (position == data.pageCount) null else position + 1
        )
    }
}
