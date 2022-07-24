package com.example.w1839054_mad_cw_2

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView

class searchActorOutput : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_search_actor_output)
        val actorOutputBackButton = findViewById<Button>(R.id.actor_output_back_btn)
        val actor_output_textView_id = findViewById<TextView>(R.id.actor_output_textView_id)

        var intent: Intent = getIntent()
        var data: String? = intent.getStringExtra("data")

        var dataArray = data?.split(";")
        var Arraylength = dataArray!!.size-1
        var i = 0
        var output = ""

        while (i < Arraylength){
            var text = dataArray[i]
            output = output + text + "\n\n"
            i++
        }

        actor_output_textView_id.text = output

        actorOutputBackButton.setOnClickListener {
            val ACTOR_OUTPUT_BACK_BTN = Intent(this, searchActor::class.java)
            startActivity(ACTOR_OUTPUT_BACK_BTN)
        }
    }

    /*
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
        outState.putString("movie", output)
    }
     */
}