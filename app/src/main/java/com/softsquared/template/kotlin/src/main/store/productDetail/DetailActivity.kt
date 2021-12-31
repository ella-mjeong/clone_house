package com.softsquared.template.kotlin.src.main.store.productDetail

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Paint
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.tabs.TabLayout
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityDetailBinding
import com.softsquared.template.kotlin.src.main.store.productDetail.BuyProduct.DetailBuyActivity
import com.softsquared.template.kotlin.src.main.store.productDetail.models.DetailInfoRvAdapter
import com.softsquared.template.kotlin.src.main.store.productDetail.models.info.ProductDetailInfo
import com.softsquared.template.kotlin.src.main.store.productDetail.models.info.ResultProductDetail
import com.softsquared.template.kotlin.src.main.store.productDetail.question.QuestionActivityView
import com.softsquared.template.kotlin.src.main.store.productDetail.question.QuestionService
import com.softsquared.template.kotlin.src.main.store.productDetail.question.models.Question
import com.softsquared.template.kotlin.src.main.store.productDetail.question.models.ResultQuestion
import com.softsquared.template.kotlin.src.main.store.productDetail.review.ReviewActivity
import com.softsquared.template.kotlin.src.main.store.productDetail.review.ReviewActivityView
import com.softsquared.template.kotlin.src.main.store.productDetail.review.ReviewService
import com.softsquared.template.kotlin.src.main.store.productDetail.review.models.ResultReview
import com.softsquared.template.kotlin.src.main.store.productDetail.review.models.ReviewInfo
import com.softsquared.template.kotlin.src.main.store.productDetail.review.models.ReviewOption
import java.text.DecimalFormat
import java.util.ArrayList
import kotlin.math.roundToInt

var productDetailInfo: ProductDetailInfo?= null
var questionInfo: ArrayList<Question> ?= null
var reviewInfo: ReviewInfo?=null

