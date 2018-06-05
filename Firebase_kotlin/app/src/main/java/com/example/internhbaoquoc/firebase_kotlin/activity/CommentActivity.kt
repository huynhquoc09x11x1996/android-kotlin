package com.example.internhbaoquoc.firebase_kotlin.activity

import android.content.Context
import android.content.SharedPreferences
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.text.TextUtils
import android.util.Log
import android.view.View
import android.widget.Toast
import com.example.internhbaoquoc.firebase_kotlin.R
import com.example.internhbaoquoc.firebase_kotlin.model.PeopleComment
import com.example.internhbaoquoc.firebase_kotlin.utils.MyConstants
import com.example.internhbaoquoc.firebase_kotlin.utils.TinhGio
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.SetOptions
import com.squareup.picasso.Callback
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.activity_comment.*
import java.lang.Exception
import java.util.ArrayList


class CommentActivity : AppCompatActivity() {
    private lateinit var sharef: SharedPreferences
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_comment)
        sharef = getSharedPreferences(MyConstants.SHAREREF_NAME, Context.MODE_PRIVATE)
        khoiTao()
        events()
    }

    private fun events() {
        val text_Cmt = edtInputComment.text.toString()
        btnSendComment.setOnClickListener {
            if (TextUtils.equals(text_Cmt, " ")) {
                Toast.makeText(this, "Nhap noi dung comment", Toast.LENGTH_SHORT).show()
            } else {
                val db = FirebaseFirestore.getInstance()
                var cmt = HashMap<String, Any>()
                val timeStr = System.currentTimeMillis().toString()
                cmt.put("idPeopleCmt", sharef.getString(MyConstants.INPUT_USER_KEY, null))
                cmt.put("idStt", intent!!.getStringExtra("id"))
                cmt.put("cmt", edtInputComment.text.toString())
                cmt.put("hourCmt", timeStr)
                var cmtPost = HashMap<String, Any>()
                cmtPost.put("cmt_" + timeStr, cmt)

                db.collection(MyConstants.LIST_COMMENT)
                        .document(intent!!.getStringExtra("id"))
                        .set(cmtPost, SetOptions.merge())
                        .addOnSuccessListener {
                            edtInputComment.setText("")
                            Log.e(MyConstants.TAG, "Comment thanh cong")
                        }
                        .addOnFailureListener {
                            Log.e(MyConstants.TAG, "Comment that bai")
                        }
            }
        }
    }

    private fun khoiTao() {
        val db = FirebaseFirestore.getInstance()
        var listCmt:ArrayList<PeopleComment> =ArrayList()


        //stt
        db.collection(MyConstants.STATUS_PUBLISH)
                .document(intent!!.getStringExtra("id"))
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
                    txtNumLikeCmtAct.text = it.data!!.get("numLike").toString() + "likes"
                    txtNumCmtAct.text = it.data!!.get("numCmt").toString() + " comments"
                }
                .addOnFailureListener {
                    Log.e(MyConstants.TAG, "CommentAct :${it.printStackTrace()}")
                }
        //cmt
        val docRef = db.collection(MyConstants.LIST_COMMENT).document(intent!!.getStringExtra("id"))
        docRef.get()
                .addOnSuccessListener {

                }
                .addOnFailureListener {
                    Log.e(MyConstants.TAG,"Các comment của stt ${intent!!.getStringExtra("id")} Error")
                }
    }
}
