package com.example.internhbaoquoc.recycleviewkotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.StaggeredGridLayoutManager
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.item_view.*

class MainActivity : AppCompatActivity(){


    val TAG : String = "HuynhBaoQuoc"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var lists : ArrayList<SinhVien> = ArrayList()
        lists.add(SinhVien(R.drawable.ic1,"Quoc 1","An Giang 1"))
        lists.add(SinhVien(R.drawable.ic2,"Quoc 2","An Giang 2"))
        lists.add(SinhVien(R.drawable.ic3,"Quoc 3","An Giang 3"))
        lists.add(SinhVien(R.drawable.ic4,"Quoc 4","An Giang 4"))
        lists.add(SinhVien(R.drawable.ic5,"Quoc 5","An Giang 5"))


        recycleView2.layoutManager = LinearLayoutManager(this)
        var adapter2=AdapterSinhVien(applicationContext,lists)
        recycleView2.adapter = adapter2
    }
}
