package com.example.internhbaoquoc.firebase_kotlin

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log

class MainActivity : AppCompatActivity() {
    private lateinit var sharef: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        sharef=getSharedPreferences(MyConstants.SHAREREF_NAME,Context.MODE_PRIVATE)
        Log.e(MyConstants.TAG,sharef.getString(MyConstants.INPUT_USER_KEY,null))
    }
}
