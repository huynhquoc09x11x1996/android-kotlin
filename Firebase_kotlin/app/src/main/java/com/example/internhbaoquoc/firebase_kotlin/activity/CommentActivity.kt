package com.example.internhbaoquoc.firebase_kotlin.activity

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import com.example.internhbaoquoc.firebase_kotlin.R
import com.example.internhbaoquoc.firebase_kotlin.utils.MyConstants
import com.example.internhbaoquoc.firebase_kotlin.utils.TinhGio
import com.google.firebase.firestore.FirebaseFirestore
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_comment.*
import java.lang.Exception

class CommentActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        khoiTao()
    }

    private fun khoiTao() {
        val db = FirebaseFirestore.getInstance()

        db.collection(MyConstants.STATUS_PUBLISH)
                .document(intent.getStringExtra("id"))
                .get()
                .addOnSuccessListener {
                    Log.e(MyConstants.TAG, "CommentAct : ${it.data}")
                    Picasso.get().load(it.data!!.get("avata").toString())
                            .into(imgAvartaCmtAct, object : Callback {
                                override fun onSuccess() {
                                    progressbarAVTCmtAct.visibility = View.GONE
                                }

                                override fun onError(e: Exception?) {
                                    imgAvartaCmtAct.setImageResource(R.drawable.no_avarta)
                                }
                            })
                    txtNameCmtAct.text = it.data!!.get("name").toString()
                    txtHourCmtAct.text = TinhGio.calc(it.data!!.get("hour").toString())
                    txtContentTextCmtAct.text = it.data!!.get("contentText").toString()
                    Picasso.get().load(it.data!!.get("contentImage").toString())
                            .into(imgContentImageCmtAct, object : Callback {
                                override fun onSuccess() {
                                    progressbarImageContentCmtAct.visibility = View.GONE
                                }

                                override fun onError(e: Exception?) {
                                    imgContentImageCmtAct.setImageResource(R.drawable.no_image)
                                }

                            })
                    txtNumLikeCmtAct.text=it.data!!.get("numLike").toString()+ "likes"
                    txtNumCmtAct.text=it.data!!.get("numCmt").toString()+ " comments"
                }
                .addOnFailureListener {
                    Log.e(MyConstants.TAG, "CommentAct :${it.printStackTrace()}")
                }
    }
}
