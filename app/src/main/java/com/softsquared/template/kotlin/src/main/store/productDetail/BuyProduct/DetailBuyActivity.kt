package com.softsquared.template.kotlin.src.main.store.productDetail.BuyProduct

import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.AdapterView
import android.widget.Spinner
import com.softsquared.template.kotlin.config.BaseActivity
import com.softsquared.template.kotlin.databinding.ActivityDetailBuyBinding
import com.softsquared.template.kotlin.src.main.store.productDetail.productDetailInfo
import java.text.DecimalFormat


data class SpinnerData(val optName:String, val price:String)
data class SelectItemData(val opt1Name:String, val opt1Price:Int,val opt2Name:String, val opt2Price:Int)

class DetailBuyActivity : BaseActivity<ActivityDetailBuyBinding>(ActivityDetailBuyBinding::inflate) {

    var spinner1: Spinner? = null
    var spinnerAdapter1: SpinnerAdapter? = null
    var spinnerAdapter2: SpinnerAdapter? = null

    var addProductItemAdapter: AddProductItemAdapter?= null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        getOptionInfo()


    }

    fun  getOptionInfo(){

        var flag = false
        var select = ""

        val dec = DecimalFormat("#,###")
        val optOption = productDetailInfo?.option
        val optColor = productDetailInfo?.productDetailColor
        val optSize = productDetailInfo?.productDetailSize
        var optNum = 0
        val arrayListSize = ArrayList<SpinnerData>()
        val arrayListColor = ArrayList<SpinnerData>()
        val arrayListDisplay = ArrayList<SpinnerData>()

        val arrayListOption = ArrayList<SelectItemData>()

        //arrayList에 내가 스피너에 보여주고 싶은 값 셋팅
        if(optSize!![0].size != null){
            optNum++
            select = "size"
            for (i in 0 until optSize.size){
                arrayListSize.add(SpinnerData(optSize[i].size, dec.format(optSize[i].price).toString()))
            }
            spinnerAdapter1 = SpinnerAdapter(this, arrayListSize, "사이즈선택")
            binding.optSpinner1.adapter = spinnerAdapter1
            //Log.d("select",binding.optSpinner1.selectedItem.toString())

        }

        if(optColor!![0].color != null){
            optNum++
            binding.spinnerBox2.visibility = View.VISIBLE

            for (i in 0 until optColor.size) {
                arrayListColor.add(SpinnerData(optColor[i].color, dec.format(optColor[i].price).toString())
                )
            }

            spinnerAdapter2 = SpinnerAdapter(this, arrayListColor, "색상선택")
            binding.optSpinner2.adapter = spinnerAdapter2

        }

        if(optOption!![0].displayName != null){
            optNum++
            select = "display"
            for (i in 0 until optOption.size){
                arrayListDisplay.add(SpinnerData(optOption[i].displayName, dec.format(optOption[i].price).toString()))
            }

            spinnerAdapter1 = SpinnerAdapter(this, arrayListDisplay, "추가상품")

            binding.optSpinner1.adapter = spinnerAdapter1

        }

        if(optNum == 1) {
            binding.optSpinner1.onItemSelectedListener =
                object : AdapterView.OnItemSelectedListener {

                    override fun onNothingSelected(p0: AdapterView<*>?) {
                    }

                    override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
                        if (flag) {
                            binding.cartItem.visibility = View.VISIBLE
                            if(select == "size") {
                                arrayListOption.add(SelectItemData(arrayListSize[p2].optName, optSize[p2].price, "null", 0))
                            }
                            else if(select == "display"){
                                arrayListOption.add(SelectItemData(arrayListDisplay[p2].optName, optOption[p2].price, "null", 0))
                            }

                            addProductItemAdapter =
                                AddProductItemAdapter(this@DetailBuyActivity, arrayListOption)
                            binding.cartItem.adapter = addProductItemAdapter
                            addProductItemAdapter?.notifyDataSetChanged()
                        }
                        flag = true
                    }
                }

        }
        else if(optNum == 2){
            binding.optSpinner2.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
                override fun onNothingSelected(p0: AdapterView<*>?) {
                    }

                override fun onItemSelected(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {

                    if(flag){
                    binding.cartItem.visibility = View.VISIBLE
                    arrayListOption.add(SelectItemData(arrayListSize[p2].optName, optSize[p2].price, arrayListColor[p2].optName, optColor[p2].price))

                    addProductItemAdapter = AddProductItemAdapter(this@DetailBuyActivity, arrayListOption)
                    binding.cartItem.adapter = addProductItemAdapter

                    }
                    flag = true
                }
            }
        }

    }
}