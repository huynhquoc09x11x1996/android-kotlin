package com.example.quocb14005xx.listview_advance_kotlin

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    var listStudent : ArrayList<Student> = ArrayList()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init()
        lv.setOnItemClickListener { parent, view, position, id ->
            Toast.makeText(this, "Click on item " + listStudent.get(position), Toast.LENGTH_SHORT).show()
            val intent : Intent =Intent(this,Main2Activity::class.java)
            intent.putExtra("item",listStudent.get(position))
            startActivity(intent)
        }
        
    }
    fun init()
    {
        listStudent.add(Student(R.drawable.airconditioner_false,"quoc","An giang"))
        listStudent.add(Student(R.drawable.camera,"tri","can tho"))
        listStudent.add(Student(R.drawable.airconditioner_true,"hau","soc trang"))
        lv.adapter=AdapterStudent(this,listStudent)
    }
}
