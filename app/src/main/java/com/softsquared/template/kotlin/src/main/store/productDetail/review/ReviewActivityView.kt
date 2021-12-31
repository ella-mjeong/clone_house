package com.softsquared.template.kotlin.src.main.store.productDetail.review

import com.softsquared.template.kotlin.src.main.store.productDetail.review.models.ResultReview

interface ReviewActivityView  {
    fun onGetProductReviewSuccess(response: ResultReview)

    fun onGetProductReviewFailure(message: String)
}