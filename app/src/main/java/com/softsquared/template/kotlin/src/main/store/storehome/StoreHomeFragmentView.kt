package com.softsquared.template.kotlin.src.main.store.storehome

import com.softsquared.template.kotlin.src.main.store.storehome.models.ResultStore

interface StoreHomeFragmentView {
    fun onGetProductListSuccess(response: ResultStore)

    fun onGetProductListFailure(message: String)

}