package com.aenatural.aenaturals.customers

import android.annotation.SuppressLint
import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.widget.LinearLayout
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.LinearSnapHelper
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.SliderModel
import com.aenatural.aenaturals.customers.adapters.SliderAdapter
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.common.Models.SellerDataModel
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import java.util.*
import kotlin.collections.ArrayList


class CustomerDashboard : BaseClass() {
lateinit var customerTrendingRecyclerView:RecyclerView
lateinit var customerallItemsRecycler:RecyclerView
lateinit var profileIcon: LinearLayout

    lateinit var itemList: java.util.ArrayList<RetailerDataModel>
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
        initializeLabels()
        initializeInputs()
    }

    override fun setLayoutXml() {
        getMidGreentheme()
        setContentView(R.layout.activity_customer_dashboard)
    }

    override fun initializeViews() {
        customerTrendingRecyclerView = findViewById(R.id.customerTrendingRecyclerView)
        customerallItemsRecycler = findViewById(R.id.customerallItemsRecycler)
        profileIcon = findViewById(R.id.customerProfileIcon)
    }

    override fun initializeClickListners() {
    profileIcon.setOnClickListener {
    startActivity(Intent(this,CustomerProfileActivity::class.java))
}
    }

    override fun initializeInputs() {

    }

    override fun initializeLabels() {
        initData()
        customerTrendingRecyclerView.adapter = CustomerTrendingAdapter(itemList)
        customerTrendingRecyclerView.layoutManager = LinearLayoutManager(this,LinearLayoutManager.HORIZONTAL,false)
        Timer().schedule(object : TimerTask() {
            override fun run() {
                if ((customerTrendingRecyclerView.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < itemList.size - 1) {
                    customerTrendingRecyclerView.layoutManager!!.smoothScrollToPosition(
                        customerTrendingRecyclerView,
                        RecyclerView.State(),
                        (customerTrendingRecyclerView.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() + 1
                    )
                } else if ((customerTrendingRecyclerView.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < itemList.size - 1) {
                    customerTrendingRecyclerView.layoutManager!!.smoothScrollToPosition(customerTrendingRecyclerView, RecyclerView.State(), 0)
                }else{
                    customerTrendingRecyclerView.smoothScrollToPosition(0);
                }
            }
        },0, 1500)


        customerallItemsRecycler.adapter = CustomerAllItemAdapter(itemList)
        customerallItemsRecycler.layoutManager = LinearLayoutManager(this)
    }


    private fun initData(){
        itemList= java.util.ArrayList()
        for(i in 0..5)
            itemList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))
    }

}