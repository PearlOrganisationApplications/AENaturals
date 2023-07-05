package com.aenatural.aenaturals.customers.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry


class Cust_performance_frag : Fragment() {

    lateinit var barDataSet: BarDataSet
    lateinit var pendingPayment:ArrayList<SellerDataModel>
    lateinit var barEntriesList: ArrayList<BarEntry>
    lateinit var barChart: BarChart

    lateinit var barData: BarData

    lateinit var customer_history_recyler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_cust_performance_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backPress()
        initializeViews(view)
        setchart()
    }
    public fun initializeViews(view: View) {

        barChart = view.findViewById(R.id.cust_chart);

        initRecyclerView(view)

    }

    private fun initRecyclerView(view: View) {

    }




    private fun setchart() {
        // on below line we are calling get bar
        // chart data to add data to our array list
        getBarChartData()

        // on below line we are initializing our bar data set
        barDataSet = BarDataSet(barEntriesList, "weekly sales")

        // on below line we are initializing our bar data
        barData = BarData(barDataSet)

        // on below line we are setting data to our bar chart
        barChart.data = barData

        // on below line we are setting colors for our bar chart text
        barDataSet.valueTextColor = Color.BLACK

        // on below line we are setting color for our bar data set
        barDataSet.setColor(resources.getColor(R.color.darkgreen))

        // on below line we are setting text size
        barDataSet.valueTextSize = 16f

        // on below line we are enabling description as false
        barChart.description.isEnabled = false
    }


    private fun getBarChartData() {
        barEntriesList = ArrayList()
        barEntriesList.add(BarEntry(1f, 1f))
        barEntriesList.add(BarEntry(2f, 2f))
        barEntriesList.add(BarEntry(3f, 3f))
        barEntriesList.add(BarEntry(4f, 2f))
        barEntriesList.add(BarEntry(5f, 5f))
        barEntriesList.add(BarEntry(6f, 3f))
        barEntriesList.add(BarEntry(7f, 5f))

    }
    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.cust_home_frame,
                        CustomerHomeFrag()
                    ).commit()
            }
        })
    }
}