q:package com.softsquared.template.kotlin.src.main.store.productDetail.BuyProduct

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.Toast
import com.softsquared.template.kotlin.databinding.ActivityDetailSelectItemBinding
import java.text.DecimalFormat

class AddProductItemAdapter (context: Context, private val selectItemList: ArrayList<SelectItemData>): BaseAdapter() {

    val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
    lateinit var binding:ActivityDetailSelectItemBinding
    var totalPrice = 0
    val dec = DecimalFormat("#,###")

    @SuppressLint("ViewHolder")
    override fun getView(p0: Int, p1: View?, p2: ViewGroup?): View {
        totalPrice = 0
        binding = ActivityDetailSelectItemBinding.inflate(inflater,p2,false)
        binding.txtProduct.text = selectItemList[p0].opt1Name
        totalPrice +=  selectItemList[p0].opt1Price
        if(selectItemList[p0].opt2Name != "null"){
            binding.txtYesDetailProduct.text = "/"
            binding.txtDetailProduct.text = selectItemList[p0].opt2Name
            totalPrice +=  selectItemList[p0].opt2Price
        }

        binding.txtSelectPrice.text = dec.format(totalPrice).toString()

        binding.imgMinus.setOnClickListener {
            var cnt = Integer.parseInt(binding.txtSelectNum.text.toString())
            var tot = Integer.parseInt(binding.txtSelectPrice.text.toString())
            var priceOne = tot/cnt
            if(cnt == 1){
                Toast.makeText(binding.imgMinus.context, "1~9999개까지만 입력이 가능합니다.", Toast.LENGTH_SHORT).show()
            }
            else{
                cnt--
                tot -= priceOne
                binding.txtSelectNum.text = cnt.toString()
                binding.txtSelectPrice.text = tot.toString()
            }
        }

        binding.imgPlus.setOnClickListener {
            var cnt = Integer.parseInt(binding.txtSelectNum.text.toString())
            var tot = totalPrice
            var priceOne = tot/cnt
            if(cnt == 9999){
                Toast.makeText(binding.imgPlus.context, "1~9999개까지만 입력이 가능합니다.", Toast.LENGTH_SHORT).show()
            }
            else{
                cnt++
                tot += priceOne
                binding.txtSelectNum.text = cnt.toString()
                binding.txtSelectPrice.text = tot.toString()
            }
        }

        binding.imgDelete.setOnClickListener {
            selectItemList.removeAt(p0)
            this.notifyDataSetChanged()
        }

        return binding.root
    }

    override fun getItem(p0: Int): Any = selectItemList[p0]

    override fun getItemId(p0: Int): Long = p0.toLong()

    override fun getCount(): Int = selectItemList.size

}

