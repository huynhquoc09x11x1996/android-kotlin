package com.example.internhbaoquoc.firebase_kotlin.utils


import android.support.design.widget.Snackbar
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.HashMap
import android.support.annotation.NonNull
import android.util.Log
import com.example.internhbaoquoc.firebase_kotlin.adapter.AdapterStatus
import com.example.internhbaoquoc.firebase_kotlin.model.StatusDataModel
import com.google.android.gms.tasks.OnFailureListener
import com.google.android.gms.tasks.OnSuccessListener


object Upload {
    /*
    * @param collection: push dữ liệu vào collection cần thiết,collection = database sql
    * @param doc_i: mỗi collection có nhiều documents, doc_i = table database
    * @param obj : mỗi bảng có nhiều instance dữ liệu (obj)
    * @param ln(View): view cho snackbar dislay toast
    * */


    fun beginUpload(collection: String, doc_i: String, obj: Any) {
        val db = FirebaseFirestore.getInstance()
        db.collection(collection)
                .document(doc_i)
                .set(obj)
                .addOnSuccessListener {
                    Log.e(MyConstants.TAG, "upload success")

                }
                .addOnFailureListener {
                    Log.e(MyConstants.TAG, "upload fail")
                }
    }

    fun increaseLike(collection: String, doc_i: String, obj: StatusDataModel) {
        val db = FirebaseFirestore.getInstance()
        db.collection(collection)
                .document(doc_i)
                .set(obj)
                .addOnSuccessListener {
                    Log.e(MyConstants.TAG, "upload success")

                }
                .addOnFailureListener {
                    Log.e(MyConstants.TAG, "upload fail")
                }
    }

    fun deleteStatus(collection: String, doc_i: String) {
        val db = FirebaseFirestore.getInstance()
        db.collection(collection).document(doc_i)
                .delete()
                .addOnSuccessListener {
                    Log.e(MyConstants.TAG, "deleted!")

                }
                .addOnFailureListener {
                    Log.e(MyConstants.TAG, "Error deleting document")
                }
    }
}
