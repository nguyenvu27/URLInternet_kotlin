package com.example.url

import android.os.AsyncTask
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Toast
import java.io.BufferedReader
import java.io.InputStream
import java.io.InputStreamReader
import java.lang.Exception
import java.lang.StringBuilder
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        ReadContenURL().execute("https://ap.poly.edu.vn/index.php")
    }

   inner class ReadContenURL : AsyncTask<String, Void, String>(){
        override fun doInBackground(vararg params: String?): String {
            var content : StringBuilder = StringBuilder()
            val url: URL = URL(params[0])
            val urlConnection: HttpURLConnection = url.openConnection() as HttpURLConnection
            val inputStream: InputStream = urlConnection.inputStream
            val inputStreamReader: InputStreamReader = InputStreamReader(inputStream)
            val bufferedReader : BufferedReader = BufferedReader(inputStreamReader)

            var line: String  = ""
            try {
                do {
                    line = bufferedReader.readLine()
                    if (line !=null){
                        content.append(line)
                    }
                }while (line != null)
                bufferedReader.close()
            }catch (e: Exception){
                Log.d("AAA", e.toString())
            }
            return content.toString()

        }

        override fun onPostExecute(result: String?) {
            super.onPostExecute(result)
            Toast.makeText(this@MainActivity, result, Toast.LENGTH_LONG).show()
        }
    }
}
