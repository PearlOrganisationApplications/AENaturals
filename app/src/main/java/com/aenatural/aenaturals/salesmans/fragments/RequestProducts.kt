package com.aenatural.aenaturals.salesmans.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.salesmans.Adapters.RequestProductsAdapter
import com.pearl.aenaturals.R


class RequestProducts : Fragment() {

    lateinit var recyclerViewRequestProducts:RecyclerView
    lateinit var retailerList:ArrayList<RetailerDataModel>
    lateinit var requestproductscheckout:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_request_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initClickListener(view)
        initDataModels(view)
        initRecyclerAdapter(view)
    }

    private fun initClickListener(view: View) {
        requestproductscheckout.setOnClickListener {

        }

    }

    private fun initRecyclerAdapter(view: View) {
        recyclerViewRequestProducts.adapter = RequestProductsAdapter(retailerList,requireContext())
        recyclerViewRequestProducts.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun initDataModels(view: View) {
        retailerList= ArrayList()
        for(i in 0..5)
            retailerList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))
    }

    private fun initViews(view: View) {
        recyclerViewRequestProducts = view.findViewById(R.id.recyclerViewRequestProducts)
        requestproductscheckout = view.findViewById(R.id.requestproductscheckout)
    }

}