package com.softsquared.template.kotlin.src.main.myPage

import android.util.Log
import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.login.email.signin.LoginActivityView
import com.softsquared.template.kotlin.src.login.email.signin.LoginRetrofitInterface
import com.softsquared.template.kotlin.src.login.email.signin.models.PostSignIn
import com.softsquared.template.kotlin.src.login.email.signin.models.ResultSignIn
import com.softsquared.template.kotlin.src.main.myPage.models.ResultLogOut
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class LogOutService(val view: LogOutActivityView) {

    fun tryGetLogOut(){
        val logOutRetrofitInterface = ApplicationClass.sRetrofit.create(LogOutRetrofitInterface::class.java)
        logOutRetrofitInterface.getLogOut().enqueue(object : Callback<ResultLogOut> {
            override fun onResponse(call: Call<ResultLogOut>, response: Response<ResultLogOut>) {
                view.onGetLogOutSuccess(response.body() as ResultLogOut)
                Log.d("login","1성공")
            }

            override fun onFailure(call: Call<ResultLogOut>, t: Throwable) {
                view.onGetLogOutFailure(t.message ?: "통신 오류")
            }
        })
    }

}