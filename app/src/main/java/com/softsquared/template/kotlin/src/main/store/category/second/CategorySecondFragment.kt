package com.softsquared.template.kotlin.src.main.store.category.second

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.View
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.GridLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentCategorySecondBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.home.popular.PopularBannerAdapter
import com.softsquared.template.kotlin.src.main.store.category.BannerCategoryActivity
import com.softsquared.template.kotlin.src.main.store.category.CategoryActivityView
import com.softsquared.template.kotlin.src.main.store.category.CategoryService
import com.softsquared.template.kotlin.src.main.store.category.category
import com.softsquared.template.kotlin.src.main.store.category.first.CategoryFirstFragment
import com.softsquared.template.kotlin.src.main.store.category.third.CategoryThirdFragment
import com.softsquared.template.kotlin.src.main.store.storehome.models.ProductListInfo
import com.softsquared.template.kotlin.src.main.store.storehome.models.ResultStore
import com.softsquared.template.kotlin.src.main.store.storehome.product.RvAdapter

class CategorySecondFragment : BaseFragment<FragmentCategorySecondBinding>(FragmentCategorySecondBinding::bind, R.layout.fragment_category_second),
        CategoryActivityView {

    var currentPosition = 0

    var myListArrayList = ArrayList<ProductListInfo>()
    var mAdapter: RvAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var bundle = arguments
        if(bundle != null){
            var cate2 = bundle.getString("cate2")
            binding.productNameTitle.text = cate2

            var cate1 = bundle.getString("cate1")
            binding.sCategory1.text = cate1
            binding.sCategory2.text = cate2
        }


        binding.btnBack.setOnClickListener {
            var categoryFirstFragment = CategoryFirstFragment()
            categoryFirstFragment.arguments = bundle
            (activity as MainActivity).changeFragment(categoryFirstFragment)
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

        val fragmentList = listOf(BannerCategoryActivity(R.drawable.cate2_b1), BannerCategoryActivity(R.drawable.cate1_b3))
        val adapter = PopularBannerAdapter(activity as FragmentActivity)
        adapter.fragments = fragmentList
        binding.category2Banner.adapter = adapter
        binding.txtAll.text = fragmentList.size.toString()


//        var bundle = Bundle()
        var arrayMore = ArrayList<String>()
        var categoryThirdFragment = CategoryThirdFragment()
        binding.cDesk.setOnClickListener {
            category = "책상"
            bundle!!.putString("cate3","책상")
            arrayMore.add("일반책상")
            arrayMore.add("좌식책상")
            arrayMore.add("스탠딩책상")
            bundle!!.putStringArrayList("more",arrayMore)
            categoryThirdFragment.arguments = bundle
            (activity as MainActivity).changeFragment(categoryThirdFragment)
        }
        binding.cBookshelf.setOnClickListener {
            category = "책장"
            bundle!!.putString("cate3","책장")
            bundle!!.putStringArrayList("more",null)
            categoryThirdFragment.arguments = bundle
            (activity as MainActivity).changeFragment(categoryThirdFragment)
        }
        binding.cChair.setOnClickListener {
            category = "학생/오피스의자"
            bundle!!.putString("cate3","학생/오피스의자")
            arrayMore.add("학생의자")
            arrayMore.add("게이밍의자")
            arrayMore.add("좌식의자")
            bundle!!.putStringArrayList("more",arrayMore)
            categoryThirdFragment.arguments = bundle
            (activity as MainActivity).changeFragment(categoryThirdFragment)
        }
        binding.cOffice.setOnClickListener {
            category = "오피스수납용품"
            bundle!!.putString("cate3","오피스수납용품")
            categoryThirdFragment.arguments = bundle
            (activity as MainActivity).changeFragment(categoryThirdFragment)
        }

    }


    override fun onResume() {
        super.onResume()
        mAdapter = context?.let { RvAdapter(it, myListArrayList) }!!
        CategoryService(this).tryGetCategory()
        val lm = GridLayoutManager(context,2)
        binding.category2Rv.layoutManager = lm
        binding.category2Rv.setHasFixedSize(true)
    }

    override fun onGetCategorySuccess(response: ResultStore) {
        if(response.code == 1000) {
            val myProductList = response.data
            val listSize = response.data.size
            binding.categorySecondItemNum.text = listSize.toString()
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
            binding.category2Rv.adapter = mAdapter
            mAdapter?.notifyDataSetChanged()
        }
    }

    override fun onGetCategoryFailure(message: String) {
        showCustomToast("오류 : $message")
    }

}