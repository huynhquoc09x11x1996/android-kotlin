package com.example.quocb14005xx.hockotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val TAG : String= "HuynhBaoQuoc"

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        //khai bao bien
//        var a :String ="HuynhQuoc"
//        a="HuynhBaoQuoc"
//        a.toInt()
//        val b : String ="KieuDiem"
//        b="kieuDiem"

        //null safety
//        var a : String = "Quoc"
//        a=null
//        //co the null
//        var b :String? ="Quoc"
//        b=null
//        //khong duoc null
//        var c : String =null!!
//        c="QuocQuoc"


        //if...else
//        var a :Int=55
//        var b :Int=15
//
//        if (a>b)
//        {
//            Log.e(TAG,"MAX = " + a)
//        }
//        else
//        {
//            Log.e(TAG,"MAX = " + b)
//        }

        //when
//        var A: Int =4;
//        Log.e(TAG,"\nWhen ví dụ 1 với case nào thì làm việc gì?")
//        when(A)
//        {
//            2-> Log.e(TAG,"Number Two")
//            3->Log.e(TAG,"Number Three")
//            4->Log.e(TAG,"Number Four")
//            5->Log.e(TAG,"Number Five")
//
//        }
//        Log.e(TAG,"When ví dụ 2, case trong khoảng giá trị")
//        var B : Int = 5
//        when(B)
//        {
//            in 0..9 ->Log.e(TAG,"Số 0~9")
//            in 10..100 ->Log.e(TAG,"Số 10~100")
//        }
    //while

//        var a : Int =0
//        while (a<=5)
//        {
//            Log.e(TAG,"WHILE "  + a)
//            a++
//        }



        //FOR
        //cách này thì sẽ làm 10 lần từ lần 1 tới lần thứ 10,muốn chạy từ 0..10 mà chỉ là 0->9 thì sa0
//        for(quoc in 1..10)
//        {
//            Log.e(TAG,"\nFOR " +quoc)
//        }

        //fuction
        show()
        show2("QUOC")
        Log.e(TAG,funReturnString())
        Log.e(TAG,funReturnDouble().toString())
        //Class & OOP
        //listview

        var listData : ArrayList<String> = ArrayList()
        listData.add("Android")
        listData.add("IOS")
        listData.add("Windows Phone")
        listData.add("C# & .NET")
        lv.adapter=ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listData)


//        var sv1: Student = Student()
//        sv1.setDiaCHi("An Giang")
//        sv1.setHoTen("Huynh Bao Quoc")
//        sv1.setAge(22)
        var sv1: Student = Student("HuynhQuoc","An Giang",1996)
        Log.e(TAG,"Sinh vien 1 :"+sv1.toString())

        var dataSt1 : StudentData = StudentData("Tran Huu Tri",1996)
        Log.e(TAG,"Data class "+dataSt1.toString())
        txtShowName.setText("Tôi là Huỳnh Quốc")
        txtShowName.setOnClickListener {
            showTextButton(txtShowName.text.toString())
            lv.adapter=ArrayAdapter<String>(this,R.layout.support_simple_spinner_dropdown_item,listData)
        }
        btnClick.setOnClickListener {
            showTextButton("Hello")
            listData.add("Python")
            lv.invalidateViews()
        }
        lv.setOnItemClickListener { parent, view, position, id -> Toast.makeText(this,listData.get(position),Toast.LENGTH_SHORT).show()  }


    }
    fun showTextButton(text: String)
    {
        Toast.makeText(this,text,Toast.LENGTH_SHORT).show()
    }
    fun funReturnString() : String
    {
        return "Quoc"
    }
    fun funReturnDouble() : Double
    {
        return 1996.0
    }
    fun show()
    {
        Log.e(TAG,"Hello")
    }
    fun show2(name : String)
    {
        Log.e(TAG,"Hello" +name)
    }
}
