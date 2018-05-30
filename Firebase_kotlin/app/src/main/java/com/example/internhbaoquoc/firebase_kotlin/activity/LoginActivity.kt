package com.example.internhbaoquoc.firebase_kotlin.activity

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
import com.google.firebase.storage.FirebaseStorage
import android.app.Activity
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.widget.ImageView
import com.example.internhbaoquoc.firebase_kotlin.utils.Upload
import com.google.firebase.firestore.DocumentReference
import com.google.firebase.firestore.FirebaseFirestore
import java.io.ByteArrayOutputStream
import kotlin.collections.HashMap

class LoginActivity : AppCompatActivity() {
    private val REQ = 999
    private var i: Int = 0
    private lateinit var sharef: SharedPreferences
    private lateinit var editor: SharedPreferences.Editor
    private lateinit var mIntent: Intent

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        init()
        events()
    }

    private fun init() {
        val db = FirebaseFirestore.getInstance()
        //share preference va intent
        mIntent = Intent(this, MainActivity::class.java)
        sharef = getSharedPreferences(MyConstants.SHAREREF_NAME, Context.MODE_PRIVATE)
        editor = sharef.edit()

        //if sharef !=null bỏ qua activity login và chỉ load dât firebase
        if (sharef.all.size > 0) {
            progressbarLogin.visibility = View.VISIBLE
            lnLogin.visibility = View.INVISIBLE
            db.collection("Users")
                    .document(sharef.getString(MyConstants.INPUT_USER_KEY, null))
                    .get()
                    .addOnSuccessListener { snap ->
                        Log.e(MyConstants.TAG, "Load users thanh cong\n" + snap.id + " - " + snap.data)
                        startActivity(mIntent)
                        finish()
                    }
                    .addOnFailureListener { exception ->
                        Log.e(MyConstants.TAG, "Load users that bai do ${exception.printStackTrace()}")
                    }

        }

        /*
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
        */
    }


    private fun events() {

        btnTruyCap.setOnClickListener {
            progressbarLogin.visibility = View.VISIBLE
            editor.putString(MyConstants.INPUT_USER_KEY, edtInputUserName.text.toString()).commit()
            uploadToFirestorage(mIntent, imgLogin, edtInputUserName.text.toString() + "/" + edtInputUserName.text.toString())
        }
        imgLogin.setOnClickListener {
            val gallery = Intent(Intent.ACTION_GET_CONTENT)
            gallery.type = "image/*"
            startActivityForResult(gallery, REQ)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == REQ && resultCode == Activity.RESULT_OK) {
            //display hinh anh den imageview,upload đến firebase và lấy ảnh của imageview
            val imageUri = data!!.getData()
            imgLogin.setDrawingCacheEnabled(true);
            imgLogin.buildDrawingCache();
            imgLogin.setImageURI(imageUri)
        }
    }

    /*
    *
    * fun tiến hành lấy image chuyên sang byte[] và dùng uploadTask của firebase đẩy lên FireStorage
    * */
    fun uploadToFirestorage(it: Intent, img: ImageView, name: String) {
        //chuyển bitmap của view sang byte[]
        val bitmap = (img.getDrawable() as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()

        //khai báo tham chiếu
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.getReference().child(name + ".jpg")
        val uploadTask = storageRef.putBytes(data)

        //data cần gởi
        var people = HashMap<String, Any>()


        //gửi request,uploadTask success và lấy urldownload thành công thì tiến hành upload object
        uploadTask.addOnSuccessListener {
            Log.e(MyConstants.TAG, "Uploaded lên fireStogare thành công")
            storageRef.downloadUrl.addOnSuccessListener { uri ->
                Log.e(MyConstants.TAG, "Url " + uri.toString())
                people.put("idPeople", edtInputUserName.text.toString())
                people.put("username", edtInputUserName.text.toString())
                people.put("linkImg", uri.toString())
                Upload.beginUpload("Users", edtInputUserName.text.toString(), people, ln)
            }
        }.addOnFailureListener { exception ->
            Log.e(MyConstants.TAG, "Uploaded lên fireStogare thất bại! ${exception.printStackTrace()}")
        }

        //delay cho upload firebase, ko ảnh hưởng
        val timer = Timer()
        var i = 0
        var timertask = timerTask {
            if (i == 3) {
                timer.cancel()
                startActivity(it)
                finish()
            }
            i++
        }
        timer.schedule(timertask, 0, 1000)
    }
}
