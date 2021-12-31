package com.softsquared.template.kotlin.src.login.email.signup

import com.softsquared.template.kotlin.src.login.email.signup.models.ResultSignUp

interface SignUpActivityView {
    fun onPostSignUpSuccess(response: ResultSignUp)

    fun onPostSignUpFailure(message: String)
}