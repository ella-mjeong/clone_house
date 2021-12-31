package com.softsquared.template.kotlin.src.main.store.productDetail.question

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.store.productDetail.ProductDetailActivityView
import com.softsquared.template.kotlin.src.main.store.productDetail.ProductDetailRetrofitInterface
import com.softsquared.template.kotlin.src.main.store.productDetail.models.info.ResultProductDetail
import com.softsquared.template.kotlin.src.main.store.productDetail.productId
import com.softsquared.template.kotlin.src.main.store.productDetail.question.models.ResultQuestion
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class QuestionService (val view: QuestionActivityView) {

    fun tryGetQuestion(){
        val questionRetrofitInterface = ApplicationClass.sRetrofit.create(QuestionRetrofitInterface::class.java)
        productId?.let {
            questionRetrofitInterface.getProductQuestion(it).enqueue(object : Callback<ResultQuestion> {
                override fun onResponse(call: Call<ResultQuestion>, response: Response<ResultQuestion>) {
                    view.onGetProductQuestionSuccess(response.body() as ResultQuestion)
                }

                override fun onFailure(call: Call<ResultQuestion>, t: Throwable) {
                    view.onGetProductQuestionFailure(t.message ?: "통신 오류")
                }
            })
        }
    }

}