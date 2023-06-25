package com.example.harsha_news_app

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler

class SplashActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Set the layout for the splash screen activity
        setContentView(R.layout.activity_splash)

        // Optional: Customize the splash screen appearance (e.g., hide the action bar)
        supportActionBar?.hide()

        // Simulate a delay for the splash screen (e.g., 2 seconds)
        Handler().postDelayed({
            // Start your main activity or any other desired activity
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Optional: Close the splash screen activity
        }, 2000) // 2000 milliseconds = 2 seconds
    }
}
