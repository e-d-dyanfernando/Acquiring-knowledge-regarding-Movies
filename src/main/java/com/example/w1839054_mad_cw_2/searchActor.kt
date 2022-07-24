package com.example.w1839054_mad_cw_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.room.Room
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.runBlocking
import kotlinx.coroutines.withContext

class searchActor : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_actor)

        val searchActorSearch = findViewById<EditText>(R.id.actor_search_plainText_id)
        val searchActorBackButton = findViewById<Button>(R.id.actorSearch_back_id)
        val searchActorSearchButton = findViewById<Button>(R.id.actor_search_id)

        searchActorSearchButton.setOnClickListener {
            var name = searchActorSearch.text.toString()
            var result = ""
            runBlocking {
                launch {
                    withContext(Dispatchers.IO){
                        val db = Room.databaseBuilder(
                            applicationContext,
                            moviesDatabase::class.java, "_Movies-Database_"
                        ).build()

                        val MOVIES_DAO = db.moviesDAO()
                        val Movies_Arrray: List<Movies> = MOVIES_DAO.loadActorData(name)

                        for (i in Movies_Arrray){
                            result += i.Title+";"
                        }
                    }
                }
            }

            val SEARCH_ACTOR_SEARCH = Intent(this, searchActorOutput::class.java)
            SEARCH_ACTOR_SEARCH.putExtra("data",result)
            startActivity(SEARCH_ACTOR_SEARCH)
        }
        searchActorBackButton.setOnClickListener {
            val SEARCH_ACTOR_BACK_BTN = Intent(this, MainActivity::class.java)
            startActivity(SEARCH_ACTOR_BACK_BTN)
        }

    }

}