package com.example.internhbaoquoc.testmvp_kotlin.presenter

import android.util.Log
import android.view.View
import com.example.internhbaoquoc.testmvp_kotlin.Utils
import com.example.internhbaoquoc.testmvp_kotlin.model.InitDataSinhVien
import com.example.internhbaoquoc.testmvp_kotlin.model.ListernerSinhVien
import com.example.internhbaoquoc.testmvp_kotlin.model.SinhVien
import com.example.internhbaoquoc.testmvp_kotlin.view.MainView

class MainPresenter : ListernerSinhVien {
    private var data : InitDataSinhVien
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




    fun loadData(succes_fail : Int)
    {
        data.initListData(succes_fail)
        Log.e(Utils.TAG ,"Presenter : $succes_fail" )

    }

    override fun onSucces(lists: ArrayList<SinhVien>) {
        mainView.hienThiThanhCong(lists)
    }

    override fun onFail() {
        mainView.hienThiThatBai()
    }
}