package com.example.internhbaoquoc.retrofit_kotlin

import android.app.Activity
import android.app.Dialog
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.dialog_add.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import android.content.Intent
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import com.example.internhbaoquoc.retrofit_kotlin.adapter.AdapterItemImage
import com.example.internhbaoquoc.retrofit_kotlin.api.ApiInterface
import com.example.internhbaoquoc.retrofit_kotlin.model.JSONTree
import com.example.internhbaoquoc.retrofit_kotlin.utils.Utils
import com.nileshp.multiphotopicker.photopicker.activity.PickImageActivity


class MainActivity : AppCompatActivity() {

    private lateinit var listImg: ArrayList<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getDataAndDisplay()

        imgLoad.setOnClickListener { accessImage() }

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
        recycleView.layoutManager = LinearLayoutManager(this, RecyclerView.HORIZONTAL,false)
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)
        if (resultCode != Activity.RESULT_OK) {
            return
        }
        if (resultCode == -1 && requestCode == PickImageActivity.PICKER_REQUEST_CODE) {
            listImg = data!!.extras.getStringArrayList(PickImageActivity.KEY_DATA_RESULT)
            Log.e(Utils.TAG, "Đã chọn ${listImg.size} ảnh!")
            listImg.forEach { Log.e(Utils.TAG, it.toString()) }
            var adapter2 = AdapterItemImage(applicationContext, listImg!!)
            recycleView.adapter = adapter2

        }

    }

    /*
    *
    * */
    private fun send(name: String, age: String, address: String) {
        val apiService = ApiInterface.create()
        val call = apiService.themDoiTuong(name, age, address)
        call.enqueue(object : Callback<String> {
            override fun onFailure(call: Call<String>?, t: Throwable?) {
                Log.e(Utils.TAG, "Lỗi vì : ${t?.printStackTrace()}")
            }

            override fun onResponse(call: Call<String>?, response: Response<String>?) {
                if (response!!.isSuccessful) {
                    Log.e(Utils.TAG, "Insert thanh cong!")
                }
            }
        })
    }

    //intent den thu vien anh
    private fun accessImage() {
        val mIntent = Intent(this, PickImageActivity::class.java)
        mIntent.putExtra(PickImageActivity.KEY_LIMIT_MAX_IMAGE, 60)
        mIntent.putExtra(PickImageActivity.KEY_LIMIT_MIN_IMAGE, 3)
        mIntent.setType("image/*");
        mIntent.setAction(Intent.ACTION_PICK);
        startActivityForResult(mIntent, PickImageActivity.PICKER_REQUEST_CODE)
    }

    //get data tu server va show ra activity
    private fun getDataAndDisplay() {
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
    }
}
