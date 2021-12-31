package com.softsquared.template.kotlin.src.main.store.productDetail

import com.softsquared.template.kotlin.src.main.store.productDetail.models.info.ResultProductDetail

interface ProductDetailActivityView {
    fun onGetProductDetailSuccess(response: ResultProductDetail)

    fun onGetProductDetailFailure(message: String)

}