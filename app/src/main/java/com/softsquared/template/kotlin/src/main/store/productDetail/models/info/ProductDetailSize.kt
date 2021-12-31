package com.softsquared.template.kotlin.src.main.store.productDetail.models.info

import com.google.gson.annotations.SerializedName

data class ProductDetailSize(
        @SerializedName("price") val price: Int,
        @SerializedName("size") val size: String
)