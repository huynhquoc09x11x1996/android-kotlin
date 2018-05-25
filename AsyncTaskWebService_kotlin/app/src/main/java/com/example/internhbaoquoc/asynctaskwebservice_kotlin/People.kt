package com.example.internhbaoquoc.asynctaskwebservice_kotlin

data class People(val id: String,val hoten: String,val namsinh:String,val diachi:String)
{
    override fun toString(): String {
        return hoten+" - "+namsinh+" - "+diachi+"\n"
    }
}