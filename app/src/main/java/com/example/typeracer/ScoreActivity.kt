package com.example.typeracer

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity


class ScoreActivity : AppCompatActivity() {
    private lateinit var backButton: Button
    private lateinit var scoreView: TextView
    private var score = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_score)

        backButton = findViewById(R.id.back_button)
        scoreView = findViewById(R.id.score)
        score = intent.getIntExtra("score", 0)

        scoreView.text = score.toString() + " WPM"

        backButton.setOnClickListener{ goBackToStart() }
    }

    private fun goBackToStart() {
        val intent = Intent(this, MainActivity::class.java)
        startActivity(intent)
    }
}
