package com.example.quocb14005xx.hockotlin

data class StudentData(val HoTen:String, val Tuoi:Int)
{
    override fun toString(): String {
        return HoTen + " - "+Tuoi
    }
}