package com.example.productssearch.data.models

data class Product(
    val productId: Long,
    val productName: String,
    val reviewInformation: ReviewInformation,
    val USPs: List<String>,
    val availabilityState: Int, //???
    val salesPriceIncVat: Int, //??
    val listPriceIncVat: Int,
    val listPriceExVat: Int, // 517.35537
    val productImage: String,
    val coolbluesChoiceInformationTitle: String,
    val promoIcon: PromoIcon,
    val nextDayDelivery: Boolean,
)

data class PromoIcon(
    val text: String,
    val type: String, //coolblues-choice, action-price
)
