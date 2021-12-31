package com.softsquared.template.kotlin.src.main.store.productDetail.question

import android.os.Bundle
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityDetailBinding
import com.softsquared.template.kotlin.src.main.store.productDetail.question.models.ResultQuestion
import com.softsquared.template.kotlin.src.main.store.productDetail.questionInfo

class QuestionActivity : BaseActivity<ActivityDetailBinding>(ActivityDetailBinding::inflate),
        QuestionActivityView {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onGetProductQuestionSuccess(response: ResultQuestion) {
        if(response.code == 1000) {
            questionInfo = response.data
            binding.productDetailQnaNum.text = questionInfo!!.size.toString()

            //리싸이클러뷰에 넣어주기


            showCustomToast("qna 데이터 가져오기 성공")
        }
    }

    override fun onGetProductQuestionFailure(message: String) {
        showCustomToast("오류 : $message")
    }

}