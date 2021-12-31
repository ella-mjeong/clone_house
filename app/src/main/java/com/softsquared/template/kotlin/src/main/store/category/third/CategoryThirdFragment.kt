package com.softsquared.template.kotlin.src.main.store.category.third

import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.recyclerview.widget.GridLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentCategoryThirdBinding
import com.softsquared.template.kotlin.src.main.MainActivity
import com.softsquared.template.kotlin.src.main.store.category.CategoryActivityView
import com.softsquared.template.kotlin.src.main.store.category.CategoryService
import com.softsquared.template.kotlin.src.main.store.category.second.CategorySecondFragment
import com.softsquared.template.kotlin.src.main.store.storehome.models.ProductListInfo
import com.softsquared.template.kotlin.src.main.store.storehome.models.ResultStore
import com.softsquared.template.kotlin.src.main.store.storehome.product.RvAdapter

class CategoryThirdFragment : BaseFragment<FragmentCategoryThirdBinding>(FragmentCategoryThirdBinding::bind, R.layout.fragment_category_third),
        CategoryActivityView {

    var myListArrayList = ArrayList<ProductListInfo>()
    var mAdapter: RvAdapter? = null

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        var bundle = arguments
        if(bundle != null){
            var cate3 = bundle.getString("cate3")
            binding.productNameTitle.text = cate3

            var cate2 = bundle.getString("cate2")
            var cate1 = bundle.getString("cate1")
            binding.tCategory1.text = cate1
            binding.tCategory2.text = cate2
            binding.tCategory3.text = cate3

            var more = bundle.getStringArrayList("more")
            if(more == null){
                binding.categoryDetailMore.visibility = View.GONE
            }
            else{
                binding.cMenu1.text = more[0]
                binding.cMenu2.text = more[1]
                binding.cMenu3.text = more[2]
            }

        }


        binding.btnBack.setOnClickListener {
            var categorySecondFragment = CategorySecondFragment()
            categorySecondFragment.arguments = bundle
            (activity as MainActivity).changeFragment(categorySecondFragment)
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

    }
    override fun onResume() {
        super.onResume()
        mAdapter = context?.let { RvAdapter(it, myListArrayList) }!!
        CategoryService(this).tryGetCategory()
        val lm = GridLayoutManager(context,2)
        binding.category3Rv.layoutManager = lm
        binding.category3Rv.setHasFixedSize(true)
    }

    override fun onGetCategorySuccess(response: ResultStore) {
        if(response.code == 1000) {
            val myProductList = response.data
            val listSize = response.data.size
            binding.categoryThirdItemNum.text = listSize.toString()
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
            binding.category3Rv.adapter = mAdapter
            mAdapter?.notifyDataSetChanged()
        }
    }

    override fun onGetCategoryFailure(message: String) {
        showCustomToast("오류 : $message")
    }

}