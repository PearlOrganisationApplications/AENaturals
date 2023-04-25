package com.aenatural.aenaturals.distributors

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.cardview.widget.CardView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass

class AddSalesmanActivity : BaseClass() {
    lateinit var seller_RegisSubmit:CardView;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
setLayoutXml()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_add_salesman)
        getMidGreentheme()
    }

    override fun initializeViews() {
        seller_RegisSubmit = findViewById(R.id.seller_RegisSubmit)
    }

    override fun initializeClickListners() {
        seller_RegisSubmit.setOnClickListener {
            startActivity(Intent(this,DistributorDashboard::class.java))
        }

    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }
}