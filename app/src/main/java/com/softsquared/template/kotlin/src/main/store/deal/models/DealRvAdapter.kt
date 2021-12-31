package com.softsquared.template.kotlin.src.main.store.deal.models

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.src.main.store.productDetail.DetailActivity
import java.text.DecimalFormat

class DealRvAdapter (val context: Context, private val dealList: ArrayList<DealListInfo>) :
        RecyclerView.Adapter<DealRvAdapter.Holder>() {
    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        val rTitleImg = itemView?.findViewById<ImageView>(R.id.img_deal_title)
        val rTop = itemView?.findViewById<TextView>(R.id.txt_deal_top)
        val rName = itemView?.findViewById<TextView>(R.id.txt_deal_name)
        val rScore = itemView?.findViewById<TextView>(R.id.txt_deal_review_score)
        val rDiscount = itemView?.findViewById<TextView>(R.id.deal_discount)
        val rPrice = itemView?.findViewById<TextView>(R.id.txt_deal_price)
        val rReview = itemView?.findViewById<TextView>(R.id.txt_deal_review_num)
        val rDelivery = itemView?.findViewById<TextView>(R.id.txt_deal_delivery_check)
        val rScrap = itemView?.findViewById<TextView>(R.id.txt_deal_scrap_num)
        val rLayoutReview= itemView?.findViewById<View>(R.id.l_deal_review)
        val rLayoutScrap= itemView?.findViewById<View>(R.id.l_deal_scrap)

        val dec = DecimalFormat("#,###")

        var imgLink = "https://minsuk.shop/uploads/"

        @SuppressLint("SetTextI18n")
        fun bind (myList: DealListInfo, context: Context) {

            if (rTitleImg != null) {
                Glide.with(rTitleImg).load(imgLink+myList.image).into(rTitleImg)
            }
            rTop?.text = myList.companyName
            rName?.text = myList.displayName
            rDiscount?.text = myList.discount.toString() + "%"
            rPrice?.text = dec.format(myList.price).toString()

            if(myList.reviewScore == null){
                rLayoutReview?.visibility = View.GONE
                rLayoutScrap?.visibility = View.VISIBLE
                rScrap?.text = myList.scrapNum.toString()
            }
            else{
                rLayoutScrap?.visibility = View.GONE
                rLayoutReview?.visibility = View.VISIBLE
                rScore?.text = myList.reviewScore
                rReview?.text = myList.reviewNum.toString()
            }

            if(myList.delivery == "무료"){
                rDelivery?.visibility = View.VISIBLE
            }
            else{
                rDelivery?.visibility = View.GONE
            }

            itemView.setOnClickListener{
                val intent = Intent(context, DetailActivity::class.java)
                ContextCompat.startActivity(itemView.context, intent, null)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_deal, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return dealList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(dealList[position], context)
    }

}