package com.aenatural.aenaturals.customers.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.salesmans.Adapters.CartListAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


class CustomerOrderFrag : Fragment() {

    lateinit var cust_addproducts_recycler: RecyclerView
    lateinit var orderListingTopBN: BottomNavigationView
    lateinit var addLayout :LinearLayout
    lateinit var listingLayout:ConstraintLayout
    lateinit var retailerList:ArrayList<RetailerDataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_customer_pending, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<LinearLayout>(R.id.include).visibility =View.GONE
        initViews(view)
        initClickListener(view)
        setDataModels()
        initRecyclerViews(view)


    }

    private fun initRecyclerViews(view: View) {

        cust_addproducts_recycler.adapter = CartListAdapter(retailerList)
        cust_addproducts_recycler.layoutManager= LinearLayoutManager(requireContext())
    }

    private fun setDataModels() {
        retailerList= ArrayList()
        for(i in 0..5)
            retailerList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))
    }

    private fun initClickListener(view: View) {
        orderListingTopBN.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.custlistingsell -> {
                    addLayout.visibility=View.VISIBLE
                    listingLayout.visibility = View.GONE
                }
                R.id.custorderlisting -> {
                    addLayout.visibility=View.GONE
                    listingLayout.visibility = View.VISIBLE
                }
            }
            true
        }
    }

    private fun initViews(view: View) {
        cust_addproducts_recycler = view.findViewById(R.id.cust_addproducts_recycler)
        orderListingTopBN = view.findViewById(R.id.orderListingTopBN)

        addLayout = view.findViewById(R.id.add_products_layout)
        listingLayout = view.findViewById(R.id.cust_sell_list_layout)

    }
}