package com.example.internhbaoquoc.retrofit_kotlin

import com.google.gson.annotations.SerializedName

class PeoplePost {

    @SerializedName("hotenSV")
    lateinit var hotenSV: String
    @SerializedName("namsinhSV")
    lateinit var namsinhSV: String
    @SerializedName("diachiSV")
    lateinit var diachiSV: String

    constructor(toString: String, toString1: String, toString2: String) {
        hotenSV = toString
        namsinhSV = toString1
        diachiSV = toString2
    }

    override fun toString(): String {
        return hotenSV + " - " + namsinhSV + " - " + diachiSV + "\n"
    }


}