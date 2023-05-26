package com.aenatural.aenaturals.customers.fragments

import android.app.Dialog
import android.os.Bundle
import android.view.*
import android.widget.LinearLayout
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.salesmans.Adapters.CartListAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView


class CustomerOrderFrag : Fragment() {

    lateinit var cust_addproducts_recycler: RecyclerView
    lateinit var orderListingTopBN: BottomNavigationView
    lateinit var addLayout: LinearLayout
    lateinit var listingLayout: ConstraintLayout
    lateinit var retailerList: ArrayList<RetailerDataModel>
    lateinit var cust_new_order_button: CardView
    lateinit var dialog: Dialog
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
        requireActivity().findViewById<LinearLayout>(R.id.include).visibility = View.GONE
        initViews(view)
        initClickListener(view)
        setDataModels()
        initRecyclerViews(view)
    }

    private fun initRecyclerViews(view: View) {
        cust_addproducts_recycler.adapter = CartListAdapter(retailerList)
        cust_addproducts_recycler.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun setDataModels() {
        retailerList = ArrayList()
        for (i in 0..5) retailerList.add(RetailerDataModel("Rajesh K",
            "rajeshisamazing@gmail.com",
            "RR Nagar Banglore",
            "+9182384898"))
    }

    private fun initClickListener(view: View) {
        orderListingTopBN.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.custlistingsell -> {
                    addLayout.visibility = View.VISIBLE
                    listingLayout.visibility = View.GONE
                }
                R.id.custorderlisting -> {
                    addLayout.visibility = View.GONE
                    listingLayout.visibility = View.VISIBLE
                }
            }
            true
        }

        cust_new_order_button.setOnClickListener {
            dialog.show()
        }

        dialog.findViewById<CardView>(R.id.add_customer_dialog_cancel).setOnClickListener {
            dialog.dismiss()
        }
        dialog.findViewById<CardView>(R.id.add_customer_dialog_add).setOnClickListener {
            dialog.dismiss()
        }

    }

    private fun initViews(view: View) {
        cust_addproducts_recycler = view.findViewById(R.id.cust_addproducts_recycler)
        orderListingTopBN = view.findViewById(R.id.orderListingTopBN)
        cust_new_order_button = view.findViewById(R.id.cust_new_order_button)
        addLayout = view.findViewById(R.id.add_products_layout)
        listingLayout = view.findViewById(R.id.cust_sell_list_layout)

        dialog = Dialog(requireContext())
        dialog.setContentView(R.layout.addcustomer_dialog)
        val layoutParams = WindowManager.LayoutParams()
        val window: Window? = dialog.window
        if (window != null) {
            layoutParams.copyFrom(window.getAttributes())
            layoutParams.width = WindowManager.LayoutParams.MATCH_PARENT
            window.setAttributes(layoutParams)
        }
    }
}