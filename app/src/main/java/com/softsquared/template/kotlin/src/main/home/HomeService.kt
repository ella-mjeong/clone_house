package com.softsquared.template.kotlin.src.main.home

class HomeService(val view: HomeFragmentView) {
/*
    fun tryGetUsers(){ //요청을 보내는 곳
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.getUsers().enqueue(object : Callback<ResultSignIn>{
            override fun onResponse(call: Call<ResultSignIn>, response: Response<ResultSignIn>) {
                view.onGetUserSuccess(response.body() as ResultSignIn)
            }

            override fun onFailure(call: Call<ResultSignIn>, t: Throwable) {
                view.onGetUserFailure(t.message ?: "통신 오류")
            }
        })
    }

    fun tryPostSignUp(postSignUp: PostSignUp){
        val homeRetrofitInterface = ApplicationClass.sRetrofit.create(HomeRetrofitInterface::class.java)
        homeRetrofitInterface.postSignUp(postSignUp).enqueue(object : Callback<ResultSignUp>{
            override fun onResponse(call: Call<ResultSignUp>, response: Response<ResultSignUp>) {
                view.onPostSignUpSuccess(response.body() as ResultSignUp)
            }

            override fun onFailure(call: Call<ResultSignUp>, t: Throwable) {
                view.onPostSignUpFailure(t.message ?: "통신 오류")
            }
        })
    }
*/
}
