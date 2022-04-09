package com.example.productssearch.data.models

data class ReviewInformation(
    val reviews: List<Review>,
    val reviewSummary: ReviewSummary,
)

data class Review(val info: String)

data class ReviewSummary(
    val reviewAverage: Double,
    val reviewCount: Int,
)
