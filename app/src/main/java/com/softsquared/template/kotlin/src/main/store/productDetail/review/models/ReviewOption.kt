package com.softsquared.template.kotlin.src.main.store.productDetail.review.models

import com.google.gson.annotations.SerializedName

data class ReviewOption(
        @SerializedName("content") val content: String,
        @SerializedName("nickname") val nickname: String,
        @SerializedName("rateDelivery") val rateDelivery: Int,
        @SerializedName("rateDesign") val rateDesign: Int,
        @SerializedName("rateDurability") val rateDurability: Int,
        @SerializedName("ratePrice") val ratePrice: Int,
        @SerializedName("구매날짜") val date: String,
        @SerializedName("도움이돼요") val good: Int,
        @SerializedName("평점") val rating: String
)