package com.softsquared.template.kotlin.src.main.home.popular.banner

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.softsquared.template.kotlin.databinding.FragmentBanner1Binding

class Banner1Fragment(val image:Int):Fragment() {
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBanner1Binding.inflate(layoutInflater)

        binding.imgSlideBannerItem.setImageResource(image)

        return binding.root
    }
}