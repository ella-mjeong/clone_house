package com.softsquared.template.kotlin.src.main.store.productDetail.question


import com.softsquared.template.kotlin.src.main.store.productDetail.question.models.ResultQuestion

interface QuestionActivityView {
    fun onGetProductQuestionSuccess(response: ResultQuestion)

    fun onGetProductQuestionFailure(message: String)

}