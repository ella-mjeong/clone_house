package com.softsquared.template.kotlin.src.main.store.category

import com.softsquared.template.kotlin.src.main.store.storehome.models.ResultStore

interface CategoryActivityView {
    fun onGetCategorySuccess(response: ResultStore)

    fun onGetCategoryFailure(message: String)
}