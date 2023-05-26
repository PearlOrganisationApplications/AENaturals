package com.aenatural.aenaturals.distributors

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.FrameLayout
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.aenatural.aenaturals.distributors.fragments.AddSalesman
import com.aenatural.aenaturals.distributors.fragments.DistributorHomeFrag
import com.aenatural.aenaturals.salesmans.BottomSectionAdapter
import com.aenatural.aenaturals.salesmans.MidSectionAdapter
import com.aenatural.aenaturals.salesmans.SecondBottomSectionAdapter
import com.aenatural.aenaturals.salesmans.fragments.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel


import java.util.*


class DistributorDashboard : BaseClass() {
    lateinit var profile:LinearLayout
    lateinit var distributor_DashboardFrameLayout:FrameLayout
    lateinit var distributor_bottomnav:BottomNavigationView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
        initializeInputs()
        initializeLabels()
        bottomNavigationControl()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_distributor_dashboard)
        getMidGreentheme()
    }

    override fun initializeViews() {
        profile = findViewById(R.id.headerdistributor)
        distributor_bottomnav = findViewById(R.id.distributor_bottomnav)
        distributor_DashboardFrameLayout = findViewById(R.id.DashboardFrameLayout)

        supportFragmentManager.beginTransaction().replace(R.id.DashboardFrameLayout,
            DistributorHomeFrag()).commit()
    }

    override fun initializeClickListners() {
        profile.setOnClickListener {
            startActivity(Intent(this,DistributorProfileActivity::class.java))
        }

    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {
    }

    private fun bottomNavigationControl() {
        distributor_bottomnav.setOnItemSelectedListener {
            when(it.itemId) {
                R.id.dist_Home->{

                    supportFragmentManager.beginTransaction().replace(R.id.DashboardFrameLayout,
                        DistributorHomeFrag()).commit()
                }
                R.id.dist_Cart->{
                    supportFragmentManager.beginTransaction().replace(R.id.DashboardFrameLayout,
                        Cart()).commit()
                }
                R.id.dist_more->{
                    supportFragmentManager.beginTransaction().replace(R.id.DashboardFrameLayout,
                        DistributorProductsFragment()).commit()
                }
                R.id.dist_addSalesman->{
                    supportFragmentManager.beginTransaction().replace(R.id.DashboardFrameLayout,
                        AddSalesman()).commit()

                }
            }
            true
        }
    }
}