package com.softsquared.template.kotlin.src.main.store.category

import com.softsquared.template.kotlin.src.main.store.storehome.models.ResultStore
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface CategoryRetrofitInterface  {
    @GET("/app/store/category")
    fun getCategory(@Query("category")category:String): Call<ResultStore>
}