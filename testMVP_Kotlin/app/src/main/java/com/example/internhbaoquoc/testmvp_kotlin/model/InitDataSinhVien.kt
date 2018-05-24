package com.example.internhbaoquoc.testmvp_kotlin.model

class InitDataSinhVien {
    private lateinit var listener : ListernerSinhVien
    fun setListenerModel(listener : ListernerSinhVien)
    {
        this.listener=listener
    }

    fun initListData()
    {
        var listss : ArrayList<SinhVien> = ArrayList()
        for(i in 1..20)
        {
            listss.add(SinhVien("Quoc $i",i))
        }
        this.listener.onSucces(listss)
    }
}