package com.example.productssearch.domain.models

data class ProductsPage (
    val products: List<Product>,
    val currentPage: Int,
    val pageSize: Int,
    val totalResults: Int,
    val pageCount: Int,
)
