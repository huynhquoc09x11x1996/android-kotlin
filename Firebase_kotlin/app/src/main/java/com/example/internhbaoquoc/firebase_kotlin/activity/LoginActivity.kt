package com.example.internhbaoquoc.firebase_kotlin.activity

import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.internhbaoquoc.firebase_kotlin.R
import com.example.internhbaoquoc.firebase_kotlin.utils.MyConstants
import kotlinx.android.synthetic.main.activity_login.*
import java.util.*
import kotlin.concurrent.timerTask

class LoginActivity : AppCompatActivity() {
    private lateinit var sharef: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        //share preferent va intent
        val mIntent = Intent(this, MainActivity::class.java)
        sharef = getSharedPreferences(MyConstants.SHAREREF_NAME, Context.MODE_PRIVATE)
        editor = sharef.edit()
        Log.e(MyConstants.TAG, sharef.all.size.toString())

        var i: Int = 0
        if (sharef.all.size > 0) {
            progressbarLogin.visibility = View.VISIBLE
            lnLogin.visibility = View.INVISIBLE
            val timer = Timer()
            timer.schedule(timerTask {
                if (i == 5) {
                    timer.cancel()
                    startActivity(mIntent)
                    finish()
                }
                i++
            }, 0, 1000)


        }
        btnTruyCap.setOnClickListener {
            editor.putString(MyConstants.INPUT_USER_KEY, edtInputUserName.text.toString()).commit()
            startActivity(mIntent)
            finish()
        }
    }
}
