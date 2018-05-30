package com.example.internhbaoquoc.firebase_kotlin.utils


import android.support.design.widget.Snackbar
import android.view.View
import com.google.firebase.firestore.FirebaseFirestore
import kotlin.collections.HashMap

object Upload {
    /*
    * @param collection: push dữ liệu vào collection cần thiết,collection = database sql
    * @param doc_i: mỗi collection có nhiều documents, doc_i = table database
    * @param obj : mỗi bảng có nhiều instance dữ liệu (obj)
    * @param ln(View): view cho snackbar dislay toast
    * */
    fun beginUpload(collection: String, doc_i: String, obj: HashMap<String, Any>, ln: View) {
        val db = FirebaseFirestore.getInstance()
        db.collection(collection)
                .document(doc_i)
                .set(obj)
                .addOnSuccessListener {
                    Snackbar.make(ln, "Truy cập thành công", 2)
                }
                .addOnFailureListener {
                    Snackbar.make(ln, "Truy cập thất bại", 2)
                }
    }
}
