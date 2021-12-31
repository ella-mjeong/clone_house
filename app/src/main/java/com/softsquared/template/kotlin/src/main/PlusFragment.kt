package com.softsquared.template.kotlin.src.main

import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentStoreBinding
import com.softsquared.template.kotlin.databinding.LayoutPlusBinding

class PlusFragment : BaseFragment<LayoutPlusBinding>(LayoutPlusBinding::bind, R.layout.layout_plus) {


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

    }

}
