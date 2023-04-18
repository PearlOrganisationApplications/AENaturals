package com.aenatural.aenaturals.salesmans

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.FrameLayout
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.salesmans.fragments.Cart
import com.aenatural.aenaturals.salesmans.fragments.HomeFragment
import com.aenatural.aenaturals.salesmans.fragments.ProductsFragment
import com.aenatural.aenaturals.salesmans.fragments.RequestProducts
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.pearl.aenaturals.R
import java.util.*
import kotlin.collections.ArrayList

class SalesmanDashboard : BaseClass() {


    lateinit var salesDashboardFrameLayout:FrameLayout
    lateinit var salesmanBottomNav:BottomNavigationView
    lateinit var driverProfileIcon:ImageView

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

        setContentView(R.layout.activity_salesman_dashboard)
        getLightGreentheme()
        supportFragmentManager.beginTransaction().replace(R.id.salesDashboardFrameLayout,HomeFragment()).commit()
    }

    private fun bottomNavigationControl() {
        salesmanBottomNav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.saleHome->{
                    supportFragmentManager.beginTransaction().replace(R.id.salesDashboardFrameLayout,HomeFragment()).commit()
                }
                R.id.saleItems->{
                    supportFragmentManager.beginTransaction().replace(R.id.salesDashboardFrameLayout,ProductsFragment()).commit()
                }
                R.id.saleProducts->{
                    supportFragmentManager.beginTransaction().replace(R.id.salesDashboardFrameLayout,RequestProducts()).commit()
                }
                R.id.saleCart->{
                    supportFragmentManager.beginTransaction().replace(R.id.salesDashboardFrameLayout,Cart()).commit()
                }
            }
            true
        }
    }


    override fun initializeViews() {
        salesDashboardFrameLayout = findViewById(R.id.salesDashboardFrameLayout)
        salesmanBottomNav = findViewById(R.id.salesmanBottomNav)
        driverProfileIcon = findViewById(R.id.driverProfileIcon)

    }

    override fun initializeClickListners() {
        driverProfileIcon.setOnClickListener {
            startActivity(Intent(this,SalesmanProfileActivity::class.java))
        }
    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {

    }
}