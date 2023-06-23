package com.aenatural.aenaturals.myspalon

import android.content.Intent
import android.os.Bundle
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass


class MSHomeScreenActivity : BaseClass() {
    lateinit var ms_myprofile:LinearLayout
    lateinit var ms_home_beauticians:LinearLayout
    lateinit var ms_home_customers:LinearLayout
    lateinit var btn_drawer_menu:ImageView
    lateinit var drawerLayout:DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_mshome_screen)
birdTheme()
    }

    override fun initializeViews() {
        ms_myprofile = findViewById(R.id.ms_myprofile)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        btn_drawer_menu = findViewById(R.id.btn_drawer_menu)
        ms_home_beauticians = findViewById(R.id.ms_home_beauticians)
        ms_home_customers = findViewById(R.id.ms_home_customers)
    }

    override fun initializeClickListners() {
        ms_myprofile.setOnClickListener {
            startActivity(Intent(this,MSEditProfileActivit::class.java))
        }
        ms_home_beauticians.setOnClickListener {
            startActivity(Intent(this,MSBeauticians::class.java))
        }
        ms_home_customers.setOnClickListener {
            startActivity(Intent(this,MSCustomersActivity::class.java))
        }
        btn_drawer_menu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START);
        }
    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }
}