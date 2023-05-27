package com.aenatural.aenaturals.distributors.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.aenatural.aenaturals.distributors.Adapters.DistributorAllOrderAdapter
import com.aenatural.aenaturals.distributors.Adapters.DistributorCartItemAdapter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry
import com.google.android.material.bottomnavigation.BottomNavigationView


class DistributorProductsFrag : Fragment() {

private lateinit var dist_cart_item_Recycler:RecyclerView
private lateinit var dist_all_orders_recycler:RecyclerView
private lateinit var distributorOrderBottomMenu:BottomNavigationView
private lateinit var distributorCartItemLayout:LinearLayout
private lateinit var distributorAllOrdersLayout:LinearLayout

private lateinit var headerTV:TextView

private var cart_item_list = ArrayList<RetailerDataModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_distributor_profit, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<LinearLayout>(R.id.headerdistributor).visibility =View.GONE
        initializeViews(view)
        BottomNavControl(view)

    }
    public fun initializeViews(view: View) {
        dist_cart_item_Recycler = view.findViewById(R.id.dist_cart_item_Recycler)
        dist_all_orders_recycler = view.findViewById(R.id.dist_all_orders_recycler)
        distributorOrderBottomMenu = view.findViewById(R.id.distributor_order_bottomMenu)
        distributorCartItemLayout = view.findViewById(R.id.distibutor_cart_items_layout)
        distributorAllOrdersLayout = view.findViewById(R.id.dist_allorders_layout)
        headerTV = view.findViewById(R.id.dist_product_header)

        initRecyclerView(view)
    }

    private fun initRecyclerView(view: View) {
        initData()
        dist_cart_item_Recycler.adapter = DistributorCartItemAdapter(cart_item_list)
        dist_cart_item_Recycler.layoutManager = LinearLayoutManager(requireContext())

        dist_all_orders_recycler.adapter = DistributorAllOrderAdapter(cart_item_list)
        dist_all_orders_recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initData() {
        cart_item_list = ArrayList()
        for(i in 0..10){
            cart_item_list.add(RetailerDataModel("","","",""))
        }
    }

    private fun BottomNavControl(view: View) {
        distributorOrderBottomMenu.setOnItemSelectedListener {
            when(it.itemId){
                R.id.dist_order_item->{
                    headerTV.text = "Order products"
                 distributorCartItemLayout.visibility=View.VISIBLE
                 distributorAllOrdersLayout.visibility=View.GONE
                }
                R.id.dist_all_orders->{
                    headerTV.text = "All Orders"
                    distributorCartItemLayout.visibility=View.GONE
                    distributorAllOrdersLayout.visibility=View.VISIBLE
                }
            }
            true
        }
    }
}

