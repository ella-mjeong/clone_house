package com.softsquared.template.kotlin.src.main.myPage

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentMyPageBinding
import com.softsquared.template.kotlin.src.login.email.signup.SignUpActivity
import com.softsquared.template.kotlin.src.main.CartActivity
import com.softsquared.template.kotlin.src.main.home.VPAdapter
import com.softsquared.template.kotlin.src.main.home.house.HomeHouseFragment
import com.softsquared.template.kotlin.src.main.home.photo.HomePhotoFragment
import com.softsquared.template.kotlin.src.main.home.popular.HomePopularFragment
import com.softsquared.template.kotlin.src.main.home.pro.HomeProFragment
import com.softsquared.template.kotlin.src.main.home.qna.HomeQnaFragment
import com.softsquared.template.kotlin.src.main.home.tip.HomeTipFragment
import com.softsquared.template.kotlin.src.main.myPage.myShopping.MyPageMyShoppingFragment
import com.softsquared.template.kotlin.src.main.myPage.profile.MyPageProfileFragment

class MyPageFragment :
    BaseFragment<FragmentMyPageBinding>(FragmentMyPageBinding::bind, R.layout.fragment_my_page) {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뷰페이저2 설정
        val fragmentList = listOf(MyPageProfileFragment(), MyPageMyShoppingFragment())
        val adapter = VPAdapter(activity as FragmentActivity)
        adapter.fragments = fragmentList
        binding.vPager2.adapter = adapter
        binding.vPager2.requestDisallowInterceptTouchEvent(true)
        TabLayoutMediator(binding.tabs, binding.vPager2,
                TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                    when (position) {
                        0 ->  tab.text = getString(R.string.my_page_profile)
                        1 ->  tab.text = getString(R.string.my_page_my_shopping)
                    }
                }
        ).attach()

        binding.imgSetting.setOnClickListener {
            startActivity(Intent(context, SettingActivity::class.java))
        }

        binding.imgCart.setOnClickListener {
            startActivity(Intent(context, CartActivity::class.java))
        }


    }
}