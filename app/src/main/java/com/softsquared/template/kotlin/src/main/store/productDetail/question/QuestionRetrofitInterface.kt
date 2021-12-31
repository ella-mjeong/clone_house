package com.softsquared.template.kotlin.src.main.store.productDetail.question

import com.softsquared.template.kotlin.src.main.store.productDetail.question.models.ResultQuestion
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface QuestionRetrofitInterface {

    @GET("/products/questions/{id}")
    fun getProductQuestion(@Path("id")id:String): Call<ResultQuestion>
}