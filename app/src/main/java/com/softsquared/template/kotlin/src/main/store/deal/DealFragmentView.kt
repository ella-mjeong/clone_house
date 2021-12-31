package com.softsquared.template.kotlin.src.main.store.deal

import com.softsquared.template.kotlin.src.main.store.deal.models.ResultDeal


interface DealFragmentView {
    fun onGetDealListSuccess(response: ResultDeal)

    fun onGetDealListFailure(message: String)

}