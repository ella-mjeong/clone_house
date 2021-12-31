package com.softsquared.template.kotlin.src.main.store.deal

import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.config.BaseFragment
import com.softsquared.template.kotlin.databinding.FragmentStoreDealBinding
//import com.softsquared.template.kotlin.databinding.FragmentStoreDealBinding
import com.softsquared.template.kotlin.src.main.home.popular.PopularBannerAdapter
import com.softsquared.template.kotlin.src.main.store.deal.models.DealListInfo
import com.softsquared.template.kotlin.src.main.store.deal.models.DealRvAdapter
import com.softsquared.template.kotlin.src.main.store.deal.models.ResultDeal
import com.softsquared.template.kotlin.src.main.store.storehome.StoreHomeService
import com.softsquared.template.kotlin.src.main.store.storehome.models.ProductListInfo
import com.softsquared.template.kotlin.src.main.store.storehome.product.RvAdapter
import java.util.*


class StoreDealFragment : BaseFragment<FragmentStoreDealBinding>(FragmentStoreDealBinding::bind, R.layout.fragment_store_deal),
   DealFragmentView{

    var myDealArrayList = ArrayList<DealListInfo>()
    var currentPosition=0
    //핸들러 설정
    val handler = Handler(Looper.getMainLooper()) {
        setPage()
        true
    }
    var myBanner = ArrayList<Int>()
    lateinit var mAdapter: DealRvAdapter

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        myBanner.add(R.drawable.deal1)
        myBanner.add(R.drawable.deal2)
        myBanner.add(R.drawable.deal3)
        myBanner.add(R.drawable.deal4)
//        뷰페이저 넘기는 쓰레드
        val thread=Thread(PagerRunnable())
        thread.start()

    }

    override fun onResume() {
        super.onResume()
        //리싸이클러뷰 어댑터 생성 및 연결
        mAdapter = context?.let { DealRvAdapter(it, myDealArrayList) }!!
        DealService(this).tryGetDeal()



        //리싸이클러뷰 매니저 설정
        val lm = LinearLayoutManager(context)
        binding.dealRV.layoutManager = lm
        //item이 추가되거나 삭제될 때 RecyclerView의 크기가 변경될 수도 있고,
        // 그렇게 되면 계층 구조의 다른 View 크기가 변경될 가능성이 있기 때문에 setHasFixedSize(true)로 설정
        // 특히 item이 자주 추가/삭제되면 오류가 날 수도 있기에 setHasFixedSize true를 설정한다.
        binding.dealRV.setHasFixedSize(true)

    }

    fun setPage(){
        if(currentPosition==4) currentPosition=0
        binding.imgBanner.setImageResource(myBanner[currentPosition])
        currentPosition+=1
    }

    inner class PagerRunnable:Runnable {
        override fun run() {
            while (true) {
                Thread.sleep(300)
                handler.sendEmptyMessage(0)
            }
        }
    }

    override fun onGetDealListSuccess(response: ResultDeal) {
        if(response.code == 1000) {
            val myProductList = response.data
            val listSize = response!!.data!!.size
            myDealArrayList.clear()
            for(i  in 0 until listSize){
                myDealArrayList.add(DealListInfo(myProductList[i].image,myProductList[i].displayName,myProductList[i].price,
                        myProductList[i].companyName,myProductList[i].discount,myProductList[i].reviewScore,myProductList[i].scrapNum,
                        myProductList[i].reviewNum,myProductList[i].delivery))
            }
            binding.dealRV.adapter = mAdapter
            mAdapter?.notifyDataSetChanged()
        }
    }

    override fun onGetDealListFailure(message: String) {
        showCustomToast("오류 : $message")
    }
}