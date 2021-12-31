package com.softsquared.template.kotlin.src.main.store.productDetail.models.info

import com.google.gson.annotations.SerializedName
import com.softsquared.template.kotlin.src.main.store.storehome.models.ProductListInfo

data class ResultProductDetail(
        @SerializedName("code")val code: Int,
        @SerializedName("data") val data: ProductDetailInfo,
        @SerializedName("isSuccess")val isSuccess: Boolean,
        @SerializedName("message")val message: String
)

