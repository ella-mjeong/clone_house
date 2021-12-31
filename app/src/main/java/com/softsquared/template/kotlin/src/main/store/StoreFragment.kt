package com.softsquared.template.kotlin.src.main.store

import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import com.google.android.material.tabs.TabLayoutMediator
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentStoreBinding
import com.softsquared.template.kotlin.src.main.CartActivity
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.VPAdapter
import com.softsquared.template.kotlin.src.main.store.best.StoreBestFragment
import com.softsquared.template.kotlin.src.main.store.deal.StoreDealFragment
import com.softsquared.template.kotlin.src.main.store.dowry.StoreDowryFragment
import com.softsquared.template.kotlin.src.main.store.plan.StorePlanFragment
import com.softsquared.template.kotlin.src.main.store.premium.StorePremiumFragment
import com.softsquared.template.kotlin.src.main.store.reaper.StoreReaperragment
import com.softsquared.template.kotlin.src.main.store.spring.StoreSpringFragment
import com.softsquared.template.kotlin.src.main.store.storehome.StoreStoreHomeFragment

class StoreFragment : BaseFragment<FragmentStoreBinding>(FragmentStoreBinding::bind, R.layout.fragment_store) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        //뷰페이저2 설정
        val fragmentList = listOf(
            StoreStoreHomeFragment(), StorePremiumFragment(), StoreBestFragment(), StoreDealFragment(),
            StoreReaperragment(), StoreDowryFragment(), StoreSpringFragment(), StorePlanFragment()
        )
        val adapter = VPAdapter(activity as FragmentActivity)
        adapter.fragments = fragmentList
        binding.vPager2.adapter = adapter
        binding.vPager2.requestDisallowInterceptTouchEvent(true)
        TabLayoutMediator(binding.tabs, binding.vPager2,
            TabLayoutMediator.TabConfigurationStrategy { tab, position ->
                when (position) {
                    0 ->  tab.text = getString(R.string.store_tab_store_home)
                    1 ->  tab.text = getString(R.string.store_tab_premium)
                    2 ->  tab.text = getString(R.string.store_tab_best)
                    3 ->  tab.text = getString(R.string.store_tab_today_deal)
                    4 ->  tab.text = getString(R.string.store_tab_reaper)
                    5 ->  tab.text = getString(R.string.store_tab_dowry)
                    6 ->  tab.text = getString(R.string.store_tab_spring)
                    7 ->  tab.text = getString(R.string.store_tab_plan)
                }
            }
        ).attach()

        binding.imgCart.setOnClickListener {
            startActivity(Intent(context, CartActivity::class.java))
        }


    }

}