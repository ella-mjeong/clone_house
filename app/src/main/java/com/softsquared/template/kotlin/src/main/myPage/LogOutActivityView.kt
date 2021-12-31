package com.softsquared.template.kotlin.src.main.myPage

import com.softsquared.template.kotlin.src.main.myPage.models.ResultLogOut

interface LogOutActivityView {
    fun onGetLogOutSuccess(response: ResultLogOut)

    fun onGetLogOutFailure(message: String)

}