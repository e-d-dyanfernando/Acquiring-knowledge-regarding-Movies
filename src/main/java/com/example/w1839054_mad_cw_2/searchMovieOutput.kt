package com.example.w1839054_mad_cw_2

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import org.json.JSONObject

class searchMovieOutput : AppCompatActivity() {

    var value=""
    var movieObject:Movies? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_movie_output)

        val movieOutputBackButton = findViewById<Button>(R.id.movie_output_back_btn)
        val movie_addDB_id = findViewById<Button>(R.id.movie_addDB_id)

        val movieOutput = findViewById<TextView>(R.id.movie_output_textView_id)
        movieOutput.movementMethod = ScrollingMovementMethod()

        movie_addDB_id.setOnClickListener{
            Log.i("TAG", movieObject!!.Title)

            runBlocking {
                launch {
                    withContext(Dispatchers.IO){

                        val db = Room.databaseBuilder(
                            applicationContext,
                            moviesDatabase::class.java, "_Movies-Database_"
                        ).build()

                        try {
                            val MOVIES_DAO = db.moviesDAO()
                            MOVIES_DAO.insertAll(movieObject!!)
                            val Movies_Arrray: List<Movies> = MOVIES_DAO.getAll()

                            for (m in Movies_Arrray) {
                                Log.i("t", m.Title)
                            }
                            runOnUiThread(){
                                Toast.makeText(applicationContext,"${movieObject!!.Title} added", Toast.LENGTH_SHORT).show()
                            }

                        }catch (e: SQLiteConstraintException){
                            runOnUiThread(){
                                Toast.makeText(applicationContext,"${movieObject!!.Title} already added", Toast.LENGTH_SHORT).show()
                            }
                        }

                    }
                }
            }
        }

        movieOutputBackButton.setOnClickListener {
            val MOVIE_OUTPUT_BACK_BTN = Intent(this, searchMovie::class.java)
            startActivity(MOVIE_OUTPUT_BACK_BTN)
        }

        var intent: Intent = getIntent()
        var data: String? = intent.getStringExtra("data")
        //Log.i ("display",data.toString())
        var movie = createMovieObject(data.toString())

        movieObject = movie

        value = "${movie.Title}\n\n${movie.Year}\n\n${movie.Rated}\n\n${movie.Released}\n\n${movie.Runtime}\n\n${movie.Genre}\n\n${movie.Director}\n\n${movie.Writer}\n\n${movie.Actors}\n\n${movie.Plot}"
        movieOutput.text = value
    }

    fun createMovieObject(stb: String): Movies {
        val json = JSONObject(stb.toString())
        // extract the title
        val title = json["Title"] as String
        val year = json["Year"] as String
        val rated = json["Rated"] as String
        val released = json["Released"] as String
        val runtime = json["Runtime"] as String
        val genre = json["Genre"] as String
        val director = json["Director"] as String
        val writer = json["Writer"] as String
        val actors = json["Actors"] as String
        val plot = json["Plot"] as String

        var movieObject = Movies(title,year,rated,released,runtime,genre,director,writer,actors,plot)
        return movieObject
    }

    /*
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("movie", value)
    }
     */
}