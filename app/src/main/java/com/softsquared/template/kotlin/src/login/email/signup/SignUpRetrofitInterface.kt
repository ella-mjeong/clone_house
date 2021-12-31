package com.softsquared.template.kotlin.src.login.email.signup

import com.softsquared.template.kotlin.src.login.email.signup.models.PostSignUp
import com.softsquared.template.kotlin.src.login.email.signup.models.ResultSignUp
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface SignUpRetrofitInterface {

    @POST("/app/sign-up")
    fun postSignUp(@Body params: PostSignUp): Call<ResultSignUp>

}