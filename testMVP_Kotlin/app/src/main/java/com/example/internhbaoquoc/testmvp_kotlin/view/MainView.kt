package com.example.internhbaoquoc.testmvp_kotlin.view

import com.example.internhbaoquoc.testmvp_kotlin.model.SinhVien

interface MainView {
    fun hienThiThanhCong(list: ArrayList<SinhVien>)
    fun hienThiThatBai()
}