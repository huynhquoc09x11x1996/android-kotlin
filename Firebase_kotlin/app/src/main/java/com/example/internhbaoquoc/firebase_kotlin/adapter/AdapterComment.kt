package com.example.internhbaoquoc.firebase_kotlin.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.TextView
import com.example.internhbaoquoc.firebase_kotlin.R
import com.example.internhbaoquoc.firebase_kotlin.model.PeopleComment
import com.example.internhbaoquoc.firebase_kotlin.utils.TinhGio

//
class AdapterComment(var ctx: Context,var listData: ArrayList<PeopleComment>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {

        var infla: LayoutInflater = LayoutInflater.from(ctx)
        val view = infla.inflate(R.layout.item_status, null)
        var txtPPCmt = view.findViewById(R.id.txtPeopleCmt) as TextView
        var txtPPHourCmt = view.findViewById(R.id.txtHourCmt) as TextView
        var txtPPcontentCmt = view.findViewById(R.id.txtPeopleContentCmt) as TextView
        var cmt= listData.get(position)

        txtPPCmt.text=cmt.idPeopleComment.toString()
        txtPPHourCmt.text=TinhGio.calc(cmt.hourCmt.toString())
        txtPPcontentCmt.text=cmt.cmt.toString()

        return view
    }

    override fun getItem(position: Int): Any {
        return listData.get(position)
    }

    override fun getItemId(position: Int): Long {
        return position.toLong()
    }

    override fun getCount(): Int {
        return listData.size
    }
}