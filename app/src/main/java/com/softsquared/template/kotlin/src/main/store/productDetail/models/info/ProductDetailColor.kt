package com.softsquared.template.kotlin.src.main.store.productDetail.models.info

import com.google.gson.annotations.SerializedName

data class ProductDetailColor(
        @SerializedName("color") val color: String,
        @SerializedName("price") val price: Int
)