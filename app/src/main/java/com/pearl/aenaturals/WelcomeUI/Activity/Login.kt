package com.pearl.aenaturals.WelcomeUI.Activity

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.pearl.aenaturals.R
import com.pearl.aenaturals.distributor.UI.Activity.DistributorDashboard
import com.pearl.aenaturals.retailer.UI.Activity.RetailerDashboard
import com.pearl.aenaturals.salesman.UI.Activity.SalesmanDashboard

class Login : AppCompatActivity() {


    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText

    lateinit var salesmanButton: CardView
    lateinit var retailerButton: CardView
    lateinit var distributorButton: CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.statusBarColor = ContextCompat.getColor(this, R.color.darkgreen)
        setContentView(R.layout.activity_login)

        initializeView()
        clickListeners()
    }

    private fun initializeView() {
        emailEditText = findViewById(R.id.emailEditText)
        passwordEditText = findViewById(R.id.passwordEditText)

        salesmanButton = findViewById(R.id.salesmanButton)
        retailerButton = findViewById(R.id.retailerButton)
        distributorButton = findViewById(R.id.distributorButton)
    }


    private fun clickListeners() {
        salesmanButton.setOnClickListener {
            startActivity(Intent(this, SalesmanDashboard::class.java))
        }
        retailerButton.setOnClickListener {
            startActivity(Intent(this, RetailerDashboard::class.java))
        }
        distributorButton.setOnClickListener {
            startActivity(Intent(this, DistributorDashboard::class.java))
        }
    }


}