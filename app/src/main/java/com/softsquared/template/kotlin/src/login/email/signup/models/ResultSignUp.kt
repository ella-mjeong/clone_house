package com.softsquared.template.kotlin.src.login.email.signup.models

import com.google.gson.annotations.SerializedName

data class ResultSignUp (
        @SerializedName("isSuccess") val isSuccess: Boolean,
        @SerializedName("code") val code: Int,
        @SerializedName("message") val message: String,
)