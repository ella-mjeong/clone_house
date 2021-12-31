package com.softsquared.template.kotlin.src.main.home.popular

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentStatePagerAdapter
import com.softsquared.template.kotlin.src.main.myPage.MyPageFragment
import com.softsquared.template.kotlin.src.main.store.StoreFragment

class AdapterFragment(fm:FragmentManager, private val fragmentCount : Int) : FragmentStatePagerAdapter(fm){
    override fun getItem(position: Int): Fragment {
        return when(position){
            0 -> StoreFragment()
            else -> MyPageFragment()
        }
    }

    override fun getCount(): Int = fragmentCount


}