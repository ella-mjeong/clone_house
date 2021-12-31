package com.softsquared.template.kotlin.src.main.store.category

import com.softsquared.template.kotlin.config.ApplicationClass
import com.softsquared.template.kotlin.src.main.store.storehome.StoreHomeFragmentView
import com.softsquared.template.kotlin.src.main.store.storehome.StoreHomeRetrofitInterface
import com.softsquared.template.kotlin.src.main.store.storehome.models.ResultStore
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

var category = ""

class CategoryService (val view: CategoryActivityView) {

    fun tryGetCategory(){
        val categoryRetrofitInterface = ApplicationClass.sRetrofit.create(CategoryRetrofitInterface::class.java)
        categoryRetrofitInterface.getCategory(category).enqueue(object : Callback<ResultStore> {
            override fun onResponse(call: Call<ResultStore>, response: Response<ResultStore>) {
                view.onGetCategorySuccess(response.body() as ResultStore)
            }

            override fun onFailure(call: Call<ResultStore>, t: Throwable) {
                view.onGetCategoryFailure(t.message ?: "통신 오류")
            }
        })
    }

}