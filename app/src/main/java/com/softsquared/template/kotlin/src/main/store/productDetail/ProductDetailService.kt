package com.softsquared.template.kotlin.src.main.store.productDetail

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.store.productDetail.models.info.ResultProductDetail
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var productId:String ?= "1"
class ProductDetailService(val view: ProductDetailActivityView) {

    fun tryGetDetail(){
        val productDetailRetrofitInterface = ApplicationClass.sRetrofit.create(ProductDetailRetrofitInterface::class.java)
        productId?.let {
            productDetailRetrofitInterface.getProductDetail(it).enqueue(object : Callback<ResultProductDetail> {
            override fun onResponse(call: Call<ResultProductDetail>, response: Response<ResultProductDetail>) {
                view.onGetProductDetailSuccess(response.body() as ResultProductDetail)
            }

            override fun onFailure(call: Call<ResultProductDetail>, t: Throwable) {
                view.onGetProductDetailFailure(t.message ?: "통신 오류")
            }
        })
        }
    }
}