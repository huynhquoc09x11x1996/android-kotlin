package com.example.internhbaoquoc.firebase_kotlin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import kotlinx.android.synthetic.main.activity_login.*

class LoginActivity : AppCompatActivity() {
    private lateinit var sharef: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)


        val mIntent = Intent(this, MainActivity::class.java)
        sharef = getSharedPreferences(MyConstants.SHAREREF_NAME, Context.MODE_PRIVATE)
        editor = sharef.edit()
        Log.e(MyConstants.TAG,sharef.all.size.toString())
        if (sharef.all.size>0)
        {
            startActivity(mIntent)
            finish()
        }
        btnTruyCap.setOnClickListener {
            editor.putString(MyConstants.INPUT_USER_KEY, edtInputUserName.text.toString()).commit()
            startActivity(mIntent)
            finish()
        }
    }
}
