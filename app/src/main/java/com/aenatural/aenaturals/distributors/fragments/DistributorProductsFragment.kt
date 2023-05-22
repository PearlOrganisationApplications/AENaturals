package com.aenatural.aenaturals.salesmans.fragments

import android.graphics.Color
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.ScrollView
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.salesmans.AllProductsAdapter
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.aenatural.aenaturals.salesmans.Adapters.PendingOrderVH
import com.aenatural.aenaturals.salesmans.Adapters.PendingOrdersAdapter
import com.aenatural.aenaturals.salesmans.Adapters.PendingPaymentAdapter
import com.aenatural.aenaturals.salesmans.Adapters.ReturnOrderAdapter
import com.github.mikephil.charting.charts.BarChart
import com.github.mikephil.charting.data.BarData
import com.github.mikephil.charting.data.BarDataSet
import com.github.mikephil.charting.data.BarEntry

class DistributorProductsFragment : Fragment() {
    lateinit var salesmanMoreRecyclerView:RecyclerView
    lateinit var pendingPaymentRecycler:RecyclerView
    lateinit var returnOrdersRecycler:RecyclerView
    lateinit var pendingOrderData:ArrayList<SellerDataModel>
    lateinit var pendingPayment:ArrayList<SellerDataModel>
    lateinit var returnOrders:ArrayList<SellerDataModel>
    lateinit var pendingOrderCard:CardView
    lateinit var returnOrderCard:CardView
    lateinit var pendingPaymentCard:CardView
    lateinit var distributor_totalProfitCard:CardView
    lateinit var distributorProfit:LinearLayout

    lateinit var return_order_layout:ConstraintLayout
    lateinit var pending_payment_layout:ConstraintLayout
    lateinit var pendingorderslayout:ConstraintLayout

    lateinit var barDataSet: BarDataSet
    lateinit var barEntriesList: ArrayList<BarEntry>
    lateinit var barChart: BarChart

    lateinit var barData: BarData
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_distributor_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews(view)
        initDataModels(view)
        initrecyclerViews(view)
        initclickListener(view)
    }

    private fun initclickListener(view: View) {
        pendingPaymentCard.setOnClickListener {
            pendingorderslayout.visibility =View.GONE
            pending_payment_layout.visibility =View.VISIBLE
            return_order_layout.visibility =View.GONE
            distributorProfit.visibility  = View.GONE
            

        }
        returnOrderCard.setOnClickListener {
            pendingorderslayout.visibility =View.GONE
            pending_payment_layout.visibility =View.GONE
            return_order_layout.visibility =View.VISIBLE
        }
        pendingOrderCard.setOnClickListener {
            pendingorderslayout.visibility =View.VISIBLE
            pending_payment_layout.visibility =View.GONE
            return_order_layout.visibility =View.GONE
        }
        distributor_totalProfitCard.setOnClickListener {
            pendingorderslayout.visibility =View.GONE
            pending_payment_layout.visibility =View.GONE
            return_order_layout.visibility =View.GONE
            distributorProfit.visibility  = View.VISIBLE
            setchart()
        }

    }

    private fun initDataModels(view: View) {
        pendingOrderData = ArrayList()
        pendingPayment = ArrayList()
        returnOrders = ArrayList()
        for(i in 0..5){
            pendingOrderData.add(SellerDataModel("","","","",""))
            pendingPayment.add(SellerDataModel("","","","",""))
            returnOrders.add(SellerDataModel("","","","",""))
        }
    }

    private fun initrecyclerViews(view: View) {
        salesmanMoreRecyclerView.adapter = PendingOrdersAdapter(requireContext(),pendingOrderData)
        salesmanMoreRecyclerView.layoutManager = LinearLayoutManager(requireContext())
        pendingPaymentRecycler.adapter = PendingPaymentAdapter(requireContext(),pendingOrderData)
        pendingPaymentRecycler.layoutManager = LinearLayoutManager(requireContext())

        returnOrdersRecycler.adapter = ReturnOrderAdapter(requireContext(),pendingOrderData)
        returnOrdersRecycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initializeViews(view: View) {
        salesmanMoreRecyclerView = view.findViewById(R.id.salesmanMoreRecyclerView)
        pendingPaymentRecycler = view.findViewById(R.id.pendingPaymentRecycler)
        returnOrdersRecycler = view.findViewById(R.id.returnOrdersRecycler)

        pendingPaymentCard = view.findViewById(R.id.distributor_pendingPaymentCard)
        returnOrderCard = view.findViewById(R.id.distributor_returnOrderCard)
        pendingOrderCard = view.findViewById(R.id.distributor_pendingOrderCard)

        return_order_layout = view.findViewById(R.id.return_order_layout)
        pending_payment_layout = view.findViewById(R.id.pending_payment_layout)
        pendingorderslayout = view.findViewById(R.id.pendingorderslayout)
        distributor_totalProfitCard = view.findViewById(R.id.distributor_totalProfitCard)

        distributorProfit = view.findViewById(R.id.distributorProfit)
        barChart = view.findViewById(R.id.cust_chart)

        

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

}