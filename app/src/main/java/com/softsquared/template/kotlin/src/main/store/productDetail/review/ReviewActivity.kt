package com.softsquared.template.kotlin.src.main.store.productDetail.review

import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityReviewBinding
import com.softsquared.template.kotlin.src.main.store.productDetail.ReviewRvAdapter
import com.softsquared.template.kotlin.src.main.store.productDetail.review.models.ResultReview
import com.softsquared.template.kotlin.src.main.store.productDetail.review.models.ReviewOption
import com.softsquared.template.kotlin.src.main.store.productDetail.reviewInfo
import java.util.ArrayList

class ReviewActivity: BaseActivity<ActivityReviewBinding>(ActivityReviewBinding::inflate),
        ReviewActivityView {

    var myReviewList = ArrayList<ReviewOption>()

    var reviewAdapter : ReviewMoreRvAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.btnBack.setOnClickListener {
            finish()
        }

    }

    override fun onResume() {
        super.onResume()
        
        reviewAdapter = ReviewMoreRvAdapter(this, myReviewList)
        ReviewService(this).tryGetReview()

        val lmReview = LinearLayoutManager(this)
        binding.productReviewRv.layoutManager = lmReview
        binding.productReviewRv.setHasFixedSize(true)
    }

    override fun onGetProductReviewSuccess(response: ResultReview) {
        reviewInfo = response.data

        //전제평점, 그거 별로 나타낸거, 프로그래스바
        var totNum = reviewInfo!!.reviewTotalNum[0].cnt
        binding.txtListReviewWriteNum.text = reviewInfo!!.reviewTotalNum[0].cnt.toString()

        binding.reviewScoreBig.text = reviewInfo!!.reviewTotalRate
        binding.reviewRatingBarBig.rating = reviewInfo!!.reviewTotalRate.toFloat()

        var size = reviewInfo!!.reviewRating.size
        for(i in 0 until size) {
            var num = reviewInfo!!.reviewRating[i].cnt
            when (reviewInfo!!.reviewRating[i].rating) {
                1 ->{binding.pBar1.max = totNum
                    binding.pBar1.progress = num
                    binding.pBar1Num.text = num.toString()}
                2 ->{binding.pBar2.max = totNum
                    binding.pBar2.progress = num
                    binding.pBar2Num.text = num.toString()}
                3 ->{binding.pBar3.max = totNum
                    binding.pBar3.progress = num
                    binding.pBar3Num.text = num.toString()}
                4 ->{binding.pBar4.max = totNum
                    binding.pBar4.progress = num
                    binding.pBar4Num.text = num.toString()}
                5 ->{ binding.pBar5.max = totNum
                    binding.pBar5.progress = num
                    binding.pBar5Num.text = num.toString()}
            }
        }

        //리뷰 리싸이클러뷰
        val listSize = reviewInfo!!.reviewOption.size
        myReviewList.clear()
        for(i in 0 until listSize) {
            myReviewList.add(ReviewOption(reviewInfo!!.reviewOption[i].content, reviewInfo!!.reviewOption[i].nickname, reviewInfo!!.reviewOption[i].rateDelivery,
                    reviewInfo!!.reviewOption[i].rateDesign, reviewInfo!!.reviewOption[i].rateDurability, reviewInfo!!.reviewOption[i].ratePrice,
                    reviewInfo!!.reviewOption[i].date, reviewInfo!!.reviewOption[i].good, reviewInfo!!.reviewOption[i].rating))
        }

       binding.productReviewRv.adapter = reviewAdapter
        reviewAdapter?.notifyDataSetChanged()
    }

    override fun onGetProductReviewFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}