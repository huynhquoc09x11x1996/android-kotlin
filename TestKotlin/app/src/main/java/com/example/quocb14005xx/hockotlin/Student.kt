package com.example.quocb14005xx.hockotlin

import android.util.Log

class Student{
    private var hoten : String = ""
    private var diachi : String = ""
    private var tuoi : Int = 0

    constructor()
    {
        Log.e("HuynhBaoQuoc","Constructor không đối số được khởi tạo")
    }
    constructor(ht: String,dc:String,t:Int)
    {
        hoten=ht
        diachi=dc
        tuoi=t
    }

    fun setAge(newAge:Int)
    {
        tuoi=newAge
    }
    fun getAge() : Int
    {
        return tuoi
    }

    fun setHoTen(newHoTen:String)
    {
        hoten= newHoTen
    }
    fun getHoTen() : String
    {
        return hoten
    }

    fun setDiaCHi(newAddr:String)
    {
        diachi=newAddr
    }
    fun getAddr() : String
    {
        return diachi
    }

    override fun toString(): String {
        return hoten +" - "+ diachi+" - "+ tuoi
    }
}