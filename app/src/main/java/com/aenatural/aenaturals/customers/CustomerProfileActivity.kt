package com.aenatural.aenaturals.customers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.customers.adapters.CustomerProfileAdapter

class CustomerProfileActivity : BaseClass() {
    lateinit var itemList: java.util.ArrayList<RetailerDataModel>
    lateinit var customerOrderHistoryRecycler:RecyclerView
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
        customerOrderHistoryRecycler = findViewById(R.id.customerOrderHistoryRecycler)
    }

    override fun initializeClickListners() {

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