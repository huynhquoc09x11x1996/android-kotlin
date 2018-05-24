package com.example.internhbaoquoc.testmvp_kotlin.presenter

import android.view.View
import com.example.internhbaoquoc.testmvp_kotlin.model.InitDataSinhVien
import com.example.internhbaoquoc.testmvp_kotlin.model.ListernerSinhVien
import com.example.internhbaoquoc.testmvp_kotlin.model.SinhVien
import com.example.internhbaoquoc.testmvp_kotlin.view.MainView

class MainPresenter : ListernerSinhVien {
    private lateinit var data : InitDataSinhVien
    private lateinit var mainView : MainView

    fun setMainView(mainView: MainView)
    {
        this.mainView=mainView
    }

    constructor()
    {
        data = InitDataSinhVien()
        data.setListenerModel(this)
    }

    fun loadData()
    {
        data.initListData()
    }

    override fun onSucces(lists: ArrayList<SinhVien>) {
        mainView.hienThiThanhCong(lists)
    }

    override fun onFail() {
        mainView.hienThiThatBai()
    }
}