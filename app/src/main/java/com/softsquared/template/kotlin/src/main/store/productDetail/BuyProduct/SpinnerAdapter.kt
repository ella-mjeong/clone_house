package com.softsquared.template.kotlin.src.main.store.productDetail.BuyProduct

import android.content.Context
import android.graphics.Color
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.src.main.store.productDetail.BuyProduct.SpinnerData


class SpinnerAdapter(val context: Context, val spinnerData: ArrayList<SpinnerData>, val name: String) :BaseAdapter() {

    var Inflater: LayoutInflater = LayoutInflater.from(context)

    override fun getCount(): Int = spinnerData!!.size


    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if (convertView == null) {
            convertView = Inflater.inflate(R.layout.item_spinner, null)
        }

        val optName = convertView!!.findViewById<TextView>(R.id.txt_spinner)

        optName.textSize = 12f
        optName.setTextColor(Color.GRAY)
        optName.text = name

        return convertView
    }

    override fun getDropDownView(position: Int, convertView: View?, parent: ViewGroup?): View? {
        var convertView = convertView
        if (convertView == null) {
            convertView = Inflater.inflate(R.layout.item_spinner_down, null)
        }

        val optDropName = convertView!!.findViewById<TextView>(R.id.spinner_opt_name)
        val optDropPrice = convertView!!.findViewById<TextView>(R.id.spinner_opt_price)



        optDropName.text = spinnerData[position].optName
        optDropPrice.text = spinnerData[position].price

        return convertView
    }

    override fun getItem(position: Int): Any? {
        return spinnerData!![position]
    }

    override fun getItemId(position: Int): Long = position.toLong()
}