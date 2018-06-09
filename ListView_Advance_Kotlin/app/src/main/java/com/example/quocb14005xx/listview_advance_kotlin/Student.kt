package com.example.quocb14005xx.listview_advance_kotlin

import java.io.Serializable

data class Student(val hinhanh :Int,val hoten :String,val diachi :String) : Serializable
{
    override fun toString(): String {
        return hoten+" - "+ diachi
    }
}