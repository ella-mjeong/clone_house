package com.softsquared.template.kotlin.src.main.store.deal

import com.softsquared.template.kotlin.src.main.store.deal.models.ResultDeal
import retrofit2.Call
import retrofit2.http.GET

interface DealRetrofitInterface {

    @GET("/app/store/today-deals")
    fun getDealList(): Call<ResultDeal>

}