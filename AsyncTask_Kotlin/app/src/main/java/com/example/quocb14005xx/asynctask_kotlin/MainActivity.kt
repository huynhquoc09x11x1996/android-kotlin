package com.example.quocb14005xx.asynctask_kotlin

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.example.quocb14005xx.asynctask_kotlin.R.id.txtShow
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        txtShow.setOnClickListener { XuLyCongViec().execute()}

        var listS : ArrayList<Student> = ArrayList<Student>()
        listS.add(Student("quoc","AG"))
        listS.add(Student("kieudiem","CT"))
        listS.add(Student("vannguyen","CT"))
        listS.forEach{ st-> Log.e("HuynhBaoQuoc",st.getName() + " - " + st.getAddr()) }

    }

    inner class XuLyCongViec : AsyncTask<Void, String, String>()
    {
        override fun onPreExecute() {
            super.onPreExecute()
            txtShow.setText("Bắt đầu...")
        }

        override fun doInBackground(vararg params: Void?): String {
            for (i in 1..10)
            {
                publishProgress("\nXử lý công việc " +i)
            }
            return "\nHoàn thành các công việc"
        }

        override fun onProgressUpdate(vararg values: String?) {
            super.onProgressUpdate(*values)
            txtShow.append(values[0])
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            txtShow.append(result)
        }

    }
}
