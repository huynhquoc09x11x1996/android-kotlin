package com.example.internhbaoquoc.testmvp_kotlin.adpater

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.example.internhbaoquoc.testmvp_kotlin.R
import com.example.internhbaoquoc.testmvp_kotlin.model.SinhVien
import kotlinx.android.synthetic.main.activity_main.view.*
import kotlinx.android.synthetic.main.item_sinhvien.view.*

class AdapterSinhVien(var ctx : Context, var listSV : ArrayList<SinhVien>) : RecyclerView.Adapter<AdapterSinhVien.IHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): IHolder {
        var v = LayoutInflater.from(ctx).inflate(R.layout.item_sinhvien, parent, false)
        return IHolder(v)
    }

    override fun getItemCount(): Int {
        return listSV.size
    }

    override fun onBindViewHolder(holder: IHolder, position: Int) {
        (holder).bindData(listSV[position])
    }

    class IHolder(itemView : View) : RecyclerView.ViewHolder(itemView) {
        fun bindData(sv: SinhVien) {
            itemView.txtItemName.text=sv.name
            itemView.txtItemAge.text=sv.age.toString()
        }
    }
}