package com.softsquared.template.kotlin.src.main.store.productDetail.models.info

import com.google.gson.annotations.SerializedName

data class ProductDetailOption(
        @SerializedName("displayName") val displayName: String,
        @SerializedName("price") val price: Int
)