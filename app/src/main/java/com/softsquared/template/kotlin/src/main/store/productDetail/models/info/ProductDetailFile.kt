package com.softsquared.template.kotlin.src.main.store.productDetail.models.info

import com.google.gson.annotations.SerializedName

data class ProductDetailFile(
        @SerializedName("fileURL")val fileURL: String
)