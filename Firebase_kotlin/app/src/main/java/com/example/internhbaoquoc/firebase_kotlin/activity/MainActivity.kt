package com.example.internhbaoquoc.firebase_kotlin.activity

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.BottomNavigationView
import android.support.v7.widget.LinearLayoutManager
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.Toast
import com.example.internhbaoquoc.firebase_kotlin.R
import com.example.internhbaoquoc.firebase_kotlin.adapter.AdapterStatus
import com.example.internhbaoquoc.firebase_kotlin.model.StatusDataModel
import com.example.internhbaoquoc.firebase_kotlin.utils.MyConstants
import com.google.firebase.firestore.FirebaseFirestore
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {

    private lateinit var sharef: SharedPreferences
    private lateinit var lists: ArrayList<StatusDataModel>
    private lateinit var lists2: ArrayList<StatusDataModel>
    private lateinit var linkAVT: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener)
        lists = ArrayList()
        lists2 = ArrayList()
    }


    override fun onResume() {
        super.onResume()
        lists.clear()
        khoiTao(lists)
    }

    private val mOnNavigationItemSelectedListener = BottomNavigationView.OnNavigationItemSelectedListener { item ->
        when (item.itemId) {
            R.id.navigation_home -> {
                Toast.makeText(this, "home", Toast.LENGTH_SHORT).show()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_dashboard -> {
                Toast.makeText(this, "dashboard", Toast.LENGTH_SHORT).show()
                return@OnNavigationItemSelectedListener true
            }
            R.id.navigation_notifications -> {
                Toast.makeText(this, "notifications ", Toast.LENGTH_SHORT).show()
                return@OnNavigationItemSelectedListener true
            }
        }
        false
    }

    private fun khoiTao(listx: ArrayList<StatusDataModel>) {
        //load username từ SharedPreference
        sharef = getSharedPreferences(MyConstants.SHAREREF_NAME, Context.MODE_PRIVATE)
        Log.e(MyConstants.TAG, "MainActivity : " + sharef.getString(MyConstants.INPUT_USER_KEY, null))

        val db = FirebaseFirestore.getInstance()


        db.collection("Users").document(sharef.getString(MyConstants.INPUT_USER_KEY, null))
                .get()
                .addOnSuccessListener {
                    linkAVT = it.data?.get("linkImg").toString()
                }
                .addOnFailureListener {
                    Log.e(MyConstants.TAG, "Load link AVT that bai!")
                }

        db.collection("StatusPuclish")
                .get()
                .addOnSuccessListener { querySnapshot ->
                    //querySnapshot chứa toàn bộ status
                    //duyệt các status(các doccument)
                    querySnapshot.documents.forEach {
                        Log.e(MyConstants.TAG, "\n \nstatus :${it.id} \n data: ${it.data}")
                        listx.add(
                                StatusDataModel(it.data!!.get("avata") as String,
                                        it.data!!.get("contentImage") as String,
                                        it.data!!.get("contentText") as String,
                                        it.data!!.get("hour") as String,
                                        it.data!!.get("id") as String,
                                        it.data!!.get("name") as String,
                                        it.data!!.get("numCmt") as Long,
                                        it.data!!.get("numLike") as Long)
                        )
                    }
                    //tắt progressbar chờ load firebase
                    pbWaitLoadFirebase.visibility = View.INVISIBLE
                    //onSuccess thì update UI recycleView
                    recyclerView.layoutManager = LinearLayoutManager(this)
                    var adapter2 = AdapterStatus(applicationContext, listx)
                    recyclerView.adapter = adapter2
                    recyclerView.adapter.notifyDataSetChanged()
                }
                .addOnFailureListener {
                    Log.e(MyConstants.TAG, "StatusPuclish ${it.printStackTrace()}")
                }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_bar, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_post_stt -> {
                val it = Intent(this, PostSttActivity::class.java)
                it.putExtra("linkAVT", linkAVT)
                Log.e(MyConstants.TAG, "link avata send qua post : $linkAVT")
                startActivity(it)
            }
        }
        return super.onOptionsItemSelected(item)
    }
}
//StatusDataModel(it.data!!.get("avarta"),it.data!!.get("contentImage"),it.data!!.get("contentText"),it.data!!.get("hour"),it.data!!.get("idStt"),it.data!!.get("listCmt"))
