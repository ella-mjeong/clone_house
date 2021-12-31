package com.softsquared.template.kotlin.src.main.store.productDetail.review

import com.softsquared.template.kotlin.src.main.store.productDetail.review.models.ResultReview
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path

interface ReviewRetrofitInterface {
    @GET("/products/comments/{id}")
    fun getProductComments(@Path("id")id:String): Call<ResultReview>
}