package com.softsquared.template.kotlin.src.main.store.productDetail.review

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.src.main.store.productDetail.DetailActivity
import com.softsquared.template.kotlin.src.main.store.productDetail.ReviewRvAdapter
import com.softsquared.template.kotlin.src.main.store.productDetail.productId
import com.softsquared.template.kotlin.src.main.store.productDetail.review.models.ReviewOption
import com.softsquared.template.kotlin.src.main.store.productDetail.reviewFull.ReviewFullActivity
import java.text.SimpleDateFormat

class ReviewMoreRvAdapter (val context: Context, private val productList: ArrayList<ReviewOption>) :
        RecyclerView.Adapter<ReviewMoreRvAdapter.Holder>() {

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        private val rNickname = itemView?.findViewById<TextView>(R.id.review_rv_nickname)
        private val rRate = itemView?.findViewById<RatingBar>(R.id.review_rv_rating)
        private val rDate = itemView?.findViewById<TextView>(R.id.review_rv_date)
        private val rContext = itemView?.findViewById<TextView>(R.id.review_rv_context)
        private val rRateDurability = itemView?.findViewById<RatingBar>(R.id.review_rv_rating_durability)
        private val rRatePrice = itemView?.findViewById<RatingBar>(R.id.review_rv_rating_price)
        private val rRateDesign = itemView?.findViewById<RatingBar>(R.id.review_rv_rating_design)
        private val rRateDelivery = itemView?.findViewById<RatingBar>(R.id.review_rv_rating_delivery)

        private val rRateMore = itemView?.findViewById<TextView>(R.id.review_rv_more)
        private val rRateLayout = itemView?.findViewById<LinearLayout>(R.id.rv_rating_more_detail)
        private val rGood = itemView?.findViewById<TextView>(R.id.rv_review_good)

        var clickMore = false

        @SuppressLint("SimpleDateFormat")
        val dateFormat = SimpleDateFormat("yyyy.MM.dd")

        fun bind (myList: ReviewOption, context: Context) {

            rNickname?.text = myList.nickname
            rRate?.rating = myList.rating.toFloat()
            rRateDurability?.rating = myList.rateDurability.toFloat()
            rRatePrice?.rating = myList.ratePrice.toFloat()
            rRateDesign?.rating = myList.rateDesign.toFloat()
            rRateDelivery?.rating = myList.rateDelivery.toFloat()
            rGood?.text = myList.good.toString()
            var tmp = myList.date
            var date = "20"+ tmp.substring(0,2)+"."+tmp.substring(3,5)+"."+tmp.substring(6)

            rDate?.text = date
            rContext?.text = myList.content

            rRateMore!!.setOnClickListener{
                if(!clickMore) {
                    rRateLayout!!.visibility = View.VISIBLE
                    clickMore = true
                }
                else{
                    rRateLayout!!.visibility = View.GONE
                    clickMore = false
                }
            }

            itemView.setOnClickListener{
                val intent = Intent(context, ReviewFullActivity::class.java)
                intent.putExtra("nickname",myList.nickname)
                intent.putExtra("rating", myList.rating)
                intent.putExtra("rateDurability", myList.rateDurability)
                intent.putExtra("ratePrice", myList.ratePrice)
                intent.putExtra("rateDesign", myList.rateDesign)
                intent.putExtra("rateDelivery", myList.rateDelivery)
                intent.putExtra("date", date)
                intent.putExtra("content", myList.content)
                intent.putExtra("good",myList.good.toString())
                ContextCompat.startActivity(itemView.context, intent, null)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_review_more, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(productList[position], context)

    }
}