package com.softsquared.template.kotlin.src.main.store.storehome

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.softsquared.template.kotlin.databinding.FragmentBanner2Binding

class Banner2Fragment (val image:Int): Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {

        val binding = FragmentBanner2Binding.inflate(layoutInflater)

        binding.imgStoreSlideBannerItem.setImageResource(image)

        return binding.root
    }
}