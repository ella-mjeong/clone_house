package com.softsquared.template.kotlin.src.login.email.signup.models

import com.google.gson.annotations.SerializedName

data class PostSignUp(
        @SerializedName("email") val email: String,
        @SerializedName("password") val password: String,
        @SerializedName("nickname") val nickname: String
)