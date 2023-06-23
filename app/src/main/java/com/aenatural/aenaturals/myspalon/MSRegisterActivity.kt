package com.aenatural.aenaturals.myspalon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass

class MSRegisterActivity : BaseClass() {
    lateinit var tv_signup:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msregister)
        getLightGreentheme()
    }

    override fun initializeViews() {
        tv_signup = findViewById(R.id.tv_signup)
    }

    override fun initializeClickListners() {
tv_signup.setOnClickListener {
    startActivity(Intent(this,MSHomeScreenActivity::class.java))
}
    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }
}