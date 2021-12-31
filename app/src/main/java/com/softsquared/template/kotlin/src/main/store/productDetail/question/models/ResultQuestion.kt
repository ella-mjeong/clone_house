package com.softsquared.template.kotlin.src.main.store.productDetail.question.models

import com.google.gson.annotations.SerializedName

data class ResultQuestion(
        @SerializedName("code") val code: Int,
        @SerializedName("data") val data: ArrayList<Question>,
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("message") val message: String
)