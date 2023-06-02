package com.aenatural.aenaturals.common

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.os.Handler
import android.view.Window
import android.view.WindowManager
import android.widget.ImageView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.customers.CustomerDashboard
import com.aenatural.aenaturals.distributors.DistributorDashboard
import com.aenatural.aenaturals.salesmans.SalesmanDashboard

class Splash : AppCompatActivity() {

    lateinit var iv_logo: ImageView
    lateinit var sharedPref: Session


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_splash)
        /* window.statusBarColor = ContextCompat.getColor(this, R.color.lightgreen)
         window.decorView.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);*/
        sharedPref =Session(this)
        if(sharedPref.isLogin){
            var section = sharedPref.loginSection()
            if(section==1){
                Handler().postDelayed({
                    val mIntent = Intent(this@Splash, DistributorDashboard::class.java)
                    startActivity(mIntent)
                    finish()
                }, 2000)
            }else if(section==2){
                Handler().postDelayed({
                    val mIntent = Intent(this@Splash, SalesmanDashboard::class.java)
                    startActivity(mIntent)
                    finish()
                }, 2000)
            }else if(section ==3){
                Handler().postDelayed({
                    val mIntent = Intent(this@Splash, CustomerDashboard::class.java)
                    startActivity(mIntent)
                    finish()
                }, 2000)
            }else{
                Toast.makeText(this,"Wrong Login Section",Toast.LENGTH_SHORT).show()
                Handler().postDelayed({
                    val mIntent = Intent(this@Splash, Login::class.java)
                    startActivity(mIntent)
                    finish()
                }, 2000)
            }
        }
        else{
            Handler().postDelayed({
                val mIntent = Intent(this@Splash, Login::class.java)
                startActivity(mIntent)
                finish()
            }, 2000)
        }


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