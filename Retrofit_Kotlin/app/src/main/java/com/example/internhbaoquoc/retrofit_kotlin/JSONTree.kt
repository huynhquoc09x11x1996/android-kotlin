package com.example.internhbaoquoc.retrofit_kotlin

import com.google.gson.annotations.SerializedName

class JSONTree {
    /*
    * {
    *   "data" :[
    *               {
    *                   ID: valueId
    *                   HoTen:  valueHoten
    *                   NamSinh: valuenamsinh
    *                   DiaChi: valueDiaChi
    *               },
    *               {
    *                   ID: valueId
    *                   HoTen:  valueHoten
    *                   NamSinh: valuenamsinh
    *                   DiaChi: valueDiaChi
    *               },
    *               {
    *                   ID: valueId
    *                   HoTen:  valueHoten
    *                   NamSinh: valuenamsinh
    *                   DiaChi: valueDiaChi
    *               },
    *               {
    *                   ID: valueId
    *                   HoTen:  valueHoten
    *                   NamSinh: valuenamsinh
    *                   DiaChi: valueDiaChi
    *               }
    *           ]
    * }
    *
    * */
    @SerializedName("data")
    var datas: ArrayList<People> = ArrayList()


    class People {
        @SerializedName("ID")
        lateinit var id: String
        @SerializedName("HoTen")
        lateinit var HoTen: String
        @SerializedName("NamSinh")
        lateinit var NamSinh: String
        @SerializedName("DiaChi")
        lateinit var DiaChi: String

        override fun toString(): String {
            return id + " - " + HoTen + " - " + NamSinh + " - " + DiaChi + "\n"
        }
    }
}

