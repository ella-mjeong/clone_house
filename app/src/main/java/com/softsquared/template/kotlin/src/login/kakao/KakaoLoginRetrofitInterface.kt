package com.softsquared.template.kotlin.src.login.kakao

import com.softsquared.template.kotlin.src.login.email.signin.models.ResultSignIn
import com.softsquared.template.kotlin.src.login.kakao.models.PostKakaoSignIn
import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.POST

interface KakaoLoginRetrofitInterface {
    @POST("/app/kakao-sign-in")
    fun postKakaoSignIn(@Body params: PostKakaoSignIn): Call<ResultSignIn>
}