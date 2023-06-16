package com.aenatural.aenaturals.salesmans.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel

import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.salesmans.*
import java.util.*
import kotlin.collections.ArrayList

class HomeFragment : Fragment() {


    lateinit var salesMidsectionRecylerView:RecyclerView
    lateinit var salesBottomRecylerView:RecyclerView
    lateinit var salessecondBottomRecylerView:RecyclerView
    lateinit var retailerList:ArrayList<RetailerDataModel>
    lateinit var addCustomers:TextView
    lateinit var card_moreaboutsales:CardView
    lateinit var card_pendingOrder:CardView
    lateinit var card_pendingPayments:CardView
    lateinit var card_totalReturns:CardView

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
        requireActivity().findViewById<LinearLayout>(R.id.include2).visibility =View.VISIBLE
        super.onViewCreated(view, savedInstanceState)
        backPress()
        initializeViews(view)
        initDataModels(view)
        initClickListeners(view)
        initrecyclerViews(view)
    }

    private fun initClickListeners(view: View) {
        var args = Bundle()
        card_pendingOrder.setOnClickListener {

            args.putString("section","1")
            var frag = ProductsFragment()
            frag.arguments = args
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.salesDashboardFrameLayout,
                    frag
                ).commit()
        }
        card_pendingPayments.setOnClickListener {
            args.putString("section","2")
            var frag = ProductsFragment()
            frag.arguments = args
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.salesDashboardFrameLayout,
                    frag
                ).commit()
        }
        card_totalReturns.setOnClickListener {
            args.putString("section","3")
            var frag = ProductsFragment()
            frag.arguments = args
            requireActivity().supportFragmentManager.beginTransaction()
                .replace(R.id.salesDashboardFrameLayout,
                    frag
                ).commit()
        }
        card_moreaboutsales.setOnClickListener { startActivity(Intent(requireContext(),SaleDetailsActivity::class.java))
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
        card_pendingPayments = view.findViewById(R.id.card_pendingPayments)
        card_totalReturns = view.findViewById(R.id.card_totalReturns)
        card_pendingOrder = view.findViewById(R.id.card_pendingOrder)
    }
    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showExitConfirmationDialog()
            }
        })
    }


    private fun showExitConfirmationDialog() {
        val alertDialogBuilder = AlertDialog.Builder(requireContext())
        alertDialogBuilder.setTitle("Exit")
        alertDialogBuilder.setMessage("Do you want to exit the app?")
        alertDialogBuilder.setPositiveButton("Yes") { _, _ ->
            requireActivity().finish()
        }
        alertDialogBuilder.setNegativeButton("No", null)
        val alertDialog = alertDialogBuilder.create()
        alertDialog.show()
    }
}