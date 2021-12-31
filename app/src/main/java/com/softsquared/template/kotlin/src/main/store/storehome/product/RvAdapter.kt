package com.softsquared.template.kotlin.src.main.store.storehome.product

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
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
import com.softsquared.template.kotlin.src.main.store.productDetail.productId
import com.softsquared.template.kotlin.src.main.store.storehome.models.ProductListInfo
import java.text.DecimalFormat

class RvAdapter (val context: Context, private val productList: ArrayList<ProductListInfo>) :
        RecyclerView.Adapter<RvAdapter.Holder>() {

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        private val rTitleImg = itemView?.findViewById<ImageView>(R.id.img_title)
        private val rTop = itemView?.findViewById<TextView>(R.id.txt_top)
        private val rName = itemView?.findViewById<TextView>(R.id.txt_name)
        private val rScore = itemView?.findViewById<TextView>(R.id.txt_review_score)
        private val rDiscount = itemView?.findViewById<TextView>(R.id.discount)
        private val rPrice = itemView?.findViewById<TextView>(R.id.txt_price)
        private val rReview = itemView?.findViewById<TextView>(R.id.txt_review_num)
        private val rDelivery = itemView?.findViewById<TextView>(R.id.txt_delivery_check)
        private val rSpecialPrice = itemView?.findViewById<TextView>(R.id.txt_price_check)
        private val rScrap = itemView?.findViewById<TextView>(R.id.txt_scrap_num)
        private val rLayoutReview= itemView?.findViewById<View>(R.id.l_review)
        private val rLayoutScrap= itemView?.findViewById<View>(R.id.l_scrap)
        private val rImgScrap = itemView?.findViewById<ImageView>(R.id.img_scrap)

        val dec = DecimalFormat("#,###")

        var imgLink = "https://minsuk.shop/uploads/"

        @SuppressLint("SetTextI18n")
        fun bind (myList: ProductListInfo, context: Context) {

            /* 나머지 TextView와 String 데이터를 연결한다. */
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
            if(myList.specialPrice == "특가"){
                rSpecialPrice?.visibility = View.VISIBLE
            }
            else{
                rSpecialPrice?.visibility = View.GONE
            }

            itemView.setOnClickListener{
                productId = (adapterPosition + 1).toString()
                Log.d("select",productId.toString())
               val intent = Intent(context, DetailActivity::class.java)
                ContextCompat.startActivity(itemView.context, intent, null)
            }


        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(productList[position], context)

    }
}