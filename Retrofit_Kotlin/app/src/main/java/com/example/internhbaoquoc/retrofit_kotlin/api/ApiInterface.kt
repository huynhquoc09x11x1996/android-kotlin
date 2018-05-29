package com.example.internhbaoquoc.retrofit_kotlin.api

import com.example.internhbaoquoc.retrofit_kotlin.model.JSONTree
import com.example.internhbaoquoc.retrofit_kotlin.utils.Utils
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.*
import com.google.gson.GsonBuilder
import okhttp3.RequestBody
import okhttp3.ResponseBody
import retrofit2.http.POST






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


    @POST("uploadMultiImage")
    fun uploadMultiFile(@Body file: RequestBody): Call<ResponseBody>

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