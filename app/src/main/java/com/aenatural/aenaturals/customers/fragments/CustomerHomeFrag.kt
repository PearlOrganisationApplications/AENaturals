package com.aenatural.aenaturals.customers.fragments

import android.content.Intent
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
import com.aenatural.aenaturals.customers.adapters.CustomerAllItemAdapter
import com.aenatural.aenaturals.customers.CustomerProfileActivity
import com.aenatural.aenaturals.customers.CustomerTrendingAdapter
import java.util.*

class CustomerHomeFrag : Fragment() {
    lateinit var customerTrendingRecyclerView: RecyclerView
    lateinit var customerallItemsRecycler: RecyclerView
    lateinit var itemList: java.util.ArrayList<RetailerDataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {

        return inflater.inflate(R.layout.fragment_customer_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        requireActivity().findViewById<LinearLayout>(R.id.include).visibility =View.VISIBLE
        initializeViews(view)
        initializeClickListners()
        initializeLabels()
        initializeInputs()
    }

    public fun initializeViews(view: View) {
        customerTrendingRecyclerView = view.findViewById(R.id.customerTrendingRecyclerView)
        customerallItemsRecycler = view.findViewById(R.id.customerallItemsRecycler)
    }

    public fun initializeClickListners() {

    }

    public fun initializeInputs() {

    }

    public fun initializeLabels() {
        initData()
        customerTrendingRecyclerView.adapter = CustomerTrendingAdapter(itemList)
        customerTrendingRecyclerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)
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
        customerallItemsRecycler.layoutManager = LinearLayoutManager(requireContext())
    }


    private fun initData(){
        itemList= java.util.ArrayList()
        for(i in 0..5)
            itemList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))
    }

}