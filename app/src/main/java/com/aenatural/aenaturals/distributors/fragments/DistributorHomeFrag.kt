package com.aenatural.aenaturals.distributors.fragments

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.aenatural.aenaturals.distributors.AddSalesmanActivity
import com.aenatural.aenaturals.distributors.DistributorProfileActivity
import com.aenatural.aenaturals.distributors.DistributorRequestActiivty
import com.aenatural.aenaturals.distributors.SellerAdapter
import com.aenatural.aenaturals.salesmans.BottomSectionAdapter
import com.aenatural.aenaturals.salesmans.SecondBottomSectionAdapter
import org.eazegraph.lib.charts.PieChart
import org.eazegraph.lib.models.PieModel
import java.util.*

class DistributorHomeFrag : Fragment() {
    lateinit var addSellers: LinearLayout
    lateinit var itemRequest: LinearLayout
    lateinit var recyclerView1: RecyclerView
    lateinit var recyclerView2: RecyclerView
    lateinit var pieChart: PieChart
    lateinit var sellerList: ArrayList<SellerDataModel>
    lateinit var itemList: ArrayList<RetailerDataModel>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_distributor_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initializeViews(view)
        initializeClickListners()
        initializeInputs()
        initializeLabels()
    }
    public fun initializeViews(view: View) {
        addSellers = view.findViewById(R.id.addSellers)
        recyclerView1 = view.findViewById(R.id.distributorTopRecycler)
        pieChart = view.findViewById(R.id.distibutorpiechart)
        recyclerView2 = view.findViewById(R.id.distributorMidRecycler)
        itemRequest = view.findViewById(R.id.itemRequest)

        setPieChart()

    }
    private fun setPieChart(){

        pieChart.isUseInnerValue = false
        pieChart.innerValueString = "Sales"
        pieChart.innerPaddingColor = R.color.midgreen
        pieChart.isUseInnerPadding= false
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

    public fun initializeClickListners() {

        addSellers.setOnClickListener {
            startActivity(Intent(requireContext(), AddSalesmanActivity::class.java))
        }
        itemRequest.setOnClickListener {
            startActivity(Intent(requireContext(), DistributorRequestActiivty::class.java))
        }
    }

    public fun initializeInputs() {

    }

    public fun initializeLabels() {
        initData()
        recyclerView1.adapter = SellerAdapter(sellerList)

        recyclerView1.layoutManager = LinearLayoutManager(requireContext(),
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

        recyclerView2.adapter = BottomSectionAdapter(itemList)
        recyclerView2.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)

        recyclerView2.adapter = SecondBottomSectionAdapter(itemList)
        recyclerView2.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)

    }
    private fun initData(){
        sellerList= ArrayList()
        itemList= ArrayList()
        for(i in 0..5)
            sellerList.add(SellerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898",""))
        for(i in 0..5)
            itemList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))
    }
}