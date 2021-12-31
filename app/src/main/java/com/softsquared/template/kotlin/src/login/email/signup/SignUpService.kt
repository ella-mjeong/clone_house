package com.softsquared.template.kotlin.src.login.email.signup

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.login.email.signup.models.PostSignUp
import com.softsquared.template.kotlin.src.login.email.signup.models.ResultSignUp
import retrofit2.Call
import retrofit2.Response
import retrofit2.Callback

class SignUpService(val view: SignUpActivityView) {

    fun tryPostSignUp(postSignUp: PostSignUp){
        val signUpRetrofitInterface = ApplicationClass.sRetrofit.create(SignUpRetrofitInterface::class.java)

        signUpRetrofitInterface.postSignUp(postSignUp).enqueue(object : Callback<ResultSignUp> {
            override fun onResponse(call: Call<ResultSignUp>, response: Response<ResultSignUp>) {
                view.onPostSignUpSuccess(response.body() as ResultSignUp)
            }

            override fun onFailure(call: Call<ResultSignUp>, t: Throwable) {
                view.onPostSignUpFailure(t.message ?: "통신 오류")
            }
        })
    }
}

