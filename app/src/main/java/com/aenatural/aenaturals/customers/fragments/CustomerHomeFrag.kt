package com.aenatural.aenaturals.customers.fragments

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.EditorInfo
import android.widget.EditText
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.customers.adapters.CustomerAllItemAdapter
import com.aenatural.aenaturals.customers.CustomerTrendingAdapter
import com.aenatural.aenaturals.customers.adapters.SkincareAdapter
import java.util.*

class CustomerHomeFrag : Fragment() {
    lateinit var customerTrendingRecyclerView: RecyclerView
    lateinit var customerallItemsRecycler: RecyclerView
    lateinit var itemList: java.util.ArrayList<RetailerDataModel>
    lateinit var skincare: LinearLayout
    lateinit var haircare: LinearLayout
    lateinit var herbalPowder: LinearLayout
    lateinit var nutritional: LinearLayout
    lateinit var personalCare: LinearLayout
    private lateinit var aromaPowders: LinearLayout
    lateinit var essentialOils: LinearLayout
    lateinit var customerSkincareRV: RecyclerView
    lateinit var customerHaircareRV: RecyclerView
    lateinit var customerHerbalPowderRV: RecyclerView
    lateinit var customerNutritionalRV: RecyclerView
    lateinit var customerPersonalCareRV: RecyclerView
    lateinit var customerAromaPowdersRV: RecyclerView
    lateinit var customerEssentialOilsRV: RecyclerView
    lateinit var searchEditText : EditText
    lateinit var trendingLayout : LinearLayout
    lateinit var shopNowLL : LinearLayout
    lateinit var shopNowLayout : LinearLayout
    private lateinit var searchButton: ImageView

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
        backPress()
        requireActivity().findViewById<LinearLayout>(R.id.include).visibility =View.VISIBLE
        initializeViews(view)
        initializeClickListners()
        initializeLabels()
        initializeInputs()
    }

    public fun initializeViews(view: View) {
        customerTrendingRecyclerView = view.findViewById(R.id.customerTrendingRecyclerView)
        customerallItemsRecycler = view.findViewById(R.id.customerallItemsRecycler)
        skincare = view.findViewById(R.id.skincare)
        haircare = view.findViewById(R.id.haircare)
        herbalPowder = view.findViewById(R.id.herbalPowder)
        nutritional = view.findViewById(R.id.nutritional)
        personalCare = view.findViewById(R.id.personalCare)
        aromaPowders = view.findViewById(R.id.aromaPowders)
        essentialOils = view.findViewById(R.id.essentialOils)
        customerSkincareRV = view.findViewById(R.id.customerSkincareRV)
        customerHaircareRV = view.findViewById(R.id.customerHaircareRV)
        customerHerbalPowderRV = view.findViewById(R.id.customerHerbalPowderRV)
        customerNutritionalRV = view.findViewById(R.id.customerNutritionalRV)
        customerPersonalCareRV = view.findViewById(R.id.customerPersonalCareRV)
        customerAromaPowdersRV = view.findViewById(R.id.customerAromaPowdersRV)
        customerEssentialOilsRV = view.findViewById(R.id.customerEssentialOilsRV)
        trendingLayout  = view.findViewById(R.id.trendingLayout)
        searchEditText   = view.findViewById(R.id.searchEditText)
        shopNowLL   = view.findViewById(R.id.shopNowLL)
    }

    public fun initializeClickListners() {

        skincare.setOnClickListener {
            customerallItemsRecycler.visibility = View.GONE
            customerSkincareRV.visibility = View.VISIBLE
        }
        haircare.setOnClickListener {

        }
        herbalPowder.setOnClickListener {

        }
        nutritional.setOnClickListener {

        }
        personalCare.setOnClickListener {

        }
        aromaPowders.setOnClickListener {

        }
        essentialOils.setOnClickListener {

        }

        searchEditText.addTextChangedListener(object : TextWatcher {
            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {

            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
                val searchText = s?.trim().toString()
                if (searchText.isNotEmpty()) {
                    trendingLayout.visibility = View.GONE
                    shopNowLL.visibility = View.GONE
                } else {
                    trendingLayout.visibility = View.VISIBLE
                    shopNowLL.visibility = View.VISIBLE
                }
            }

            override fun afterTextChanged(s: Editable?) {}
        })
        // Set a listener for keyboard search action

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

        customerSkincareRV.adapter = SkincareAdapter(itemList)
        customerSkincareRV.layoutManager = LinearLayoutManager(requireContext())
    }


    private fun initData(){
        itemList= java.util.ArrayList()
        for(i in 0..5)
            itemList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))
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

    /*private fun performSearch() {
        val query = searchEditText.text.toString()
        // Perform the search operation with the query
        if (query.isNotEmpty()) {
            // Iterate through the items in the shop now layout and filter based on the search query
            for (i in 0 until shopNowLayout.childCount) {
                val childView = shopNowLayout.getChildAt(i)
                if (childView is TextView) {
                    val itemName = childView.text.toString()
                    if (itemName.contains(query, ignoreCase = true)) {
                        // Item matches the search query, show it or perform relevant actions
                        childView.visibility = View.VISIBLE
                    } else {
                        // Item does not match the search query, hide it or perform relevant actions
                        childView.visibility = View.GONE
                    }
                }
            }
        } else {
            // Search query is empty, show all items in the shop now layout
            for (i in 0 until shopNowLayout.childCount) {
                val childView = shopNowLayout.getChildAt(i)
                childView.visibility = View.VISIBLE
            }
        }
    }*/
}