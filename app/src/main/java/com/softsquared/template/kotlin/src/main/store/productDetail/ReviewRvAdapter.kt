package com.softsquared.template.kotlin.src.main.store.productDetail

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.RatingBar
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.softsquared.template.kotlin.R
import com.softsquared.template.kotlin.src.main.store.productDetail.review.ReviewActivity
import com.softsquared.template.kotlin.src.main.store.productDetail.review.models.ReviewOption
import com.softsquared.template.kotlin.src.main.store.productDetail.reviewFull.ReviewFullActivity
import java.text.SimpleDateFormat

class ReviewRvAdapter (val context: Context, private val productList: ArrayList<ReviewOption>) :
        RecyclerView.Adapter<ReviewRvAdapter.Holder>() {

    inner class Holder(itemView: View?) : RecyclerView.ViewHolder(itemView!!) {

        private val rNickname = itemView?.findViewById<TextView>(R.id.rv_nickname)
        private val rRate = itemView?.findViewById<RatingBar>(R.id.rv_rating)
        private val rDate = itemView?.findViewById<TextView>(R.id.rv_date)
        private val rContext = itemView?.findViewById<TextView>(R.id.rv_context)

        @SuppressLint("SimpleDateFormat")
        val dateFormat = SimpleDateFormat("yyyy.MM.dd")

        fun bind (myList: ReviewOption, context: Context) {

            rNickname?.text = myList.nickname
            rRate?.rating = myList.rating.toFloat()
            var tmp = myList.date
            var date = "20"+ tmp.substring(0,2)+"."+tmp.substring(3,5)+"."+tmp.substring(6)

            rDate?.text = date
            rContext?.text = myList.content

            itemView.setOnClickListener{
                val intent = Intent(context, ReviewFullActivity::class.java)
                ContextCompat.startActivity(itemView.context, intent, null)
            }



        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(context).inflate(R.layout.item_product_detail_review, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return productList.size
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder?.bind(productList[position], context)

    }
}