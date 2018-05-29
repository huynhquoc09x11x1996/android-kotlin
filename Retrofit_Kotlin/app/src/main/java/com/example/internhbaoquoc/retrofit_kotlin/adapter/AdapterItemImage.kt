package com.example.internhbaoquoc.retrofit_kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.internhbaoquoc.retrofit_kotlin.R
import kotlinx.android.synthetic.main.item_multi_imggae.view.*
import android.graphics.BitmapFactory




class AdapterItemImage(var ctx: Context, var listImg: ArrayList<String>) : RecyclerView.Adapter<AdapterItemImage.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view = LayoutInflater.from(ctx).inflate(R.layout.item_multi_imggae, parent, false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return listImg.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        (holder).bindData(listImg[position])
    }

    /*
    * tọa bitmap từ path string của mỗi item sang bitmap để imageview set bitmap mỗi item
    * */
    class MyHolder(itemView :View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(path: String) {
            val bmImg = BitmapFactory.decodeFile(path)
            itemView.imgItem.setImageBitmap(bmImg)
        }
    }
}
