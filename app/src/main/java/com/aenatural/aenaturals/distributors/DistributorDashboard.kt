package com.aenatural.aenaturals.distributors

import android.content.Intent
import android.graphics.Color
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
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import java.util.*


class DistributorDashboard : BaseClass() {
    lateinit var profile:LinearLayout
    lateinit var addSellers:LinearLayout
    lateinit var recyclerView1: RecyclerView
    lateinit var recyclerView2: RecyclerView
    lateinit var pieChart: PieChart
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
        addSellers = findViewById(R.id.addSellers)
        recyclerView1 = findViewById(R.id.distributorTopRecycler)
        pieChart = findViewById(R.id.distibutorpiechart)
        setPieChart()
        recyclerView2 = findViewById(R.id.distributorMidRecycler)

    }
    private fun setPieChart(){
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
    }

    override fun initializeClickListners() {
        profile.setOnClickListener {
            startActivity(Intent(this,DistributorProfileActivity::class.java))
        }
        addSellers.setOnClickListener {
            startActivity(Intent(this,AddSalesmanActivity::class.java))
        }
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