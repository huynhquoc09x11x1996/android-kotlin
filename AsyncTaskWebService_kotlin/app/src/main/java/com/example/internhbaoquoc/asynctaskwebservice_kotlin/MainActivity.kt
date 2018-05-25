package com.example.internhbaoquoc.asynctaskwebservice_kotlin

import android.os.AsyncTask
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {
    private lateinit var datas:ArrayList<People>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        datas= ArrayList()
        GetData().execute("https://abatable-sisters.000webhostapp.com/get_data.php")


        datas.forEach { Log.e("HuynhBaoQuoc",it.toString()) }
    }

    inner class GetData : AsyncTask<String, Void, String>() {
        override fun doInBackground(vararg params: String?): String {
            var jsonContent: StringBuilder = StringBuilder()
            val url: URL = URL(params[0])
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val inputStream: InputStream = urlConnection.inputStream
            val inputStreamReader: InputStreamReader = InputStreamReader(inputStream)
            val bufferedReader: BufferedReader = BufferedReader(inputStreamReader)
            var line: String = ""

            try {
                do {
                    line = bufferedReader.readLine()
                    if (line!=null)
                    {
                        jsonContent.append(line)
                    }
                } while (line != null)
            }catch (e:Exception)
            {
                e.printStackTrace()
            }
            return jsonContent.toString()
        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            var jsonArray : JSONArray = JSONArray(result)
            Log.e("HuynhBaoQuoc","\n"+jsonArray.get(0))
//            for (i in 0 until jsonArray.length())
//            {
//                var jo: JSONObject = jsonArray.getJSONObject(i)
//                datas.add(
//                        People(jo.getString("ID"),jo.getString("HoTen"),jo.getString("NamSinh"),jo.getString("DiaChi"))
//                )
//            }

        }
    }
}
