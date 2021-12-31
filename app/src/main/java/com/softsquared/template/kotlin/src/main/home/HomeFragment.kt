package com.softsquared.template.kotlin.src.main.home

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.View
import android.view.View.OnTouchListener
import android.widget.ScrollView
import androidx.fragment.app.FragmentActivity
import androidx.fragment.app.FragmentTransaction
import com.google.android.material.tabs.TabLayoutMediator
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentHomeBinding
import com.softsquared.template.kotlin.src.login.LoginActivity
import com.softsquared.template.kotlin.src.main.CartActivity
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.house.HomeHouseFragment
import com.softsquared.template.kotlin.src.main.home.photo.HomePhotoFragment
import com.softsquared.template.kotlin.src.main.home.popular.HomePopularFragment
import com.softsquared.template.kotlin.src.main.home.pro.HomeProFragment
import com.softsquared.template.kotlin.src.main.home.qna.HomeQnaFragment
import com.softsquared.template.kotlin.src.main.home.tip.HomeTipFragment
import com.softsquared.template.kotlin.src.main.store.StoreFragment


class HomeFragment : BaseFragment<FragmentHomeBinding>(FragmentHomeBinding::bind, R.layout.fragment_home),

        HomeFragmentView {

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뷰페이저2 설정
        val fragmentList = listOf(HomePopularFragment(), HomePhotoFragment(),HomeHouseFragment(), HomeTipFragment(),
        HomeProFragment(), HomeQnaFragment())
        val adapter = VPAdapter(activity as FragmentActivity)
        adapter.fragments = fragmentList
        binding.vPager2.adapter = adapter
        binding.vPager2.requestDisallowInterceptTouchEvent(true)
        TabLayoutMediator(binding.tabs, binding.vPager2,
                TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                    when (position) {
                        0 ->  tab.text = getString(R.string.home_tab_popular)
                        1 ->  tab.text = getString(R.string.home_tab_photo)
                        2 ->  tab.text = getString(R.string.home_tab_house)
                        3 ->  tab.text = getString(R.string.home_tab_tip)
                        4 ->  tab.text = getString(R.string.home_tab_pro)
                        5 ->  tab.text = getString(R.string.home_tab_qna)
                    }
                }
        ).attach()

        binding.mainScrollView.run {
            header = binding.tabs
            stickListener = { _ ->
                Log.d("LOGGER_TAG", "stickListener")
            }
            freeListener = { _ ->
                Log.d("LOGGER_TAG", "freeListener")
            }
        }

        binding.imgCart.setOnClickListener {
            startActivity(Intent(context, CartActivity::class.java))
        }

    }


    @SuppressLint("ResourceType")
    fun changeFragment(){
        childFragmentManager.beginTransaction().replace(R.layout.fragment_home, HomeHouseFragment())
            .setTransition(FragmentTransaction.TRANSIT_FRAGMENT_OPEN).commit()
    }
}