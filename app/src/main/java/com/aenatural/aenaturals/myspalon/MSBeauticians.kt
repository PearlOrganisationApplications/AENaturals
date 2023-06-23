package com.aenatural.aenaturals.myspalon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass

class MSBeauticians : BaseClass() {
    lateinit var add_beauticians_btn:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msbeauticians)
        birdTheme()
    }

    override fun initializeViews() {
        add_beauticians_btn = findViewById(R.id.add_beauticians_btn)
    }

    override fun initializeClickListners() {
        add_beauticians_btn.setOnClickListener {
            startActivity(Intent(this,MSAddBeauticians::class.java))
        }
    }

    override fun initializeInputs() {
    }

    override fun initializeLabels() {
    }
}