package com.aenatural.aenaturals.myspalon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass

class MSAddBeauticians : BaseClass() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
setLayoutXml()

    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msadd_beauticians)
        birdTheme()
    }

    override fun initializeViews() {
    }

    override fun initializeClickListners() {
    }

    override fun initializeInputs() {
        TODO("Not yet implemented")
    }

    override fun initializeLabels() {
        TODO("Not yet implemented")
    }
}