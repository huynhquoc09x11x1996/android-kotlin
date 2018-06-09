package com.example.quocb14005xx.asynctask_kotlin

class Student {
    private var name:String =""
    private var addr:String =""
    constructor(n : String,dc : String)
    {
        name=n
        addr=dc
    }
    fun getName(): String
    {
        return name
    }
    fun getAddr(): String
    {
        return addr
    }
}