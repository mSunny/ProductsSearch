package com.example.productssearch

import com.example.productssearch.domain.models.Product
import com.example.productssearch.domain.models.PromoIcon
import com.example.productssearch.domain.models.ReviewInformation
import com.example.productssearch.domain.models.ReviewSummary

object ProductTestData {
    val reviewInformation1 = ReviewInformation(
        reviews = emptyList(),
        reviewSummary = ReviewSummary(5.2, 125)
    )
    val product1 = Product(
        productId = 793652,
        productName = "Apple iPhone X 256GB Zilver",
        reviewInformation = reviewInformation1,
        USPs = listOf("123","456","789"),
        availabilityState = 2,
        salesPriceIncVat = 10.0,
        listPriceIncVat = 0.0,
        listPriceExVat = 0.0,
        productImage = "Image URL",
        coolbluesChoiceInformationTitle = "Title",
        promoIcon = PromoIcon(null, null),
        nextDayDelivery = true,
    )

    val product2 = Product(
        productId = 608152,
        productName = "Apple Usb-C to Digital AV Adapter",
        reviewInformation = reviewInformation1,
        USPs = listOf("123","456","789"),
        availabilityState = 2,
        salesPriceIncVat = 500.0,
        listPriceIncVat = 600.0,
        listPriceExVat = 700.0,
        productImage = "Image URL",
        coolbluesChoiceInformationTitle = "Title",
        promoIcon = PromoIcon("123", "123"),
        nextDayDelivery = false,
    )

    val productsList = listOf(product1, product2)
}