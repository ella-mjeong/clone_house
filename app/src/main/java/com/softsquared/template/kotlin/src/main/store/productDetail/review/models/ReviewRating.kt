package com.softsquared.template.kotlin.src.main.store.productDetail.review.models

import com.google.gson.annotations.SerializedName

data class ReviewRating(
        @SerializedName("개수")  val cnt: Int,
        @SerializedName("평점") val rating: Int
)