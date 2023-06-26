package com.aenatural.aenaturals.myspalon

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.view.MenuItem
import android.view.View
import android.widget.ImageView
import android.widget.LinearLayout
import androidx.annotation.RequiresApi
import androidx.core.content.ContextCompat
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.common.Login
import com.google.android.material.navigation.NavigationView


class MSHomeScreenActivity : BaseClass(), NavigationView.OnNavigationItemSelectedListener {
    lateinit var ms_myprofile:LinearLayout
    lateinit var ms_home_beauticians:LinearLayout
    lateinit var ms_home_customers:LinearLayout
    lateinit var ms_home_invoice:LinearLayout
    lateinit var btn_drawer_menu:ImageView
    lateinit var drawerLayout:DrawerLayout
    lateinit var nav_view:NavigationView

    @RequiresApi(Build.VERSION_CODES.M)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    @RequiresApi(Build.VERSION_CODES.M)
    override fun setLayoutXml() {
        setContentView(R.layout.activity_mshome_screen)
           birdTheme()

    }

    override fun initializeViews() {
        ms_myprofile = findViewById(R.id.ms_myprofile)
        drawerLayout = findViewById<DrawerLayout>(R.id.drawer_layout)
        btn_drawer_menu = findViewById(R.id.btn_drawer_menu)
        ms_home_beauticians = findViewById(R.id.ms_home_beauticians)
        ms_home_invoice = findViewById(R.id.ms_home_invoice)
        ms_home_customers = findViewById(R.id.ms_home_customers)
        nav_view = findViewById(R.id.nav_view)
        nav_view.setNavigationItemSelectedListener(this)
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
        ms_home_invoice.setOnClickListener {
            startActivity(Intent(this,MSInvoiceActivity::class.java))
        }
    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {

        when(item.itemId){
            R.id.menu_item1 ->{
                startActivity(Intent(this,MSBeauticians::class.java))
            }
            R.id.menu_item2 ->{
                startActivity(Intent(this,MSCustomersActivity::class.java))
            }
            R.id.menu_item3 ->{

            }
            R.id.menu_appointments ->{

            }
            R.id.menu_invoices->{
                startActivity(Intent(this,MSInvoiceActivity::class.java))
            }
            R.id.menu_logout ->{
                startActivity(Intent(this,Login::class.java))
                finish()
            }
        }
        // Close the drawer after handling the click event
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }
}