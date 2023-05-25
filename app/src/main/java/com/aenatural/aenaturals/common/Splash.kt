package com.aenatural.aenaturals.common

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.view.Window
import android.view.WindowManager
import android.view.animation.Animation
import android.view.animation.AnimationUtils
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import com.aenatural.aenaturals.R

class Splash : AppCompatActivity() {

    lateinit var iv_logo: ImageView



    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash)
       /* window.statusBarColor = ContextCompat.getColor(this, R.color.lightgreen)
        window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);*/


          Handler().postDelayed({
         val mIntent = Intent(this@Splash, Login::class.java)
         startActivity(mIntent)
         finish()
     }, 2000)

/*
        iv_logo = findViewById(R.id.iv_logo)
        val Animation = AnimationUtils.loadAnimation(this, R.anim.animzoomin)
        //  imageView.startAnimation(Animation);
        Animation.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationStart(animation: Animation) {}
            override fun onAnimationEnd(animation: Animation) {
                startActivity(Intent(applicationContext, WelcomeScreen::class.java))
                // HomeActivity.class is the activity to go after showing the splash screen.
            }

            override fun onAnimationRepeat(animation: Animation) {}
        })
        iv_logo.startAnimation(Animation)
*/




    }
}