package com.softsquared.template.kotlin.src.main

import android.os.Bundle
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityCartBinding

class CartActivity : BaseActivity<ActivityCartBinding>(ActivityCartBinding::inflate) {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnBack.setOnClickListener {
            finish()
        }

    }
}