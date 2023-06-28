package com.aenatural.aenaturals.myspalon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass

class MSAddAppointmentActivity : BaseClass() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msadd_appointment)
        birdTheme()
    }

    override fun initializeViews() {

    }

    override fun initializeClickListners() {

    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }
}