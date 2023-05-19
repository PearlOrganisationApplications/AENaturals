package com.aenatural.aenaturals.customers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.aenatural.aenaturals.customers.adapters.CustomerSaleHistoryAdapter


class CustomerOrder_HistoryFrag : Fragment() {

    lateinit var pendingPayment:ArrayList<SellerDataModel>
    lateinit var customer_history_recyler: RecyclerView

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
        requireActivity().findViewById<LinearLayout>(R.id.include).visibility =View.VISIBLE
        initializeViews(view)
    }

    private fun initializeViews(view: View) {
        customer_history_recyler = view.findViewById(R.id.customer_history_recyler);
        initRecyclerView(view)
    }

    private fun initRecyclerView(view: View) {
        initData()
        customer_history_recyler.adapter = CustomerSaleHistoryAdapter(pendingPayment)
        customer_history_recyler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initData() {
        pendingPayment = ArrayList()
        for (i in 0..5) {
            pendingPayment.add(SellerDataModel("", "", "", "", ""))
        }
    }


}