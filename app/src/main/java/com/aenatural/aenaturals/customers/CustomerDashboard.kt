package com.aenatural.aenaturals.customers

import android.annotation.SuppressLint
import android.graphics.Color
import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.SliderModel
import com.aenatural.aenaturals.customers.adapters.SliderAdapter
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import java.util.*
import kotlin.collections.ArrayList


class CustomerDashboard : BaseClass() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_customer_dashboard)
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