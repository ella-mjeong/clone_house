package com.softsquared.template.kotlin.src.main.myPage

import com.softsquared.template.kotlin.src.main.myPage.models.ResultLogOut
import retrofit2.Call
import retrofit2.http.GET

interface LogOutRetrofitInterface {
    @GET("/app/sign-out")
    fun getLogOut() : Call<ResultLogOut>
}