// MOBILE APPLICATION DEVELOPMENT
// Assignment 2
// 5COSC023C

// Module Leader : Torin Wirasingha
// Deadline : 19/4/2022, 13:00

// Name : E. D. D. Fernando
// UoW ID : w1839054
// IIT ID : 20200473

// The application developed will be helping users with testing and acquiring new knowledge regarding movies:

package com.example.w1839054_mad_cw_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import androidx.room.Room
import com.google.firebase.firestore.auth.User
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val addMoviesButton = findViewById<Button>(R.id.add_movies)
        val searchMoviesButton = findViewById<Button>(R.id.search_movies)
        val searchActorsButton = findViewById<Button>(R.id.search_actors)
        val allMoviesButton = findViewById<Button>(R.id.all_movies)

        addMoviesButton.setOnClickListener {
            val ADDMOVIES = Intent(this, addMovie::class.java)
            startActivity(ADDMOVIES)
        }
        searchMoviesButton.setOnClickListener {
            val SEARCHMOVIES = Intent(this, searchMovie::class.java)
            startActivity(SEARCHMOVIES)
        }
        searchActorsButton.setOnClickListener {
            val SEARCHACTORS = Intent(this, searchActor::class.java)
            startActivity(SEARCHACTORS)
        }
        allMoviesButton.setOnClickListener{
            val ALLMOVIES =Intent(this, allMovies::class.java)
            startActivity(ALLMOVIES)
        }
    }
}