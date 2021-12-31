package com.softsquared.template.kotlin.src.main.store.productDetail.review

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.store.productDetail.ProductDetailRetrofitInterface
import com.softsquared.template.kotlin.src.main.store.productDetail.productId
import com.softsquared.template.kotlin.src.main.store.productDetail.review.models.ResultReview
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class ReviewService (val view: ReviewActivityView) {

    fun tryGetReview(){
        val reviewRetrofitInterface = ApplicationClass.sRetrofit.create(ReviewRetrofitInterface::class.java)
        productId?.let {
            reviewRetrofitInterface.getProductComments(it).enqueue(object : Callback<ResultReview> {
                override fun onResponse(call: Call<ResultReview>, response: Response<ResultReview>) {
                    view.onGetProductReviewSuccess(response.body() as ResultReview)
                }

                override fun onFailure(call: Call<ResultReview>, t: Throwable) {
                    view.onGetProductReviewFailure(t.message ?: "통신 오류")
                }
            })
        }
    }

}