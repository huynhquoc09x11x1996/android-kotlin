package com.example.internhbaoquoc.firebase_kotlin.activity

import android.app.Activity
import android.app.ProgressDialog
import android.content.Context
import android.content.Intent
import android.graphics.Bitmap
import android.graphics.drawable.BitmapDrawable
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.Snackbar
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.example.internhbaoquoc.firebase_kotlin.R
import com.example.internhbaoquoc.firebase_kotlin.model.StatusDataModel
import com.example.internhbaoquoc.firebase_kotlin.utils.MyConstants
import com.example.internhbaoquoc.firebase_kotlin.utils.Upload
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.storage.FirebaseStorage
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.activity_post_stt.*
import java.io.ByteArrayOutputStream

class PostSttActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_post_stt)
        events()
    }

    private fun events() {
        imgGrallery.setOnClickListener {
            val gallery = Intent(Intent.ACTION_GET_CONTENT)
            gallery.type = "image/*"
            startActivityForResult(gallery, MyConstants.REQ)
        }
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.menu_poststt, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            R.id.action_submit_post -> {
                startPost()
                finish()
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun startPost() {
        //chuyển bitmap của view sang byte[]
        val bitmap = (imgClone.getDrawable() as BitmapDrawable).bitmap
        val baos = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos)
        val data = baos.toByteArray()
        //khai báo tham chiếu
        val username = getSharedPreferences(MyConstants.SHAREREF_NAME, Context.MODE_PRIVATE).getString(MyConstants.INPUT_USER_KEY, null)
        val storage = FirebaseStorage.getInstance()
        val storageRef = storage.getReference().child(
                username + "/" + System.currentTimeMillis().toString() + ".jpg")
        val uploadTask = storageRef.putBytes(data)

        val dialog = ProgressDialog(this)
        dialog.setTitle("Bắt đầu đăng....")
        dialog.show()
        //gửi request,uploadTask success và lấy urldownload thành công thì tiến hành upload object
        uploadTask.addOnSuccessListener {
            storageRef.downloadUrl.addOnSuccessListener { uri ->
                val obj = StatusDataModel(intent!!.getStringExtra("linkAVT"), uri.toString(), edtInputContentStt.text.toString(), System.currentTimeMillis().toString(), "stt_" + System.currentTimeMillis().toString(), username, 0, 0)
                Snackbar.make(lnPost,"Đăng status xong",5).show()
                Upload.beginUpload("StatusPuclish", "stt_" + System.currentTimeMillis().toString(), obj)
            }
        }.addOnFailureListener { exception ->
            Log.e(MyConstants.TAG, "Uploaded lên fireStogare thất bại! ${exception.printStackTrace()}")
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        if (requestCode == MyConstants.REQ && resultCode == Activity.RESULT_OK) {
            //display hinh anh den imageview,upload đến firebase và lấy ảnh của imageview
            val imageUri = data!!.getData()
            imgClone.setDrawingCacheEnabled(true);
            imgClone.buildDrawingCache();
            imgClone.setImageURI(imageUri)
        }
    }
}
