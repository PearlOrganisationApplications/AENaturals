package com.aenatural.aenaturals.myspalon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass

class MSCalendarSectionActivity : BaseClass() {
    lateinit var ms_add_appointment:Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_mscalendar_section)
        birdTheme()
    }

    override fun initializeViews() {
        ms_add_appointment =findViewById(R.id.ms_add_appointment)
    }

    override fun initializeClickListners() {
        ms_add_appointment.setOnClickListener {
            startActivity(Intent(this,MSAddAppointmentActivity::class.java))
        }
    }

    override fun initializeInputs() {
    }

    override fun initializeLabels() {
    }
}