class DetailActivity  : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate),
        ProductDetailActivityView, ReviewActivityView,QuestionActivityView {

    val dec = DecimalFormat("#,###")
    var imgLink = "https://minsuk.shop/uploads/"
    var myDetailFileList = ArrayList<String>()
    var myReviewList = ArrayList<ReviewOption>()

    var detailAdapter: DetailInfoRvAdapter? = null
    var reviewAdapter : ReviewRvAdapter?= null

    @SuppressLint("ClickableViewAccessibility")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding.productMainScroll.run{
            header = binding.tabs

            stickListener = { _ ->
                Log.d("LOGGER_TAG", "stickListener")
            }
            freeListener = { _ ->
                Log.d("LOGGER_TAG", "freeListener")
            }
        }

        binding.tabs.addOnTabSelectedListener(object : TabLayout.OnTabSelectedListener {
            override fun onTabReselected(tab: TabLayout.Tab?) {
                var pos = tab?.position
                if(pos == 0){
                    binding.productMainScroll.scrollTo(0,binding.lProductDetailTab1.top-(130))
                }
                if(pos == 1){
                    binding.productMainScroll.scrollTo(0,binding.lProductDetailTab2.top-(binding.lReviewWrite.height))
                }
                if(pos == 2){
                    binding.productMainScroll.scrollTo(0,binding.lQnaTab3.top-(binding.lQnaTab3.height+130))
                }
                if(pos == 3){
                    binding.productMainScroll.scrollTo(0,binding.lDeliveryRefundTab4.top-(binding.lDeliveryRefundTab4.height+130))
                }
                if(pos == 4){
                    binding.productMainScroll.scrollTo(0,binding.lTab5.top-(binding.lTab5.height+130))
                }
            }

            override fun onTabUnselected(tab: TabLayout.Tab?) {
            }

            override fun onTabSelected(tab: TabLayout.Tab?) {
                var pos = tab?.position
                if(pos == 0){
                    binding.productMainScroll.scrollTo(0,binding.lProductDetailTab1.top-(130))
                }
                if(pos == 1){
                    binding.productMainScroll.scrollTo(0,binding.lProductDetailTab2.top-(binding.lReviewWrite.height))
                }
                if(pos == 2){
                    binding.productMainScroll.scrollTo(0,binding.lQnaTab3.top-(binding.lQnaTab3.height+130))
                }
                if(pos == 3){
                    binding.productMainScroll.scrollTo(0,binding.lDeliveryRefundTab4.top-(binding.lDeliveryRefundTab4.height+130))
                }
                if(pos == 4){
                    binding.productMainScroll.scrollTo(0,binding.lTab5.top-(binding.lTab5.height+130))
                }
            }

        }
        )

        binding.btnBack.setOnClickListener {
            finish()
        }

        binding.reviewBtnMore.setOnClickListener {
            startActivity(Intent(this, ReviewActivity::class.java))
        }


        binding.btnBuy.setOnClickListener {
            startActivity(Intent(this, DetailBuyActivity::class.java))
        }

        binding.imgScrap.setOnClickListener {


        }

    }

    override fun onResume() {
        super.onResume()

        //리싸이클러뷰 어댑터 생성 및 연결
        detailAdapter = DetailInfoRvAdapter(this, myDetailFileList)
        showLoadingDialog(this)
        ProductDetailService(this).tryGetDetail()

        //리싸이클러뷰 매니저 설정
        val lm = LinearLayoutManager(this)
        binding.detailRv.layoutManager = lm
        binding.detailRv.setHasFixedSize(true)

        //리싸이클러뷰 어댑터 생성 및 연결
        reviewAdapter = ReviewRvAdapter(this, myReviewList)
        ReviewService(this).tryGetReview()

        //리싸이클러뷰 매니저 설정
        val lmReview = LinearLayoutManager(this)
        binding.productReviewRv.layoutManager = lmReview
        binding.productReviewRv.setHasFixedSize(true)

        dismissLoadingDialog()

        QuestionService(this).tryGetQuestion()


    }

    @SuppressLint("SetTextI18n")
    fun setInfo(){
        binding.productNameTitle.text = productDetailInfo!!.displayname
        binding.productDetailName.text = productDetailInfo!!.displayname

        binding.productDetailCompany.text = productDetailInfo!!.brandName
        binding.productDetailCompany2.text = productDetailInfo!!.brandName

        binding.productDetailReviewRating.rating = productDetailInfo!!.reviewRating.toFloat()

        binding.productDetailBeforePrice.text = dec.format(productDetailInfo!!.beforePrice).toString()
        binding.productDetailBeforePrice.paintFlags = binding.productDetailBeforePrice.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG
        binding.productDetailDiscount.text = productDetailInfo!!.discount.toString()+"%"
        binding.productDetailPrice.text = dec.format(productDetailInfo!!.price).toString()

        if(productDetailInfo!!.special != "특가"){
            binding.productDetailPriceCheck.visibility= View.GONE
        }

        val point = (productDetailInfo!!.price * 0.3).roundToInt()/100
        binding.productDetailSavePoint.text = point.toString()

        binding.txtScrapNum.text = dec.format(productDetailInfo!!.scrapNum).toString()

        if( productDetailInfo!!.delivery != "무료"){
            binding.productDetailDelivery.text = "3,000원 〉"
        }

        Glide.with(binding.productDetailTitle).load(imgLink+productDetailInfo!!.productDetailFile[0].fileURL).into(binding.productDetailTitle)

        val listSize = productDetailInfo!!.productDetailFile.size

        myDetailFileList.clear()
        for(i in 0 until listSize) {
            myDetailFileList.add(productDetailInfo!!.productDetailFile[i].fileURL)
        }

        binding.detailRv.adapter = detailAdapter
        detailAdapter?.notifyDataSetChanged()
    }


    override fun onGetProductDetailSuccess(response: ResultProductDetail) {
        if(response.code == 1000) {
            productDetailInfo = response.data
            setInfo()
        }

    }

    override fun onGetProductDetailFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    @SuppressLint("SetTextI18n")
    override fun onGetProductReviewSuccess(response: ResultReview) {
            reviewInfo = response.data
            var totNum = reviewInfo!!.reviewTotalNum[0].cnt
            binding.productDetailReviewNum.text = "("+reviewInfo!!.reviewTotalNum[0].cnt.toString()+")"
            binding.txtListReviewWriteNum.text = reviewInfo!!.reviewTotalNum[0].cnt.toString()
            binding.productDetailReviewNum2.text = reviewInfo!!.reviewTotalNum[0].cnt.toString()

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
            if(listSize < 3) {
                for (i in 0 until listSize) {
                    myReviewList.add(ReviewOption(reviewInfo!!.reviewOption[i].content, reviewInfo!!.reviewOption[i].nickname, reviewInfo!!.reviewOption[i].rateDelivery,
                            reviewInfo!!.reviewOption[i].rateDesign, reviewInfo!!.reviewOption[i].rateDurability, reviewInfo!!.reviewOption[i].ratePrice,
                            reviewInfo!!.reviewOption[i].date, reviewInfo!!.reviewOption[i].good, reviewInfo!!.reviewOption[i].rating))
                }
            }
            else{
                for (i in 0 until 3) {
                    myReviewList.add(ReviewOption(reviewInfo!!.reviewOption[i].content, reviewInfmo!!.reviewOption[i].nickname, reviewInfo!!.reviewOption[i].rateDelivery,
                            reviewInfo!!.reviewOption[i].rateDesign, reviewInfo!!.reviewOption[i].rateDurability, reviewInfo!!.reviewOption[i].ratePrice,
                            reviewInfo!!.reviewOption[i].date, reviewInfo!!.reviewOption[i].good, reviewInfo!!.reviewOption[i].rating))
                }
            }
            binding.productReviewRv.adapter = reviewAdapter
            reviewAdapter?.notifyDataSetChanged()

    }

    override fun onGetProductReviewFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onGetProductQuestionSuccess(response: ResultQuestion) {
        if(response.code == 1000) {
            questionInfo = response.data
            binding.productDetailQnaNum.text = questionInfo!!.size.toString()
        }
    }

    override fun onGetProductQuestionFailure(message: String) {
        showCustomToast("오류 : $message")
    }


}

