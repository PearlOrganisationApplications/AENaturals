package com.aenatural.aenaturals.customers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.salesmans.Adapters.CartListAdapter

class CustomerCartActivity : BaseClass() {

    lateinit var customer_cart_recyclerview:RecyclerView
    lateinit var itemList: java.util.ArrayList<RetailerDataModel>
    lateinit var retailerList:ArrayList<RetailerDataModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
        initData()
        initializeInputs()
        initializeLabels()

    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_customer_cart)
        getMidGreentheme()
    }

    override fun initializeViews() {
        customer_cart_recyclerview = findViewById(R.id.customer_cart_recyclerview)
    }

    override fun initializeClickListners() {
    }

    override fun initializeInputs() {
    }

    override fun initializeLabels() {
        customer_cart_recyclerview.adapter = CartListAdapter(retailerList)
        customer_cart_recyclerview.layoutManager= LinearLayoutManager(this)
    }

    private fun initData(){
        retailerList= ArrayList()
        for(i in 0..5)
            retailerList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))
    }
}