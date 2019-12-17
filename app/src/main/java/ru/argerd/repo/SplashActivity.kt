package ru.argerd.repo

import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import java.util.*

class SplashActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        val timer = Timer()
        val timerTask: TimerTask = object : TimerTask() {
            override fun run() {
                runOnUiThread { startActivity(Intent(this@SplashActivity,
                        MainActivity::class.java)) }
            }
        }
        timer.schedule(timerTask, 3000)
    }
}