package com.softsquared.template.kotlin.src.login.email.signin

import com.softsquared.template.kotlin.src.login.email.signin.models.ResultSignIn

interface LoginActivityView {
    fun onPostSignInSuccess(response: ResultSignIn)

    fun onPostSignInFailure(message: String)
}