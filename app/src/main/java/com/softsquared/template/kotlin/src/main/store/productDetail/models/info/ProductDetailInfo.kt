package com.softsquared.template.kotlin.src.main.store.productDetail.models.info

import com.google.gson.annotations.SerializedName

data class ProductDetailInfo(
        @SerializedName("color") val productDetailColor: ArrayList<ProductDetailColor>,
        @SerializedName("displayname") val displayname: String,
        @SerializedName("file") val productDetailFile: ArrayList<ProductDetailFile>,
        @SerializedName("option") val option: ArrayList<ProductDetailOption>,
        @SerializedName("price") val price: Int,
        @SerializedName("discount") val discount: Int,
        @SerializedName("원가") val beforePrice: Int,
        @SerializedName("size") val productDetailSize: ArrayList<ProductDetailSize>,
        @SerializedName("special") val special: String,
        @SerializedName("배송") val delivery: String,
        @SerializedName("브랜드명") val brandName: String,
        @SerializedName("브랜드주소") val brandAddr: String,
        @SerializedName("스크랩수") val scrapNum: Int,
        @SerializedName("평점") val reviewRating: String
)