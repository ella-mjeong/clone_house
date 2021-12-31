package com.softsquared.template.kotlin.src.main.store.productDetail.review.models

import com.google.gson.annotations.SerializedName

data class ReviewInfo(
        @SerializedName("option") val reviewOption: ArrayList<ReviewOption>,
        @SerializedName("rating") val reviewRating: ArrayList<ReviewRating>,
        @SerializedName("평점") val reviewTotalRate: String,
        @SerializedName("total_review") val reviewTotalNum: ArrayList<ReviewNum>
)