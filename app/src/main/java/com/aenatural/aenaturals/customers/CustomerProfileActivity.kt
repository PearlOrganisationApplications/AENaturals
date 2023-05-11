package com.aenatural.aenaturals.customers

import android.app.AlertDialog
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ScrollView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.common.Login
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.customers.adapters.CustomerProfileAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class CustomerProfileActivity : BaseClass() {

    lateinit var customerLogout: TextView
    lateinit var cust_customercareLayout: ScrollView
    lateinit var cust_privacypolicylayout: ScrollView
    lateinit var profile_bottomnav:BottomNavigationView
    lateinit var itemList: java.util.ArrayList<RetailerDataModel>
    lateinit var customerOrderHistoryRecycler:RecyclerView
    lateinit var alertDialog:AlertDialog.Builder
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
        initializeInputs()
        initializeLabels()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_customer_profile)
        getMidGreentheme()
    }

    override fun initializeViews() {
        profile_bottomnav = findViewById(R.id.customer_bottomnav)
        customerOrderHistoryRecycler = findViewById(R.id.customerOrderHistoryRecycler)

        cust_customercareLayout = findViewById(R.id.cust_customercareLayout)
        cust_privacypolicylayout = findViewById(R.id.cust_privacypolicylayout)


        alertDialog = AlertDialog.Builder(this)
        alertDialog.setTitle("Alert")
        alertDialog.setMessage("Do you want to Logout?")
        alertDialog.setPositiveButton("Yes"){dialogInterface,_ ->
            run{
                startActivity(Intent(this, Login::class.java))
                dialogInterface.dismiss()
            }
        }
        alertDialog.setNegativeButton("No"){dialogInterface,_ ->
            run{
                //startActivity(Intent(this, SalesmanDashboard::class.java))
                dialogInterface.dismiss()
            }
        }
    }

    override fun initializeClickListners() {
        profile_bottomnav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.customercare->{
                    cust_customercareLayout.isVisible = !cust_customercareLayout.isVisible
                    cust_privacypolicylayout.visibility = View.GONE
                }
                R.id.cust_privacybn->{
                    cust_privacypolicylayout.isVisible = !cust_privacypolicylayout.isVisible
                    cust_customercareLayout.visibility = View.GONE
                }
                R.id.cust_logout_bn->{
alertDialog.show()
                }

            }
            true
        }
    }

    override fun initializeInputs() {

    }

    private fun initDataModels(){
        itemList= java.util.ArrayList()
        for(i in 0..5)
            itemList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))
    }
    override fun initializeLabels() {
        initDataModels()
        customerOrderHistoryRecycler.adapter = CustomerProfileAdapter(itemList)
        customerOrderHistoryRecycler.layoutManager = LinearLayoutManager(this)

    }
}