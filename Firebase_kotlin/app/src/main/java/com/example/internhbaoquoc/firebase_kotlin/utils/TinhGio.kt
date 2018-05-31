package com.example.internhbaoquoc.firebase_kotlin.utils

import java.text.SimpleDateFormat
import java.util.*

object TinhGio {
    fun calc(preHour: String):String {
        val format = SimpleDateFormat("dd MM yyyy HH mm ss")
        //pre
        val preTime = preHour.toLong()
        val preDate = Date(preTime)
        val pre_output =format.format(preDate).split(" ")
        //current
        val currentTme = System.currentTimeMillis()
        val currentDate = Date(currentTme)
        val current_output = format.format(currentDate).split(" ")

        val p : ArrayList<Int> = arrayListOf(pre_output[0].toInt(),pre_output[1].toInt(),pre_output[2].toInt(),pre_output[3].toInt(),pre_output[4].toInt(),pre_output[5].toInt())
        val c : ArrayList<Int> = arrayListOf(current_output[0].toInt(),current_output[1].toInt(),current_output[2].toInt(),current_output[3].toInt(),current_output[4].toInt(),current_output[5].toInt())

        if (c[5]<p[5])
        {
            //nếu giây hiện tại nhỏ hơn giây lúc post thì xét tới phút
            if (c[4]<p[4])
            {
                //nếu phút hiện tại nhỏ hơn phút lúc post thì xét tới giờ
                if(c[3]<p[3])
                {
                    //nếu gio hiện tại nhỏ hơn gio lúc post thì xét tới ngay
                }
                else
                {
                    val h=c[3]-p[3]
                    return "$h giờ cách đây"
                }
            }
            else
            {
                val m:Int=c[4]-p[4]
                return ("$m phút cách đây")
            }
        }
        else
        {
            val s:Int=c[5]-p[5]
            return(" $s giây cách đây")
        }
        return "${pre_output[3]}giờ, ${pre_output[0]}/${pre_output[1]}/${pre_output[2]}"
    }
}