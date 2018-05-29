package com.example.internhbaoquoc.retrofit_kotlin

import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import com.google.gson.GsonBuilder
import com.google.gson.Gson




interface ApiInterface {

    /*
    * các Call<T> là echo response từ php
    *
    * getdata return Call<JSONTree> là do sẽ trả về các object item
    *
    * themDoiTUong return :Call<String> là do php echo success hay fail
    *
    * */
    @GET("get_data.php")
    fun getdata(): Call<JSONTree>

    @POST("insert.php")
    @FormUrlEncoded
    fun themDoiTuong(@Field("hotenSV") name : String
                     ,@Field("namsinhSV") ns: String
                     ,@Field("diachiSV") addr: String) : Call<String>

    companion object {
        fun create(): ApiInterface {
            val gson = GsonBuilder().setLenient().create()
            val retrofit = Retrofit.Builder()
                    .baseUrl(Utils.BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create(gson))
                    .build()
            return retrofit.create(ApiInterface::class.java)
        }
    }
}