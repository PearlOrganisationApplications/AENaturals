package com.aenatural.aenaturals.salesmans

import android.os.Bundle
import android.view.View
import android.view.WindowManager
import com.aenatural.aenaturals.baseframework.BaseClass
import com.pearl.aenaturals.R

class SalesmanProfileActivity : BaseClass() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()

    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_salesman_profile)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = resources.getColor(R.color.midgreen)
        window.decorView.systemUiVisibility = View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR
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