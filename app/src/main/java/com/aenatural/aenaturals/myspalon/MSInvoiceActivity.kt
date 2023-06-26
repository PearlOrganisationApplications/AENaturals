package com.aenatural.aenaturals.myspalon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.ImageButton
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass

class MSInvoiceActivity : BaseClass() {
    lateinit var create_invoice_btn: Button
    lateinit var invoiceBack: ImageButton

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msinvoice)
        birdTheme()
    }

    override fun initializeViews() {
        invoiceBack = findViewById(R.id.invoiceBack)
        create_invoice_btn = findViewById(R.id.create_invoice_btn)


    }

    override fun initializeClickListners() {
        create_invoice_btn.setOnClickListener {
            startActivity(Intent(this, CreateInvoide::class.java))
        }

        invoiceBack.setOnClickListener {
            onBackPressed()
        }
    }

    override fun initializeInputs() {
        TODO("Not yet implemented")
    }

    override fun initializeLabels() {
        TODO("Not yet implemented")
    }
}