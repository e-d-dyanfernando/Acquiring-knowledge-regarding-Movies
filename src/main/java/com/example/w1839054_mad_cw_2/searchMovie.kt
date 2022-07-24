package com.example.w1839054_mad_cw_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.HttpURLConnection
import java.net.URL

class searchMovie : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie)

        val searchMovieSearchButton = findViewById<Button>(R.id.movie_search_id)
        val searchMovieBackButton = findViewById<Button>(R.id.movieSearch_back_id)

        val inputMovieName = findViewById<EditText>(R.id.actor_search_plainText_id)

        searchMovieSearchButton.setOnClickListener {

            var Output = ""
            var Input = (inputMovieName.text).toString()

            runBlocking {
                launch {
                    withContext(Dispatchers.IO) {

                        val url_string = "https://www.omdbapi.com/?t="+Input+"&apikey=a122edc0";
                        val url = URL(url_string)
                        val connection: HttpURLConnection = url.openConnection() as HttpURLConnection
                        //Log.i ("t",connection.responseCode.toString())
                        BufferedReader(InputStreamReader(connection.getInputStream())).use { inp ->
                            var line: String?
                            while (inp.readLine().also { line = it } != null) {
                                Output = Output + line
                            }
                        }
                    }
                }

            }
            val SEARCH_MOVIE_SEARCH_BTN = Intent(this, searchMovieOutput::class.java)
            SEARCH_MOVIE_SEARCH_BTN.putExtra("data",Output)
            startActivity(SEARCH_MOVIE_SEARCH_BTN)
        }

        searchMovieBackButton.setOnClickListener {
            val SEARCH_MOVIE_BACK_BTN = Intent(this, MainActivity::class.java)
            startActivity(SEARCH_MOVIE_BACK_BTN)
        }

    }
}