package com.aenatural.aenaturals.distributors

import android.os.Bundle
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.salesmans.BottomSectionAdapter
import com.aenatural.aenaturals.salesmans.MidSectionAdapter
import com.aenatural.aenaturals.salesmans.SecondBottomSectionAdapter
import java.util.*
import kotlin.collections.ArrayList

class DistributorDashboard : BaseClass() {
    lateinit var profile:LinearLayout
    lateinit var recyclerView1: RecyclerView
    lateinit var recyclerView2: RecyclerView
    lateinit var recyclerView3: RecyclerView
    lateinit var sellerList:ArrayList<RetailerDataModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
        initializeInputs()
        initializeLabels()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_distributor_dashboard)
        getMidGreentheme()
    }

    override fun initializeViews() {
        profile = findViewById(R.id.headerdistributor)
        recyclerView1 = findViewById(R.id.distributorTopRecycler)
        recyclerView2 = findViewById(R.id.distributorMidRecycler)

    }

    override fun initializeClickListners() {

    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {
        initData()
        recyclerView1.adapter = MidSectionAdapter(sellerList)

        recyclerView1.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                if ((recyclerView1.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < sellerList.size - 1) {
                    recyclerView1.layoutManager!!.smoothScrollToPosition(
                        recyclerView1,
                        RecyclerView.State(),
                        (recyclerView1.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() + 1
                    )
                } else if ((recyclerView1.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < sellerList.size - 1) {
                    recyclerView1.layoutManager!!.smoothScrollToPosition(recyclerView1, RecyclerView.State(), 0)
                }else{
                    recyclerView1.smoothScrollToPosition(0);
                }
            }
        },0, 1500)

        recyclerView2.adapter = BottomSectionAdapter(sellerList)
        recyclerView2.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.HORIZONTAL,false)

        recyclerView2.adapter = SecondBottomSectionAdapter(sellerList)
        recyclerView2.layoutManager = LinearLayoutManager(this,
            LinearLayoutManager.VERTICAL,false)

    }
    private fun initData(){
        sellerList= ArrayList()
        for(i in 0..5)
            sellerList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))
    }
}