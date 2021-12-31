package com.softsquared.template.kotlin.src.login.email.signin

import com.softsquared.template.kotlin.src.login.email.signin.models.PostSignIn
import com.softsquared.template.kotlin.src.login.email.signin.models.ResultSignIn
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface LoginRetrofitInterface {
    @POST("/app/sign-in")
    fun postSignIn(@Body params: PostSignIn): Call<ResultSignIn>
}