package com.aenatural.aenaturals.common

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import androidx.core.content.ContextCompat
import com.pearl.aenaturals.R

class Splash : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash)
        window.statusBarColor = ContextCompat.getColor(this, R.color.lightgreen)
        Handler().postDelayed({
            val mIntent = Intent(this@Splash, WelcomeScreen::class.java)
            startActivity(mIntent)
            finish()
        }, 2000)
    }
}