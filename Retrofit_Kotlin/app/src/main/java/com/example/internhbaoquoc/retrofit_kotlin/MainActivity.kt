package com.example.internhbaoquoc.retrofit_kotlin

import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.google.gson.JsonObject
import com.google.gson.JsonArray
import com.google.gson.Gson


class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val apiService = ApiInterface.create()
        val call = apiService.getdata()

        call.enqueue(object : Callback<JSONTree> {
            override fun onFailure(call: Call<JSONTree>?, t: Throwable?) {
                Log.e(Utils.TAG, "Get data that bai \n $t ")
            }

            override fun onResponse(call: Call<JSONTree>?, response: Response<JSONTree>?) {
                if (response != null) {
                    var listss: ArrayList<JSONTree.People> = response.body()!!.datas
                    listss.forEach {
                        Log.e(Utils.TAG, it.toString())
                        txtShow.append(it.toString())
                    }

                }
            }
        })

        btnAdd.setOnClickListener {
            var dialog: Dialog = Dialog(this)
            dialog.setTitle("Add")
            dialog.setContentView(R.layout.dialog_add)
            dialog.show()
            dialog.btnOk.setOnClickListener {
                //code insert
                send(dialog.edtnName.text.toString()
                        , dialog.edtAge.text.toString()
                        , dialog.edtAddress.text.toString())
                dialog.cancel()
            }
            dialog.btnCancel.setOnClickListener { dialog.cancel() }
        }
    }

    private fun send(name: String, age: String, address: String) {
        val apiService = ApiInterface.create()
        val call = apiService.themDoiTuong(name, age, address)
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.e(Utils.TAG, "Lỗi vì : ${t?.printStackTrace()}")
            }

            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if (response!!.isSuccessful)
                {
                    Log.e(Utils.TAG, "Insert thanh cong!")
                }
            }
        })
    }
}
