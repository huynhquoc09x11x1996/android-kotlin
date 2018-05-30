package com.example.internhbaoquoc.firebase_kotlin.adapter

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.text.Layout
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.internhbaoquoc.firebase_kotlin.R
import com.example.internhbaoquoc.firebase_kotlin.model.StatusDataModel
import kotlinx.android.synthetic.main.item_status.view.*

class AdapterStatus(val ctx: Context , val listData: ArrayList<StatusDataModel>): RecyclerView.Adapter<AdapterStatus.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        var view = LayoutInflater.from(ctx).inflate(R.layout.item_status,parent,false)
        return MyHolder(view)
    }

    override fun getItemCount(): Int {
        return listData.size
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        (holder).bindData(listData.get(position))
    }

    class MyHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
        fun bindData(stt: StatusDataModel)
        {
            itemView.txtName.text=stt.getName()
            itemView.txtHour.text=stt.getHour()
            itemView.txtContentText.text=stt.getContentText()
            itemView.txtNumLike.text=stt.getNumLike().toString()
            itemView.txtNumCmt.text=stt.getNumCmt().toString()
        }
    }
}