package com.softsquared.template.kotlin.src.main.store.category.first

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentCategoryFirstBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.popular.PopularBannerAdapter
import com.softsquared.template.kotlin.src.main.store.category.BannerCategoryActivity
import com.softsquared.template.kotlin.src.main.store.category.CategoryActivityView
import com.softsquared.template.kotlin.src.main.store.category.CategoryService
import com.softsquared.template.kotlin.src.main.store.category.category
import com.softsquared.template.kotlin.src.main.store.category.second.CategorySecondFragment
import com.softsquared.template.kotlin.src.main.store.storehome.StoreStoreHomeFragment
import com.softsquared.template.kotlin.src.main.store.storehome.models.ProductListInfo
import com.softsquared.template.kotlin.src.main.store.storehome.models.ResultStore
import com.softsquared.template.kotlin.src.main.store.storehome.product.RvAdapter


class CategoryFirstFragment : BaseFragment<FragmentCategoryFirstBinding>(FragmentCategoryFirstBinding::bind, R.layout.fragment_category_first),
        CategoryActivityView {
//    var currentPosition=0
//    //핸들러 설정
//    val handler= Handler(Looper.getMainLooper()){
//        setPage()
//        true
//    }

    var myListArrayList = ArrayList<ProductListInfo>()
    var mAdapter: RvAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var bundle = arguments
        if(bundle != null){
            var cate1 = bundle.getString("cate1")
            binding.categoryFirstTitle.text = cate1
        }

        binding.btnBack.setOnClickListener {
            (activity as MainActivity).changeFragment(StoreStoreHomeFragment())
        }

        binding.productMainScroll.run{
            header = binding.secondTitle

            stickListener = { _ ->
                Log.d("LOGGER_TAG", "stickListener")
            }
            freeListener = { _ ->
                Log.d("LOGGER_TAG", "freeListener")
            }
        }

        val fragmentList = listOf(BannerCategoryActivity(R.drawable.cate1_b1), BannerCategoryActivity(R.drawable.cate1_b2), BannerCategoryActivity(R.drawable.cate1_b3))
        val adapter = PopularBannerAdapter(activity as FragmentActivity)
        adapter.fragments = fragmentList
        binding.category1Banner.adapter = adapter
        binding.txtAll.text = fragmentList.size.toString()

//        val thread=Thread(Category1PagerRunnable())
//        thread.start()

        //학생/서재가구 클릭
        binding.btnCategory5.setOnClickListener {
            var categorySecondFragment = CategorySecondFragment()
            category = "학생/서재가구"
            bundle!!.putString("cate2","학생/서재가구")
            categorySecondFragment.arguments = bundle
            (activity as MainActivity).changeFragment(categorySecondFragment)
        }



    }
//
//    private fun setPage(){
//        if(currentPosition==3) currentPosition=0
//        binding.category1Banner.currentItem = currentPosition
//        currentPosition+=1
//        binding.txtCurPage.text = currentPosition.toString()
//    }
//
//    inner class Category1PagerRunnable:Runnable {
//        override fun run() {
//            while (true) {
//                Thread.sleep(4000)
//                handler.sendEmptyMessage(0)
//            }
//        }
//    }

    override fun onResume() {
        super.onResume()
        mAdapter = context?.let { RvAdapter(it, myListArrayList) }!!
        CategoryService(this).tryGetCategory()
        val lm = GridLayoutManager(context,2)
        binding.category1Rv.layoutManager = lm
        binding.category1Rv.setHasFixedSize(true)
    }

    override fun onGetCategorySuccess(response: ResultStore) {
        if(response.code == 1000) {
            val myProductList = response.data
            val listSize = response.data.size
            binding.categoryFirstItemNum.text = listSize.toString()
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
            binding.category1Rv.adapter = mAdapter
            mAdapter?.notifyDataSetChanged()
        }
    }

    override fun onGetCategoryFailure(message: String) {
        showCustomToast("오류 : $message")
    }

}