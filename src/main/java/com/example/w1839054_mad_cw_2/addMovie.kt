package com.example.w1839054_mad_cw_2

import android.content.Intent
import android.database.sqlite.SQLiteConstraintException
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.Toast
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext
import java.io.BufferedReader
import java.io.InputStreamReader
import java.net.URL

class addMovie : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_movie)

        val addDBBackButton = findViewById<Button>(R.id.addDB_back_btn_id)

        addDBBackButton.setOnClickListener {
            val ADDDB_BACK_BTN = Intent(this, MainActivity::class.java)
            startActivity(ADDDB_BACK_BTN)
        }

        runBlocking {
            launch {

                withContext(Dispatchers.IO){

                    val db = Room.databaseBuilder(
                        applicationContext,
                        moviesDatabase::class.java, "_Movies-Database_"
                    ).build()

                    try {
                        val MOVIES_DAO = db.moviesDAO()

                        val movie1 = Movies(
                            "The Shawshank Redemption",
                            "1994",
                            "R",
                            "14 Oct 1994",
                            "142 min",
                            "Drama",
                            "Frank Darabont",
                            "Stephen King, Frank Darabont",
                            "Tim Robbins, Morgan Freeman, Bob Gunton",
                            "Two imprisoned men bond over a number of years, finding solace and eventual redemption through acts of common decency."
                        )
                        val movie2 = Movies(
                            "Batman: The Dark Knight Returns, Part 1",
                            "2012",
                            "PG-13",
                            "25 Sep 2012",
                            "76 min",
                            "Animation, Action, Crime, Drama, Thriller",
                            "Jay Oliva",
                            "Bob Kane (character created by: Batman), Frank Miller (comic book), Klaus Janson (comic book), Bob Goodman",
                            "Peter Weller, Ariel Winter, David Selby, Wade Williams",
                            "Batman has not been seen for ten years. A new breed of criminal ravages Gotham City, forcing 55-year-old Bruce Wayne back into the cape and cowl. But, does he still have what it takes to fight crime in a new era?"
                        )
                        val movie3 = Movies(
                            "The Lord of the Rings: The Return of the King",
                            "2003",
                            "PG-13",
                            "17 Dec 2003",
                            "201 min",
                            "Action, Adventure, Drama",
                            "Peter Jackson",
                            "J.R.R. Tolkien, Fran Walsh, Philippa Boyens",
                            "Elijah Wood, Viggo Mortensen, Ian McKellen",
                            "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring."
                        )
                        val movie4 = Movies(
                            "Inception",
                            "2010",
                            "PG-13",
                            "16 Jul 2010",
                            "148 min",
                            "Action, Adventure, Sci-Fi",
                            "Christopher Nolan",
                            "Christopher Nolan",
                            "Leonardo DiCaprio, Joseph Gordon-Levitt, Elliot Page",
                            "A thief who steals corporate secrets through the use of dream-sharing technology is given the inverse task of planting an idea into the mind of a C.E.O., but his tragic past may doom the project and his team to disaster.",
                        )
                        val movie5 = Movies(
                            "The Matrix",
                            "1999",
                            "R",
                            "31 Mar 1999",
                            "136 min",
                            "Action, Sci-Fi",
                            "Lana Wachowski, Lilly Wachowski",
                            "Lilly Wachowski, Lana Wachowski",
                            "Keanu Reeves, Laurence Fishburne, Carrie-Anne Moss",
                            "When a beautiful stranger leads computer hacker Neo to aforbidding underworld, he discovers the shocking truth--the life heknows is the elaborate deception of an evil cyber-intelligence."
                        )

                        MOVIES_DAO.insertAll(movie1, movie2, movie3, movie4, movie5)

                        val Movies_Arrray: List<Movies> = MOVIES_DAO.getAll()

                        for (m in Movies_Arrray) {
                            Log.i("t", m.Title)
                            runOnUiThread(){
                                Toast.makeText(applicationContext,"${m.Title} added", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                    catch (e: SQLiteConstraintException){

                        val MOVIES_DAO = db.moviesDAO()
                        val Movies_Arrray: List<Movies> = MOVIES_DAO.getAll()

                        for (m in Movies_Arrray) {
                            runOnUiThread(){
                                Toast.makeText(applicationContext,"${m.Title} already added", Toast.LENGTH_SHORT).show()
                            }
                        }
                    }
                }
            }
        }
    }
}