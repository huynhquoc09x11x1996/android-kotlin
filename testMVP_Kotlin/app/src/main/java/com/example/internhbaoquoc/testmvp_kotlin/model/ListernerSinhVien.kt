package com.example.internhbaoquoc.testmvp_kotlin.model

interface ListernerSinhVien
{
    fun onSucces(lists:ArrayList<SinhVien>)
    fun onFail()
}