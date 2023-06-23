package com.aenatural.aenaturals.myspalon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass

class MSCustomersActivity : BaseClass() {
    lateinit var add_customers_btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_mscustomers)
        birdTheme()
    }

    override fun initializeViews() {
        add_customers_btn = findViewById(R.id.add_customers_btn)
    }

    override fun initializeClickListners() {
        add_customers_btn.setOnClickListener {
            startActivity(Intent(this,MSAddCustomers::class.java))
        }
    }

    override fun initializeInputs() {
    }

    override fun initializeLabels() {
    }
}