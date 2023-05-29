package com.aenatural.aenaturals.customers.fragments

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
import com.aenatural.aenaturals.customers.adapters.CustomerOrderHistoryAdapter
import com.aenatural.aenaturals.customers.adapters.CustomerSaleHistoryAdapter
import com.aenatural.aenaturals.distributors.fragments.DistributorHomeFrag
import com.google.android.material.bottomnavigation.BottomNavigationView


class CustomerOrder_HistoryFrag : Fragment() {

    lateinit var pendingPayment:ArrayList<SellerDataModel>
    lateinit var customer_history_recyler: RecyclerView
    lateinit var customer_history_topnav:BottomNavigationView
    lateinit var customer_sellhistory_layout:LinearLayout
    lateinit var customer_order_history_layout:LinearLayout
    lateinit var customer_order_history_recycler:RecyclerView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_customer_order_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backPress()
        requireActivity().findViewById<LinearLayout>(R.id.include).visibility =View.VISIBLE
        initializeViews(view)
        topnav(view)
    }

    private fun topnav(view: View) {
        customer_history_topnav.setOnItemSelectedListener {
            when(it.itemId){
                R.id.menu_sell_history->{
                    customer_sellhistory_layout.visibility = View.VISIBLE
                    customer_order_history_layout.visibility=View.GONE
                }
                R.id.menu_order_history->{
                    customer_sellhistory_layout.visibility = View.GONE
                    customer_order_history_layout.visibility=View.VISIBLE
                }
            }
            true
        }
    }

    private fun initializeViews(view: View) {
        customer_history_recyler = view.findViewById(R.id.customer_history_recyler);
        customer_history_topnav = view.findViewById(R.id.customer_history_topnav)
        customer_sellhistory_layout = view.findViewById(R.id.customer_sellhistory_layout)
        customer_order_history_layout = view.findViewById(R.id.customer_order_history_layout)
        customer_order_history_recycler = view.findViewById(R.id.customer_order_history_recycler)
        initRecyclerView(view)
    }

    private fun initRecyclerView(view: View) {
        initData()
        customer_history_recyler.adapter = CustomerSaleHistoryAdapter(pendingPayment)
        customer_history_recyler.layoutManager = LinearLayoutManager(requireContext())

        customer_order_history_recycler.adapter = CustomerOrderHistoryAdapter(pendingPayment)
        customer_order_history_recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initData() {
        pendingPayment = ArrayList()
        for (i in 0..5) {
            pendingPayment.add(SellerDataModel("", "", "", "", ""))
        }
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