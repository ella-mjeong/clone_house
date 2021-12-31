package com.softsquared.template.kotlin.src.main.store.storehome

import com.softsquared.template.kotlin.src.main.store.storehome.models.ResultStore
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.POST
import retrofit2.http.Path

interface StoreHomeRetrofitInterface {

    @GET("/app/store")
    fun getProductList(): Call<ResultStore>

}