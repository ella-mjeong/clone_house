package com.softsquared.template.kotlin.src.main.store.storehome

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.store.storehome.models.ResultStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class StoreHomeService(val view: StoreHomeFragmentView) {

    fun tryGetStore(){
        val storeHomeRetrofitInterface = ApplicationClass.sRetrofit.create(StoreHomeRetrofitInterface::class.java)
        storeHomeRetrofitInterface.getProductList().enqueue(object : Callback<ResultStore> {
            override fun onResponse(call: Call<ResultStore>, response: Response<ResultStore>) {
                view.onGetProductListSuccess(response.body() as ResultStore)
            }

            override fun onFailure(call: Call<ResultStore>, t: Throwable) {
                view.onGetProductListFailure(t.message ?: "통신 오류")
            }
        })
    }

}