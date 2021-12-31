package com.softsquared.template.kotlin.src.main.home.popular

//import cn.trinea.android.view.autoscrollviewpager.AutoScrollViewPager
import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import android.widget.TextView
import androidx.annotation.RequiresApi
import androidx.fragment.app.FragmentActivity
import com.google.android.material.bottomnavigation.BottomNavigationMenu
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentHomePopularBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.HomeFragment
import com.softsquared.template.kotlin.src.main.home.popular.banner.Banner1Fragment
import com.softsquared.template.kotlin.src.main.store.StoreFragment
import com.softsquared.template.kotlin.src.main.store.category.category
import com.softsquared.template.kotlin.src.main.store.category.first.CategoryFirstActivity
import com.softsquared.template.kotlin.src.main.store.category.first.CategoryFirstFragment
import com.softsquared.template.kotlin.src.main.store.productDetail.review.ReviewActivity


class HomePopularFragment : BaseFragment<FragmentHomePopularBinding>(FragmentHomePopularBinding::bind, R.layout.fragment_home_popular) {
    var currentPosition=0
    //핸들러 설정
    val handler= Handler(Looper.getMainLooper()){
        setPage()
        true
    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.lCurrent.layoutParams.width = 200
        binding.lCurrentContent.layoutParams.width = 200

        val fragmentList = listOf(Banner1Fragment(R.drawable.popular_b1), Banner1Fragment(R.drawable.popular_b2),Banner1Fragment(R.drawable.popular_b3)
        ,Banner1Fragment(R.drawable.popular_b4),Banner1Fragment(R.drawable.popular_b5),Banner1Fragment(R.drawable.popular_b6),Banner1Fragment(R.drawable.popular_b7)
        ,Banner1Fragment(R.drawable.popular_b8),Banner1Fragment(R.drawable.popular_b9),Banner1Fragment(R.drawable.popular_b10))
        val adapter = PopularBannerAdapter(activity as FragmentActivity)
        adapter.fragments = fragmentList
        binding.banner.adapter = adapter

        binding.txtAll.text = fragmentList.size.toString()

        //뷰페이저 넘기는 쓰레드
        val thread=Thread(PagerRunnable())
        thread.start()

//        binding.lBanner.setOnClickListener {
//            binding.banner.requestDisallowInterceptTouchEvent(true)
//            if(binding.banner.currentItem != currentPosition){
//                binding.txtCurPage.text = (binding.banner.currentItem+1).toString()
//            }
//        }

        binding.imgShopping.setOnClickListener {
            Log.d("Popular","shopping 클릭")

            (activity as MainActivity).setMenuChange(1)
            (activity as MainActivity).changeFragment(StoreFragment())
        }

        binding.categoryGagu.setOnClickListener {
            var intent = Intent(context, CategoryFirstActivity::class.java)
            intent.putExtra("cate1","가구")
            startActivity(intent)
        }

    }
    //페이지 변경하기
    private fun setPage(){
        if(currentPosition==10) currentPosition=0
        binding.banner.currentItem = currentPosition
        currentPosition+=1
        if(currentPosition == 10) {
            binding.lCurrent.layoutParams.width = 220
            binding.lCurrentContent.layoutParams.width = 220
        }
        else{
            binding.lCurrent.layoutParams.width = 200
            binding.lCurrentContent.layoutParams.width = 200
        }
        binding.txtCurPage.text = currentPosition.toString()

    }

    //4초 마다 페이지 넘기기
    inner class PagerRunnable:Runnable {
        override fun run() {
            while (true) {
                Thread.sleep(4000)
                handler.sendEmptyMessage(0)
            }
        }
    }
}

