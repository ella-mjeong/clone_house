package com.softsquared.template.kotlin.src.main.myPage.profile

import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.Typeface
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.text.Spannable
import android.text.SpannableString
import android.text.style.ForegroundColorSpan
import android.text.style.StyleSpan
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentMyPageProfileBinding
import com.softsquared.template.kotlin.src.login.email.signin.models.PostSignIn
import com.softsquared.template.kotlin.src.main.home.popular.PopularBannerAdapter
import com.softsquared.template.kotlin.src.main.home.popular.banner.Banner1Fragment

class MyPageProfileFragment : BaseFragment<FragmentMyPageProfileBinding>(FragmentMyPageProfileBinding::bind, R.layout.fragment_my_page_profile) {
    var currentPosition=0
    //핸들러 설정
    val handler= Handler(Looper.getMainLooper()){
        setPage()
        true
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val spanningString = SpannableString(binding.button.text)
        spanningString.setSpan(StyleSpan(Typeface.BOLD),8,14,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        spanningString.setSpan(ForegroundColorSpan(resources.getColor(R.color.colorMainBlue)),8,14,Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
        binding.button.text = spanningString

        val fragmentList = listOf(
            Banner1Fragment(R.drawable.popular_b4),
            Banner1Fragment(R.drawable.popular_b3),
            Banner1Fragment(R.drawable.popular_b7)

        )
        val adapter = PopularBannerAdapter(activity as FragmentActivity)
        adapter.fragments = fragmentList
        binding.myPageBanner.adapter = adapter

        binding.txtAll.text = fragmentList.size.toString()

        //뷰페이저 넘기는 쓰레드
        val thread=Thread(MyPagePagerRunnable())
        thread.start()

    }
    //페이지 변경하기
    private fun setPage(){
        if(currentPosition==3) currentPosition=0
        binding.myPageBanner.currentItem = currentPosition
        currentPosition+=1
        binding.txtCurPage.text = currentPosition.toString()
    }

    //2초 마다 페이지 넘기기
    inner class MyPagePagerRunnable:Runnable {
        override fun run() {
            while (true) {
                Thread.sleep(2000) //추후 4000(4초)로 변경
                handler.sendEmptyMessage(0)
            }
        }
    }
}