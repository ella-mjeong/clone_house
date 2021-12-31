package com.softsquared.template.kotlin.src.main.store.category

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.softsquared.template.kotlin.databinding.FragmentBannerCategoryBinding

class BannerCategoryActivity (val image:Int): Fragment() {

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        val binding = FragmentBannerCategoryBinding.inflate(layoutInflater)

        binding.imgStoreSlideBannerItem.setImageResource(image)

        return binding.root
    }
}