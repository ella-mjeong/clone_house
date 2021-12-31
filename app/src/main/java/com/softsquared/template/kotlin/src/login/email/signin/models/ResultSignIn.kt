package com.softsquared.template.kotlin.src.login.email.signin.models

import com.google.gson.annotations.SerializedName

data class ResultSignIn (
        @SerializedName("userInfo") val userInfo: UserInfo,
        @SerializedName("jwt") val jwt: String,
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String
)