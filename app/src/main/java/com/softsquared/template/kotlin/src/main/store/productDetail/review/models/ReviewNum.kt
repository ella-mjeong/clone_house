package com.softsquared.template.kotlin.src.main.store.productDetail.review.models

import com.google.gson.annotations.SerializedName

data class ReviewNum(
        @SerializedName("리뷰수")  val cnt: Int
)