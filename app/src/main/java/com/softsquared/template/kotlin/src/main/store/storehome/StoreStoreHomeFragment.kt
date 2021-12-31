package com.softsquared.template.kotlin.src.main.store.storehome

import android.content.Intent
import android.os.Bundle
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentStoreStorehomeBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.popular.PopularBannerAdapter
import com.softsquared.template.kotlin.src.main.store.category.category
import com.softsquared.template.kotlin.src.main.store.category.first.CategoryFirstFragment
import com.softsquared.template.kotlin.src.main.store.deal.DealFragmentView
import com.softsquared.template.kotlin.src.main.store.deal.DealService
import com.softsquared.template.kotlin.src.main.store.deal.models.DealListInfo
import com.softsquared.template.kotlin.src.main.store.deal.models.DealRvAdapter
import com.softsquared.template.kotlin.src.main.store.deal.models.ResultDeal
import com.softsquared.template.kotlin.src.main.store.storehome.models.ProductListInfo
import com.softsquared.template.kotlin.src.main.store.storehome.models.ResultStore
import com.softsquared.template.kotlin.src.main.store.storehome.product.RvAdapter
import kotlin.collections.ArrayList



class StoreStoreHomeFragment : BaseFragment<FragmentStoreStorehomeBinding>(FragmentStoreStorehomeBinding::bind, R.layout.fragment_store_storehome),
    StoreHomeFragmentView, DealFragmentView {
    //private val myStoreBanner = view?.findViewById<ViewPager2>(R.id.my_banner)
    var myListArrayList = ArrayList<ProductListInfo>()
    //var myProductList = ArrayList<ProductListInfo>()
    var myDealArrayList = java.util.ArrayList<DealListInfo>()

    var mAdapter: RvAdapter? = null
    var dealAdapter:DealRvAdapter? =null
//    var curPosition=0
//    val handler = Handler(Looper.getMainLooper()) {
//        nowPage()
//        true
//    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragmentList = listOf(Banner2Fragment(R.drawable.store_home_b1), Banner2Fragment(R.drawable.store_home_b2), Banner2Fragment(R.drawable.store_home_b3)
                , Banner2Fragment(R.drawable.store_home_b4), Banner2Fragment(R.drawable.store_home_b5), Banner2Fragment(R.drawable.store_home_b6))
        val adapter = PopularBannerAdapter(activity as FragmentActivity)
        adapter.fragments = fragmentList
        binding.myBanner.adapter = adapter

        //        뷰페이저 넘기는 쓰레드
//        val thread=Thread(Pager2Runnable())
//        thread.start()

        binding.btnMore.setOnClickListener {
            binding.imgMore.setImageResource(R.drawable.menu8)
            binding.txtMore.text = getString(R.string.store_home_menu8_click)
            binding.btnMore.setBackgroundColor(resources.getColor(R.color.white))
            binding.row3.visibility = View.VISIBLE
            binding.row4.visibility = View.VISIBLE
            binding.row5.visibility = View.VISIBLE
        }

        binding.row5.setOnClickListener {
            binding.imgMore.setImageResource(R.drawable.menu_more)
            binding.txtMore.text = getString(R.string.store_home_menu8)
            binding.btnMore.setBackgroundColor(resources.getColor(R.color.browser_actions_bg_grey))
            binding.row3.visibility = View.GONE
            binding.row4.visibility = View.GONE
            binding.row5.visibility = View.GONE
        }

        binding.btnStoreDealMore.setOnClickListener {
            startActivity(Intent(context, TodayDealActivity::class.java))
        }
        binding.txtStoreDealMore.setOnClickListener {
            startActivity(Intent(context, TodayDealActivity::class.java))
        }

        binding.categoryGagu.setOnClickListener {
            var categoryFirstFragment = CategoryFirstFragment()
            category = "가구"
            var bundle = Bundle()
            bundle.putString("cate1","가구")
            categoryFirstFragment.arguments = bundle
            (activity as MainActivity).changeFragment(categoryFirstFragment)
            //var intent = Intent(context, CategoryFirstActivity::class.java)
            //"가구" 전달
            //startActivity(intent)
        }



    }

    override fun onResume() {
        super.onResume()

        //오늘의 딜 리싸이클러뷰 어댑터 생성 및 연결
        dealAdapter = context?.let { DealRvAdapter(it, myDealArrayList) }!!
        DealService(this).tryGetDeal()
        val lmDeal = LinearLayoutManager(context)
        binding.storeDealRV.layoutManager = lmDeal
        binding.storeDealRV.setHasFixedSize(true)

        //인기상품 리싸이클러뷰 어댑터 생성 및 연결
        mAdapter = context?.let { RvAdapter(it, myListArrayList) }!!
        StoreHomeService(this).tryGetStore()
        val lm = GridLayoutManager(context,2)
        binding.mRecyclerView.layoutManager = lm
        binding.mRecyclerView.setHasFixedSize(true)
    }


//    private fun nowPage(){
//    if(curPosition>=6) curPosition=0
//        myStoreBanner?.currentItem = curPosition
//    curPosition+=1
//    }
//
//    //2초 마다 페이지 넘기기
//    inner class Pager2Runnable:Runnable {
//        override fun run() {
//            while (true) {
//                Thread.sleep(2000)
//                handler.sendEmptyMessage(0)
//            }
//        }
//    }

    override fun onGetProductListSuccess(response: ResultStore) {
        if(response.code == 1000) {
            val myProductList = response.data
            val listSize = response!!.data!!.size
            myListArrayList.clear()
            for(i in 0 until listSize){
                if(myProductList[i].specialPrice == null){
                    myListArrayList.add(ProductListInfo(myProductList[i].image,myProductList[i].displayName,myProductList[i].price,
                            myProductList[i].companyName,myProductList[i].discount,myProductList[i].reviewScore,myProductList[i].scrapNum,
                            myProductList[i].reviewNum,myProductList[i].delivery,""))
                }
                else {
                    myListArrayList.add(ProductListInfo(myProductList[i].image, myProductList[i].displayName, myProductList[i].price,
                            myProductList[i].companyName, myProductList[i].discount, myProductList[i].reviewScore, myProductList[i].scrapNum,
                            myProductList[i].reviewNum, myProductList[i].delivery, myProductList[i].specialPrice))
                }
            }
            binding.mRecyclerView.adapter = mAdapter
            mAdapter?.notifyDataSetChanged()
        }
    }

    override fun onGetProductListFailure(message: String) {
        showCustomToast("오류 : $message")
    }

    override fun onGetDealListSuccess(response: ResultDeal) {
        if(response.code == 1000) {
            val myProductList = response.data
            myDealArrayList.clear()
            for(i in 0 until 4){
                myDealArrayList.add(
                    DealListInfo(myProductList[i].image,myProductList[i].displayName,myProductList[i].price,
                    myProductList[i].companyName,myProductList[i].discount,myProductList[i].reviewScore,myProductList[i].scrapNum,
                    myProductList[i].reviewNum,myProductList[i].delivery)
                )
            }
            binding.storeDealRV.adapter = dealAdapter
            dealAdapter?.notifyDataSetChanged()
        }
    }

    override fun onGetDealListFailure(message: String) {
        showCustomToast("오류 : $message")
    }

}

