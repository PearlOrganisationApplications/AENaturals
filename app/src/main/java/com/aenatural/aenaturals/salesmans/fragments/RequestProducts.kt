package com.aenatural.aenaturals.salesmans.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.salesmans.Adapters.RequestProductsAdapter
import com.pearl.aenaturals.R

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [RequestProducts.newInstance] factory method to
 * create an instance of this fragment.
 */
class RequestProducts : Fragment() {
    // TODO: Rename and change types of parameters
    private var param1: String? = null
    private var param2: String? = null

    lateinit var recyclerViewRequestProducts:RecyclerView
    lateinit var retailerList:ArrayList<RetailerDataModel>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            param1 = it.getString(ARG_PARAM1)
            param2 = it.getString(ARG_PARAM2)
        }
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_request_products, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initViews(view)
        initDataModels(view)
        initRecyclerAdapter(view)
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
    }

}