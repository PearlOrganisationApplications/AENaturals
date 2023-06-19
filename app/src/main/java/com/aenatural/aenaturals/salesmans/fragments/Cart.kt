package com.aenatural.aenaturals.salesmans.fragments

import android.app.Dialog
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.SearchView
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.salesmans.Adapters.CartListAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.salesmans.Adapters.CustomerAdapter
import com.aenatural.aenaturals.salesmans.Adapters.RequestProductsAdapter

class Cart : Fragment() {
    lateinit var cartrequestLayout: LinearLayout
    lateinit var cartsellLayout: LinearLayout
    lateinit var salesman_cart_select_customer: LinearLayout
    lateinit var salesman_cart_customer_top_layout: LinearLayout
    lateinit var cartsellRecycler: RecyclerView
    lateinit var cartrequestRecycler: RecyclerView
    lateinit var cartnavigation: BottomNavigationView
    lateinit var retailerList: ArrayList<RetailerDataModel>
    lateinit var selectCustomer: TextView
    lateinit var sells_select_customer_name: TextView
    lateinit var customDialog: Dialog
    lateinit var searchCustomer: SearchView
    lateinit var customerListRV: RecyclerView
    lateinit var doneCV: CardView
    lateinit var cancelTV: TextView
    lateinit var headerTV: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        return inflater.inflate(R.layout.fragment_cart, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        backPress()
        customDialog = Dialog(requireActivity())
        requireActivity().findViewById<LinearLayout>(R.id.include2).visibility = View.GONE
        initViews(view)
        initClickListener(view)
        initDataModels(view)
        initRecyclerAdapter(view)
    }

    private fun initRecyclerAdapter(view: View) {
        cartsellRecycler.adapter = CartListAdapter(retailerList)
        cartsellRecycler.layoutManager = LinearLayoutManager(requireContext())

        cartrequestRecycler.adapter = RequestProductsAdapter(retailerList, requireContext())
        cartrequestRecycler.layoutManager = LinearLayoutManager(requireContext())

    }

    private fun initDataModels(view: View) {
        retailerList = ArrayList()
        for (i in 0..5)
            retailerList.add(
                RetailerDataModel(
                    "Rajesh K",
                    "rajeshisamazing@gmail.com",
                    "RR Nagar Banglore",
                    "+9182384898"
                )
            )
    }

    private fun initClickListener(view: View) {
        cartnavigation.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.cartrequest -> {
                    cartrequestLayout.visibility = View.VISIBLE
                    cartsellLayout.visibility = View.GONE
                    headerTV.setText("Inventory")
                }
                R.id.cartsell -> {
                    cartrequestLayout.visibility = View.GONE
                    cartsellLayout.visibility = View.VISIBLE
                    headerTV.text = "Sell Item"
                }
            }
            true
        }
        cancelTV.setOnClickListener {
            customDialog.dismiss()
        }
        doneCV.setOnClickListener {
            customDialog.dismiss()
            sells_select_customer_name.text = "Customer Name"
//            salesman_cart_select_customer.visibility = View.GONE
//            salesman_cart_customer_top_layout.visibility = View.VISIBLE
        }
        selectCustomer.setOnClickListener {

//            salesman_cart_select_customer.visibility = View.VISIBLE
//            salesman_cart_customer_top_layout.visibility = View.GONE
//            customDialog.window?.attributes?.windowAnimations = R.style.animation
            customDialog.show()

            customerListRV.adapter = CustomerAdapter(retailerList)
            customerListRV.layoutManager = LinearLayoutManager(requireContext(),
                LinearLayoutManager.VERTICAL,false)
        }
    }

    private fun initViews(view: View) {
        cartrequestLayout = view.findViewById(R.id.cartrequestLayout)
        cartsellLayout = view.findViewById(R.id.cartsellLayout)
        sells_select_customer_name = view.findViewById(R.id.sells_select_customer_name)
        salesman_cart_select_customer = view.findViewById(R.id.salesman_cart_select_customer)
        salesman_cart_customer_top_layout = view.findViewById(R.id.salesman_cart_customer_top_layout)
        cartnavigation = view.findViewById(R.id.cartnavigation)
        cartsellRecycler = view.findViewById(R.id.cartsellRecycler)
        cartrequestRecycler = view.findViewById(R.id.cartrequestRecycler)
        selectCustomer = view.findViewById(R.id.selectCustomer)
        headerTV = view.findViewById(R.id.headerTV)

        customDialog.setContentView(R.layout.customer_select)
        customDialog.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.WRAP_CONTENT
        )
        searchCustomer = customDialog.findViewById(R.id.searchCustomer)
        customerListRV = customDialog.findViewById(R.id.customerListRV)
        cancelTV = customDialog.findViewById(R.id.cancelTV)
        doneCV = customDialog.findViewById(R.id.doneCV)
    }


    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.salesDashboardFrameLayout,
                            HomeFragment()
                        ).commit()
                }
            })
    }
}