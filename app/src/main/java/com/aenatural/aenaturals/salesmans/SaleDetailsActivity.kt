package com.aenatural.aenaturals.salesmans

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import androidx.cardview.widget.CardView
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.R
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel

class SaleDetailsActivity : BaseClass() {
    lateinit var genderMale:ImageView
    lateinit var genderFemale:ImageView
    lateinit var customerFormSubmit:CardView
    lateinit var pieChart: PieChart

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        getLightGreentheme()
        setContentView(R.layout.activity_customer_registration)
    }

    override fun initializeViews() {
        /*genderMale = findViewById(R.id.genderMale)
        genderFemale = findViewById(R.id.genderFemale)
        customerFormSubmit = findViewById(R.id.customerFormSubmit)*/

        pieChart = findViewById(R.id.salesman_piechart)
        setPieChart()
    }
    private fun setPieChart(){

        pieChart.isUseInnerValue = true
        pieChart.innerValueString = "Sales"
        pieChart.innerPaddingColor = R.color.midgreen

        pieChart.addPieSlice(
            PieModel(
                "Salesman 1", 40F,
                Color.parseColor("#004D40")
            )
        )
        pieChart.addPieSlice(
            PieModel(
                "Salesman 2", 30F,
                Color.parseColor("#00BFA5")
            )
        )
        pieChart.addPieSlice(
            PieModel(
                "Salesman 3", 20F,
                Color.parseColor("#1DE9B6")
            )
        )
        pieChart.addPieSlice(
            PieModel(
                "others", 10F,
                Color.parseColor("#64FFDA")
            )
        )
        pieChart.startAnimation();



    }
    override fun initializeClickListners() {
/*
        var m=0
        var f=0
        genderMale.setOnClickListener {
            m=1
            if(f==1)
            {
                f=0
                genderFemale.setBackgroundResource(R.drawable.loginedittextbg)
            }
            genderMale.setBackgroundResource(R.drawable.tapcurvedbackground)
        }

        genderFemale.setOnClickListener {
            f=1
            if(m==1)
            {
                m=0
                genderMale.setBackgroundResource(R.drawable.loginedittextbg)
            }
            genderFemale.setBackgroundResource(R.drawable.tapcurvedbackground)
        }

        customerFormSubmit.setOnClickListener {
            startActivity(Intent(this,SalesmanDashboard::class.java))
        }
*/



    }

    override fun initializeInputs() {
    }

    override fun initializeLabels() {
    }
}