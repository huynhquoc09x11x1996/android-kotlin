package com.example.internhbaoquoc.retrofit_kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val apiService = ApiInterface.create()
        val call = apiService.getdata()

        call.enqueue(object : Callback<JSONTree> {
            override fun onFailure(call: Call<JSONTree>?, t: Throwable?)
            {
                Log.e(Utils.TAG,"Get data that bai \n $t ")
            }

            override fun onResponse(call: Call<JSONTree>?, response: Response<JSONTree>?)
            {
                if (response != null)
                {
                    var listss: ArrayList<JSONTree.People> = response.body()!!.datas
                    listss.forEach { Log.e(Utils.TAG,it.toString())
                        txtShow.append(it.toString())}

                }
            }
        })
    }

}
