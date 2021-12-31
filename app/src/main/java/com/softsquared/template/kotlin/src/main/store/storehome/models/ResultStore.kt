package com.softsquared.template.kotlin.src.main.store.storehome.models

import com.google.gson.annotations.SerializedName

data class ResultStore (
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String,
        @SerializedName("data") val data: ArrayList<ProductListInfo>
)

