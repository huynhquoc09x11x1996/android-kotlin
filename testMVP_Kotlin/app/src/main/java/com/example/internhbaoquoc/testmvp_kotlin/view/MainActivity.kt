package com.example.internhbaoquoc.testmvp_kotlin.view

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.widget.Toast
import com.example.internhbaoquoc.testmvp_kotlin.R
import com.example.internhbaoquoc.testmvp_kotlin.adpater.AdapterSinhVien
import com.example.internhbaoquoc.testmvp_kotlin.model.SinhVien
import kotlinx.android.synthetic.main.activity_main.*
import com.example.internhbaoquoc.testmvp_kotlin.presenter.MainPresenter



class MainActivity : AppCompatActivity(),MainView {

    private lateinit var mainPresenter : MainPresenter


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        mainPresenter = MainPresenter()
        mainPresenter.setMainView(this)
        btnLoad.setOnClickListener { mainPresenter.loadData() }

    }
    override fun hienThiThanhCong(lists: ArrayList<SinhVien>) {
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter=AdapterSinhVien(this,lists)
    }
    override fun hienThiThatBai() {
        Toast.makeText(this,"Tải dữ liệu không thành công!",Toast.LENGTH_SHORT).show()
    }
}
