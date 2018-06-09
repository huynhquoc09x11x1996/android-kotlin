package com.example.internhbaoquoc.firebase_kotlin.activity

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.internhbaoquoc.firebase_kotlin.R
import com.example.internhbaoquoc.firebase_kotlin.utils.MyConstants
import com.google.firebase.firestore.QuerySnapshot
import com.google.android.gms.tasks.OnCompleteListener
import com.google.firebase.firestore.FirebaseFirestore


class MainActivity : AppCompatActivity() {
    private lateinit var sharef: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        khoiTao()
        val db =FirebaseFirestore.getInstance()
        db.collection("StatusPuclish")
                .get()
                .addOnCompleteListener(OnCompleteListener<QuerySnapshot> { task ->
                    if (task.isSuccessful) {
                        for (document in task.result) {
                            Log.e(MyConstants.TAG, document.id + " => " + document.data)
                        }
                    } else {
                        Log.e(MyConstants.TAG, "Error getting documents.", task.exception)
                    }
                })

    }


    private fun khoiTao()
    {
        sharef = getSharedPreferences(MyConstants.SHAREREF_NAME, Context.MODE_PRIVATE)
        Log.e(MyConstants.TAG, sharef.getString(MyConstants.INPUT_USER_KEY, null))
    }


}
