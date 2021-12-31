package com.softsquared.template.kotlin.src.login.email.signin

import android.util.Log
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.login.email.signin.models.PostSignIn
import com.softsquared.template.kotlin.src.login.email.signin.models.ResultSignIn
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LoginService(val view: LoginActivityView) {

    fun tryPostSignIn(postSignIn: PostSignIn){
        val loginRetrofitInterface = ApplicationClass.sRetrofit.create(LoginRetrofitInterface::class.java)
        loginRetrofitInterface.postSignIn(postSignIn).enqueue(object : Callback<ResultSignIn> {
            override fun onResponse(call: Call<ResultSignIn>, response: Response<ResultSignIn>) {
                view.onPostSignInSuccess(response.body() as ResultSignIn)
                Log.d("login","1성공")
            }

            override fun onFailure(call: Call<ResultSignIn>, t: Throwable) {
                view.onPostSignInFailure(t.message ?: "통신 오류")
            }
        })
    }

}