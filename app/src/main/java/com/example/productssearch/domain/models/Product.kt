package com.example.productssearch.domain.models

const val PROMO_ICON_COOLBLUE_CHOICE = "coolblues-choice"
const val PROMO_ICON_ACTION_PRICE = "action-price"

data class Product(
    val productId: Long,
    val productName: String,
    val reviewInformation: ReviewInformation,
    val USPs: List<String>,
    val availabilityState: Int, //???
    val salesPriceIncVat: Double, //??
    val listPriceIncVat: Double,
    val listPriceExVat: Double, // 517.35537
    val productImage: String,
    val coolbluesChoiceInformationTitle: String,
    val promoIcon: PromoIcon?,
    val nextDayDelivery: Boolean,
)

data class PromoIcon(
    val text: String?,
    val type: String?, //coolblues-choice, action-price
)
