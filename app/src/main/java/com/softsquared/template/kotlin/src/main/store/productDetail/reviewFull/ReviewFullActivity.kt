package com.softsquared.template.kotlin.src.main.store.productDetail.reviewFull

import android.os.Bundle
import android.util.Log
import android.view.View
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityReviewFullBinding

class ReviewFullActivity : BaseActivity<ActivityReviewFullBinding>(ActivityReviewFullBinding::inflate){

    var clickMore = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.reviewMore.setOnClickListener {
            Log.d("tag","클릭")
            if(!clickMore) {
                binding.ratingMoreDetail!!.visibility = View.VISIBLE
                clickMore = true
            }
            else{
                binding.ratingMoreDetail!!.visibility = View.GONE
                clickMore = false
            }
        }

        binding.reviewNickname.text = intent.getStringExtra("nickname")
        binding.reviewRating.rating = intent.getStringExtra("rating")!!.toFloat()
        binding.reviewRatingDurability.rating = intent.getIntExtra("rateDurability",0).toFloat()
        binding.reviewRatingPrice.rating = intent.getIntExtra("ratePrice",0).toFloat()
        binding.reviewRatingDesign.rating = intent.getIntExtra("rateDesign",0).toFloat()
        binding.reviewRatingDelivery.rating = intent.getIntExtra("rateDelivery",0).toFloat()
        binding.reviewDate.text = intent.getStringExtra("date")
        binding.reviewContext.text = intent.getStringExtra("content")
        binding.reviewGood.text = intent.getStringExtra("good")

    }
}