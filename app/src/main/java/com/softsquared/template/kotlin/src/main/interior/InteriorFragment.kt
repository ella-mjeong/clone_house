package com.softsquared.template.kotlin.src.main.interior

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentInteriorBinding
import com.softsquared.template.kotlin.src.main.CartActivity

class InteriorFragment : BaseFragment<FragmentInteriorBinding>(FragmentInteriorBinding::bind, R.layout.fragment_interior) {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.imgCart.setOnClickListener {
            startActivity(Intent(context, CartActivity::class.java))
        }


    }
}