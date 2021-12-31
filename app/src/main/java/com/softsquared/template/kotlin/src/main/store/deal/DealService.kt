package com.softsquared.template.kotlin.src.main.store.deal

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.store.deal.models.ResultDeal
import com.softsquared.template.kotlin.src.main.store.storehome.StoreHomeFragmentView
import com.softsquared.template.kotlin.src.main.store.storehome.StoreHomeRetrofitInterface
import com.softsquared.template.kotlin.src.main.store.storehome.models.ResultStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class DealService(val view: DealFragmentView) {

    fun tryGetDeal(){
        val storeHomeRetrofitInterface = ApplicationClass.sRetrofit.create(DealRetrofitInterface::class.java)
        storeHomeRetrofitInterface.getDealList().enqueue(object : Callback<ResultDeal> {
            override fun onResponse(call: Call<ResultDeal>, response: Response<ResultDeal>) {
                view.onGetDealListSuccess(response.body() as ResultDeal)
            }

            override fun onFailure(call: Call<ResultDeal>, t: Throwable) {
                view.onGetDealListFailure(t.message ?: "통신 오류")
            }
        })
    }

}