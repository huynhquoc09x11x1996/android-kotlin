package com.example.internhbaoquoc.testmvp_kotlin.model

import android.util.Log
import com.example.internhbaoquoc.testmvp_kotlin.Utils

class InitDataSinhVien {
    private lateinit var listener: ListernerSinhVien
    fun setListenerModel(listener: ListernerSinhVien) {
        this.listener = listener
    }


    fun initListData(succ_fail: Int) {
        Log.e(Utils.TAG,"Model initListdata : $succ_fail")

        when (succ_fail) {
            1 -> {
                var listss: ArrayList<SinhVien> = ArrayList()
                for (i in 1..20) {
                    listss.add(SinhVien("Quoc $i", i))
                }
                this.listener.onSucces(listss)
            }
            0 -> this.listener.onFail()
            else -> Log.e(Utils.TAG,"Khong lam gi ca")
        }
    }
}