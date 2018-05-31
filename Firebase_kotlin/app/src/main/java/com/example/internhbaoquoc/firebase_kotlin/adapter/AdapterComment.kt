package com.example.internhbaoquoc.firebase_kotlin.adapter

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import com.example.internhbaoquoc.firebase_kotlin.model.PeopleComment

class AdapterComment(var ctx: Context,var listData: ArrayList<PeopleComment>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

    }

    override fun getItem(position: Int): Any {

    }

    override fun getItemId(position: Int): Long {

    }

    override fun getCount(): Int {
        return listData.size
    }
}