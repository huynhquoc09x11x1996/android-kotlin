package com.example.quocb14005xx.listview_advance_kotlin

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val intent2 = intent.getSerializableExtra("item") as Student
        if (intent2!=null)
        {
            txtShowItem.setText(intent2.hoten + " - "+ intent2.diachi)
        }
    }
}
