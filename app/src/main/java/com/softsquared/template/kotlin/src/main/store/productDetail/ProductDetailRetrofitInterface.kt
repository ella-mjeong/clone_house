package com.softsquared.template.kotlin.src.main.store.productDetail

import com.softsquared.template.kotlin.src.main.store.productDetail.models.info.ResultProductDetail
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ProductDetailRetrofitInterface {

    @GET("/products/{id}")
    fun getProductDetail(@Path("id")id:String): Call<ResultProductDetail>

}