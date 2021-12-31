package com.softsquared.template.kotlin.src.main.store.storehome.models

import com.google.gson.annotations.SerializedName

data class ProductListInfo (
        @SerializedName("image") val image: String,
        @SerializedName("displayName") val displayName: String,
        @SerializedName("price") val price: Int,
        @SerializedName("companyName") val companyName: String,
        @SerializedName("discount") val discount: Int,
        @SerializedName("rating") val reviewScore: String,
        @SerializedName("scrap") val scrapNum: Int ,
        @SerializedName("review") val reviewNum: Int,
        @SerializedName("delivery") val delivery: String,
        @SerializedName("special") val specialPrice: String
)