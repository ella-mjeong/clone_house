package com.softsquared.template.kotlin.src.login.kakao

import com.softsquared.template.kotlin.src.login.email.signin.models.ResultSignIn

interface KakaoLoginActivityView {
    fun onPostKakaoSignInSuccess(response: ResultSignIn)

    fun onPostKakaoSignInFailure(message: String)
}