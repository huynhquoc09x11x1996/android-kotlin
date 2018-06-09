package com.example.quocb14005xx.listview_advance_kotlin

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView

class AdapterStudent(var context: Context, var listData: ArrayList<Student>) : BaseAdapter() {
    override fun getView(position: Int, convertView: View?, parent: ViewGroup?): View {
        var view: View?
        var infla: LayoutInflater = LayoutInflater.from(context)
        view = infla.inflate(R.layout.item_product, null)

        var img :ImageView = view.findViewById(R.id.imgItemImg) as ImageView
        var txtname :TextView = view.findViewById(R.id.txtItemName) as TextView
        var txtdiachi :TextView = view.findViewById(R.id.txtItemDiaChi) as TextView

        var student_at : Student= getItem(position) as Student

        txtname.setText(student_at.hoten)
        txtdiachi.setText(student_at.diachi)
        img.setImageResource(student_at.hinhanh)
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