package com.aenatural.aenaturals.distributors

import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.salesmans.Adapters.CartListAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView

class DistributorRequestActiivty : BaseClass() {

    lateinit var distrequestLayout: LinearLayout
    lateinit var distorderLayout: LinearLayout
    lateinit var dist_orderRecycler:RecyclerView
    lateinit var dist_requestRecycler:RecyclerView
    lateinit var distnavigation: BottomNavigationView
    lateinit var retailerList:ArrayList<RetailerDataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        setLayoutXml()
        initializeViews()
        initializeClickListners()
        initializeInputs()
        initializeLabels()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_distributor_request_actiivty)
        getMidGreentheme()
    }

    override fun initializeViews() {
        distrequestLayout = findViewById(R.id.distrequestLayout)
        distorderLayout = findViewById(R.id.distorderLayout)
        distnavigation = findViewById(R.id.distnavigation)
        dist_orderRecycler = findViewById(R.id.dist_orderRecycler)
        dist_requestRecycler = findViewById(R.id.dist_requestRecycler)
        initDataModels()

        dist_orderRecycler.adapter = CartListAdapter(retailerList)
        dist_orderRecycler.layoutManager= LinearLayoutManager(this)

        dist_requestRecycler.adapter = CartListAdapter(retailerList)
        dist_requestRecycler.layoutManager= LinearLayoutManager(this)


    }

    override fun initializeClickListners() {
        distnavigation.setOnItemSelectedListener {
            when(it.itemId){
                R.id.dist_menu_req->{
                    distrequestLayout.visibility= View.VISIBLE
                    distorderLayout.visibility = View.GONE
                }
                R.id.dit_menu_order->{
                    distrequestLayout.visibility= View.GONE
                    distorderLayout.visibility = View.VISIBLE
                }
            }
            true
        }
    }

    override fun initializeInputs() {
    }

    override fun initializeLabels() {
    }

    private fun initDataModels() {
        retailerList= ArrayList()
        for(i in 0..5)
            retailerList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))
    }
}