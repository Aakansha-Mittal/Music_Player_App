package com.example.music_player_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        supportActionBar?.hide()

        val handler = Handler()
        handler.postDelayed( {
            val intent = Intent(this, Music::class.java)
            startActivity(intent)
        },3000)
    }
}