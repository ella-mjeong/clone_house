package com.softsquared.template.kotlin.src.login.kakao

import android.util.Log
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.login.email.signin.models.ResultSignIn
import com.softsquared.template.kotlin.src.login.kakao.models.PostKakaoSignIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class KakaoLoginService (val view: KakaoLoginActivityView) {

    fun tryPostKakaoSignIn(postSignIn: PostKakaoSignIn){
        val kakaoLoginRetrofitInterface = ApplicationClass.sRetrofit.create(KakaoLoginRetrofitInterface::class.java)
        kakaoLoginRetrofitInterface.postKakaoSignIn(postSignIn).enqueue(object : Callback<ResultSignIn> {
            override fun onResponse(call: Call<ResultSignIn>, response: Response<ResultSignIn>) {
                view.onPostKakaoSignInSuccess(response.body() as ResultSignIn)
                Log.d("login","1성공")
            }

            override fun onFailure(call: Call<ResultSignIn>, t: Throwable) {
                view.onPostKakaoSignInFailure(t.message ?: "통신 오류")
            }
        })
    }

}