package com.softsquared.template.kotlin.src.main.store.productDetail.question.models

import com.google.gson.annotations.SerializedName

data class Question(
        @SerializedName("content") val content: String,
        @SerializedName("displayName") val displayName: Any,
        @SerializedName("nickname") val nickname: String,
        @SerializedName("price") val price: Any,
        @SerializedName("type") val type: String,
        @SerializedName("글쓴일") val date: String
)