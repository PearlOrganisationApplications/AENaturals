package com.aenatural.aenaturals.salesmans.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.salesmans.BottomSectionAdapter

import com.aenatural.aenaturals.salesmans.MidSectionAdapter
import com.aenatural.aenaturals.salesmans.SecondBottomSectionAdapter
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.salesmans.SalesmanProfileActivity
import java.util.*
import kotlin.collections.ArrayList


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {


    lateinit var salesMidsectionRecylerView:RecyclerView
    lateinit var salesBottomRecylerView:RecyclerView
    lateinit var salessecondBottomRecylerView:RecyclerView
    lateinit var retailerList:ArrayList<RetailerDataModel>
    lateinit var addCustomers:TextView
    lateinit var card_moreaboutsales:CardView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initializeViews(view)
        initDataModels(view)
        initClickListeners(view)
        initrecyclerViews(view)
    }

    private fun initClickListeners(view: View) {
        card_moreaboutsales.setOnClickListener { startActivity(Intent(requireContext(),SalesmanProfileActivity::class.java))
        }

    }
    private fun initDataModels(view:View) {
        retailerList= ArrayList()
        for(i in 0..5)
            retailerList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))
    }

    private fun initrecyclerViews(view:View) {

        salesBottomRecylerView.adapter = BottomSectionAdapter(retailerList)
        salesBottomRecylerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)

        salessecondBottomRecylerView.adapter = MidSectionAdapter(retailerList)
        salessecondBottomRecylerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
    }

    private fun initializeViews(view: View) {

        salesMidsectionRecylerView = view.findViewById(R.id.salesMidsectionRecylerView)
        salesBottomRecylerView = view.findViewById(R.id.salesBottomRecylerView)
        salessecondBottomRecylerView = view.findViewById(R.id.salessecondBottomRecylerView)
        addCustomers = view.findViewById(R.id.addCustomers)
        card_moreaboutsales = view.findViewById(R.id.card_moreaboutsales)
    }

}