package com.example.w1839054_mad_cw_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.util.Log
import android.widget.Button
import android.widget.TextView

class allMoviesOutput : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_all_movies_output)

        val allMoviesOutputBackButton = findViewById<Button>(R.id.allmovies_output_back_btn)
        val allMoviesOutputPlainText = findViewById<TextView>(R.id.allmovies_output_textView_id)

        allMoviesOutputPlainText.movementMethod = ScrollingMovementMethod()

        allMoviesOutputBackButton.setOnClickListener {
            val ALLMOVIES_OUTPUT_BACK_BTN = Intent(this, allMovies::class.java)
            startActivity(ALLMOVIES_OUTPUT_BACK_BTN)
        }

        var intent: Intent = getIntent()
        var data: String? = intent.getStringExtra("data")

        Log.i("recived", data.toString())
        allMoviesOutputPlainText.text = data.toString()
    }
}