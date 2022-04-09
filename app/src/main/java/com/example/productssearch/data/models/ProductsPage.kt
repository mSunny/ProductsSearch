package com.example.productssearch.data.models

data class ProductsPage (
    val products: List<Product>,
    val currentPage: Int,
    val pageSize: Int,
    val totalResults: Int,
    val pageCount: Int,
)
