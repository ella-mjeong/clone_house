package com.softsquared.template.kotlin.src.login.kakao.models

import com.google.gson.annotations.SerializedName
import com.kakao.sdk.user.model.Profile

data class PostKakaoSignIn(
        @SerializedName("email") val email: String,
        @SerializedName("password") val password: String,
        @SerializedName("nickname") val nickname: Profile?
)