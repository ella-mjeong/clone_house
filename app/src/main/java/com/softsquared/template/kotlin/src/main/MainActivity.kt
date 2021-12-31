package com.softsquared.template.kotlin.src.main

import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.app.Dialog
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.fragment.app.Fragment
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityMainBinding
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.interior.InteriorFragment
import com.softsquared.template.kotlin.src.main.myPage.MyPageFragment
import com.softsquared.template.kotlin.src.main.store.StoreFragment
import com.softsquared.template.kotlin.src.main.store.storehome.StoreStoreHomeFragment


class MainActivity : BaseActivity<ActivityMainBinding>(ActivityMainBinding::inflate) {



    private var plusClick = false
    private var beforeBtn = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        supportFragmentManager.beginTransaction().replace(R.id.main_frm, HomeFragment()).commitAllowingStateLoss()

        binding.mainBtmNav.itemIconTintList = null

        binding.mainBtmNav.setOnNavigationItemSelectedListener(
            BottomNavigationView.OnNavigationItemSelectedListener { item ->
                when (item.itemId) {
                    R.id.menu_main_btm_nav_home -> {
                        beforeBtn = 0
                        binding.imgPlus.setImageResource(R.drawable.plus_before)
                        if(plusClick){
                            var rotateLeft = ObjectAnimator.ofFloat(binding.imgPlus, View.ROTATION,45f,0f)
                            rotateLeft.duration = 200
                            rotateLeft.start()
                        }
                        plusClick = false
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, HomeFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_store -> {
                        beforeBtn = 1
                        binding.imgPlus.setImageResource(R.drawable.plus_before)
                        if(plusClick){
                            var rotateLeft = ObjectAnimator.ofFloat(binding.imgPlus, View.ROTATION,45f,0f)
                            rotateLeft.duration = 200
                            rotateLeft.start()
                        }
                        plusClick = false
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, StoreFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_interior -> {
                        beforeBtn = 2
                        binding.imgPlus.setImageResource(R.drawable.plus_before)
                        if(plusClick){
                            var rotateLeft = ObjectAnimator.ofFloat(binding.imgPlus, View.ROTATION,45f,0f)
                            rotateLeft.duration = 200
                            rotateLeft.start()
                        }
                        plusClick = false
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, InteriorFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                    R.id.menu_main_btm_nav_my_page -> {
                        beforeBtn = 3
                        binding.imgPlus.setImageResource(R.drawable.plus_before)
                        if(plusClick){
                            var rotateLeft = ObjectAnimator.ofFloat(binding.imgPlus, View.ROTATION,45f,0f)
                            rotateLeft.duration = 200
                            rotateLeft.start()
                        }
                        plusClick = false
                        supportFragmentManager.beginTransaction()
                            .replace(R.id.main_frm, MyPageFragment())
                            .commitAllowingStateLoss()
                        return@OnNavigationItemSelectedListener true
                    }
                }
                false
            })

        binding.imgPlus.setOnClickListener {

            if(!plusClick){
                var rotateRight = ObjectAnimator.ofFloat(binding.imgPlus, View.ROTATION,0f,45f)
                rotateRight.duration = 200
                rotateRight.start()
                binding.mainBtmNav.menu.setGroupCheckable(0, true, false)
                for (i in 0 until binding.mainBtmNav.menu.size()) {
                    binding.mainBtmNav.menu.getItem(i).isChecked = false
                }
                binding.mainBtmNav.menu.setGroupCheckable(0, true, true)
                plusClick = true

                changeFragment(PlusFragment())
            }
            else{
                var rotateLeft = ObjectAnimator.ofFloat(binding.imgPlus, View.ROTATION,45f,0f)
                rotateLeft.duration = 200
                rotateLeft.start()
                binding.mainBtmNav.menu.getItem(beforeBtn).isChecked = false
                when(beforeBtn){
                    0 -> {changeFragment(HomeFragment())}
                    1 -> {changeFragment(StoreFragment())}
                    2 -> {changeFragment(InteriorFragment())}
                    3 -> {changeFragment(MyPageFragment())}
                }
                plusClick = false
            }

        }
    }


    @SuppressLint("ResourceType")
    fun changeFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction()
                .replace(R.id.main_frm, fragment)
                .commitAllowingStateLoss()
    }

    fun setMenuChange(item:Int){
        binding.mainBtmNav.menu.getItem(item).isChecked = true
    }


}