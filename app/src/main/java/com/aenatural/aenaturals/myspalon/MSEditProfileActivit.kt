package com.aenatural.aenaturals.myspalon

import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ImageButton
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass

class MSEditProfileActivit : BaseClass() {
    lateinit var profile_backIB: ImageButton
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msedit_profile)
        birdTheme()
    }

    override fun initializeViews() {
        profile_backIB = findViewById(R.id.profile_backIB)
    }

    override fun initializeClickListners() {
        profile_backIB.setOnClickListener {
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