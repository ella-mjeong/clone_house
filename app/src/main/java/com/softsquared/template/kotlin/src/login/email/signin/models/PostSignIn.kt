package com.softsquared.template.kotlin.src.login.email.signin.models

import com.google.gson.annotations.SerializedName

data class PostSignIn(
        @SerializedName("email") val email: String,
        @SerializedName("password") val password: String
)