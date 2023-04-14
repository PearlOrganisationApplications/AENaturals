package com.aenatural.aenaturals.salesmans.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.salesmans.BottomSectionAdapter
import com.aenatural.aenaturals.salesmans.MidSectionAdapter
import com.aenatural.aenaturals.salesmans.SecondBottomSectionAdapter
import com.pearl.aenaturals.R
import java.util.*
import kotlin.collections.ArrayList


private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"


class HomeFragment : Fragment() {

    private var param1: String? = null
    private var param2: String? = null

    lateinit var salesMidsectionRecylerView:RecyclerView
    lateinit var salesBottomRecylerView:RecyclerView
    lateinit var salessecondBottomRecylerView:RecyclerView
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
        return inflater.inflate(R.layout.fragment_home, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        
        initializeViews(view)
        initDataModels(view)
        initrecyclerViews(view)
    }

    private fun initDataModels(view:View) {
        retailerList= ArrayList()
        for(i in 0..5)
            retailerList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))
    }

    private fun initrecyclerViews(view:View) {

        salesMidsectionRecylerView.adapter = MidSectionAdapter(retailerList)
        salesMidsectionRecylerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)

        Timer().schedule(object : TimerTask() {
            override fun run() {
                if ((salesMidsectionRecylerView.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < retailerList.size - 1) {
                    salesMidsectionRecylerView.layoutManager!!.smoothScrollToPosition(
                        salesMidsectionRecylerView,
                        RecyclerView.State(),
                        (salesMidsectionRecylerView.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() + 1
                    )
                } else if ((salesMidsectionRecylerView.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < retailerList.size - 1) {
                    salesMidsectionRecylerView.layoutManager!!.smoothScrollToPosition(salesMidsectionRecylerView, RecyclerView.State(), 0)
                }else{
                    salesMidsectionRecylerView.smoothScrollToPosition(0);
                }
            }
        },0, 1500)

        salesBottomRecylerView.adapter = BottomSectionAdapter(retailerList)
        salesBottomRecylerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.HORIZONTAL,false)

        salessecondBottomRecylerView.adapter = SecondBottomSectionAdapter(retailerList)
        salessecondBottomRecylerView.layoutManager = LinearLayoutManager(requireContext(),
            LinearLayoutManager.VERTICAL,false)
    }

    private fun initializeViews(view: View) {
       
        salesMidsectionRecylerView = view.findViewById(R.id.salesMidsectionRecylerView)
        salesBottomRecylerView = view.findViewById(R.id.salesBottomRecylerView)
        salessecondBottomRecylerView = view.findViewById(R.id.salessecondBottomRecylerView)
    }


}