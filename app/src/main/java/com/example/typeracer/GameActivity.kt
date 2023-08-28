package com.example.typeracer

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.CountDownTimer
import android.widget.EditText
import android.widget.TextView
import kotlin.random.Random

class GameActivity : AppCompatActivity() {
    private lateinit var input: EditText
    private lateinit var clock: TextView
    private lateinit var timer: CountDownTimer
    private lateinit var passageView: TextView
    private lateinit var passage: String
    private var timeLeft: Long = 60000
    private lateinit var typed: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_game)

        input = findViewById(R.id.text)
        clock = findViewById(R.id.clock)
        passageView = findViewById(R.id.passageView)

        input.requestFocus()

        val pas_num = Random.nextInt(0,5)
        passage = when(pas_num) {
            1 -> getString(R.string.pas_1)
            2 -> getString(R.string.pas_2)
            3 -> getString(R.string.pas_3)
            4 -> getString(R.string.pas_4)
            else -> getString(R.string.pas_0)
        }

        passageView.text = passage
        startTimer()
    }

    private fun startTimer() {
        timer = object :CountDownTimer(timeLeft,1000) {
            override fun onTick(p0: Long) {
                timeLeft = p0
                updateClock()
            }

            override fun onFinish() {
                clock.text = "GAME DONE"
                typed = input.text.toString()
                compare()
            }
        }.start()
    }

    private fun updateClock() {
        var sec = timeLeft.toInt() / 1000
        var stringTime = "CLOCK: "+ sec + " SEC LEFT"
        runOnUiThread {
            kotlin.run {
                clock.text = stringTime
            }
        }
    }

    private fun compare() {
        val user = typed.split("\\s".toRegex()).toTypedArray()
        val given = passage.split("\\s".toRegex()).toTypedArray()
        val l = user.count() - 1
        var score = 0
        for (i in 0..l) {
            if (given[i].compareTo(user[i]) == 0) {
                score += 1
            }
        }
        goToScore(score)
    }

    private fun goToScore(score: Int) {
        val intent = Intent(this, ScoreActivity::class.java)
        intent.putExtra("score", score)
        startActivity(intent)
    }
}