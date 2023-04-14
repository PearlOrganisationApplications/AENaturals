package com.aenatural.aenaturals.common

import android.content.Intent
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import com.aenatural.aenaturals.distributors.DistributorDashboard
import com.aenatural.aenaturals.retailers.RetailerDashboard
import com.aenatural.aenaturals.salesmans.SalesmanDashboard
import com.pearl.aenaturals.R

class Login : AppCompatActivity() {


    lateinit var emailEditText: EditText
    lateinit var passwordEditText: EditText

    lateinit var salesmanButton: CardView
    lateinit var retailerButton: CardView
    lateinit var distributorButton: CardView
    lateinit var tv_login: TextView

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
        tv_login = findViewById(R.id.tv_login)
    }


    private fun clickListeners() {

        tv_login.setOnClickListener {


            if(emailEditText.text.toString().equals("salesman") && passwordEditText.text.toString().equals("123")){
                startActivity(Intent(this, SalesmanDashboard::class.java))
            }else if(){

            }else if(){

            }else{

            }


        }



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