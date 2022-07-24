package com.example.w1839054_mad_cw_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONArray
import org.json.JSONObject
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class allMovies : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_movies)

        val allMOviesPlainTxtButton = findViewById<EditText>(R.id.all_movies_plainText_id)
        val allMoviesSearchButton = findViewById<Button>(R.id.all_movies_searchButton_id)
        val allMoviesBackButton = findViewById<Button>(R.id.all_movies_backButton_id)

        allMoviesSearchButton.setOnClickListener {
            var result = ""
            var name = (allMOviesPlainTxtButton.text.toString()).trim()

            runBlocking {
                launch {
                    withContext(Dispatchers.IO) {
                        var url_string = "https://www.omdbapi.com/?s=${name}&type=movie&apikey=a122edc0"
                        val stb = StringBuilder("")
                        val url = URL(url_string)
                        val con = url.openConnection() as HttpURLConnection
                        val bf: BufferedReader
                        try {
                            bf = BufferedReader(InputStreamReader(con.inputStream))
                        } catch (e: IOException) {
                            e.printStackTrace()
                            return@withContext
                        }
                        var line = bf.readLine()
                        while (line != null) {
                            stb.append(line)
                            line = bf.readLine()
                        }
                        //Log.i("response",stb.toString())

                        var data: JSONArray = parseJSON(stb.toString())

                        //result = stb.toString()
                        var i = 0
                        while(i < data.length()){
                            var movieName: JSONObject = JSONObject(data[i].toString())
                            Log.i("response", movieName["Title"].toString())
                            result = result + movieName["Title"].toString()+" (${movieName["Year"]}) "+"\n\n"
                            i++
                        }
                        Log.i("output",result)
                    }
                }
            }

            val ALLMOVIESSEARCH = Intent(this, allMoviesOutput::class.java)
            ALLMOVIESSEARCH.putExtra("data",result)
            startActivity(ALLMOVIESSEARCH)
        }
        allMoviesBackButton.setOnClickListener {
            val ALLMOVIESBACK = Intent(this, MainActivity::class.java)
            startActivity(ALLMOVIESBACK)
        }
    }

    fun parseJSON(stb: String): JSONArray {
        val json = JSONObject(stb.toString())
        val dataArray = json["Search"] as JSONArray
        return dataArray
    }
}