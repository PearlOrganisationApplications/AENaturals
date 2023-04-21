package com.aenatural.aenaturals.salesmans

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.R

class CustomerRegistrationActivity : BaseClass() {
    lateinit var genderMale:ImageView
    lateinit var genderFemale:ImageView
    lateinit var customerFormSubmit:CardView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        getLightGreentheme()
        setContentView(R.layout.activity_customer_registration)
    }

    override fun initializeViews() {
        genderMale = findViewById(R.id.genderMale)
        genderFemale = findViewById(R.id.genderFemale)
        customerFormSubmit = findViewById(R.id.customerFormSubmit)
    }

    override fun initializeClickListners() {
        var m=0
        var f=0
        genderMale.setOnClickListener {
            m=1
            if(f==1)
            {
                f=0
                genderFemale.setBackgroundResource(R.drawable.loginedittextbg)
            }
            genderMale.setBackgroundResource(R.drawable.tapcurvedbackground)
        }

        genderFemale.setOnClickListener {
            f=1
            if(m==1)
            {
                m=0
                genderMale.setBackgroundResource(R.drawable.loginedittextbg)
            }
            genderFemale.setBackgroundResource(R.drawable.tapcurvedbackground)
        }

        customerFormSubmit.setOnClickListener {
            startActivity(Intent(this,SalesmanDashboard::class.java))
        }

    }

    override fun initializeInputs() {
    }

    override fun initializeLabels() {
    }
}