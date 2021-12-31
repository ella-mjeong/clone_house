package com.softsquared.template.kotlin.src.main.store.productDetail.models

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.src.main.store.productDetail.models.info.ProductDetailFile


class DetailInfoRvAdapter  (val context: Context, private val productList: ArrayList<String>) :
        RecyclerView.Adapter<DetailInfoRvAdapter.Holder>() {

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        private val rDetailImg = itemView?.findViewById<ImageView>(R.id.item_product_info)

        var imgLink = "https://minsuk.shop/uploads/"

        fun bind (myList: String, context: Context) {

            /* 나머지 TextView와 String 데이터를 연결한다. */
            if (rDetailImg != null) {
                Glide.with(rDetailImg).load(imgLink+myList).into(rDetailImg)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product_detail_info, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(productList[position], context)

    }
}