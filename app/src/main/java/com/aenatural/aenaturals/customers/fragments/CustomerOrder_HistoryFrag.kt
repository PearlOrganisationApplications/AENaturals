package com.aenatural.aenaturals.customers.fragments

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.activity.OnBackPressedCallback
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.OrderHistoryApiService
import com.aenatural.aenaturals.apiservices.datamodels.Order
import com.aenatural.aenaturals.apiservices.datamodels.OrderHistoryResponse
import com.aenatural.aenaturals.apiservices.datamodels.SellHistoryResponse
import com.aenatural.aenaturals.apiservices.datamodels.SoldItem
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.Models.SellerDataModel
import com.aenatural.aenaturals.common.RetrofitClient
import com.aenatural.aenaturals.common.RetrofitClient.mainScope
import com.aenatural.aenaturals.common.RetrofitClient.retrofit
import com.aenatural.aenaturals.customers.adapters.CustomerOrderHistoryAdapter
import com.aenatural.aenaturals.customers.adapters.CustomerSaleHistoryAdapter
import com.aenatural.aenaturals.distributors.fragments.DistributorHomeFrag
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomerOrder_HistoryFrag : Fragment() {

    lateinit var pendingPayment: ArrayList<SellerDataModel>
    lateinit var customer_history_recyler: RecyclerView
    lateinit var customer_history_topnav: BottomNavigationView
    lateinit var customer_sellhistory_layout: LinearLayout
    lateinit var customer_order_history_layout: LinearLayout
    lateinit var customer_order_history_recycler: RecyclerView
    lateinit var session: Session
    lateinit var orders: List<Order>
    lateinit var sold: ArrayList<SoldItem>
    var LogString: kotlin.String? = null
    lateinit var loadingDialogPB: DialogPB

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        return inflater.inflate(R.layout.fragment_customer_order_frag, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        session = Session(context)
        loadingDialogPB = DialogPB(requireActivity())
        backPress()
        requireActivity().findViewById<LinearLayout>(R.id.include).visibility = View.VISIBLE
        initializeViews(view)
        topnav(view)
        fetchOrderHistory()
        fetchSellHistory()
    }

    private fun topnav(view: View) {
        customer_history_topnav.setOnItemSelectedListener {
            when (it.itemId) {
                R.id.menu_sell_history -> {
                    customer_sellhistory_layout.visibility = View.VISIBLE
                    customer_order_history_layout.visibility = View.GONE
                }
                R.id.menu_order_history -> {
                    customer_sellhistory_layout.visibility = View.GONE
                    customer_order_history_layout.visibility = View.VISIBLE

                }
            }
            true
        }
    }

    private fun initializeViews(view: View) {
        customer_history_recyler = view.findViewById(R.id.customer_history_recyler);
        customer_history_topnav = view.findViewById(R.id.customer_history_topnav)
        customer_sellhistory_layout = view.findViewById(R.id.customer_sellhistory_layout)
        customer_order_history_layout = view.findViewById(R.id.customer_order_history_layout)
        customer_order_history_recycler = view.findViewById(R.id.customer_order_history_recycler)
        initRecyclerView(view)
    }

    private fun initRecyclerView(view: View) {
        initData()
       /* customer_history_recyler.adapter = CustomerSaleHistoryAdapter(pendingPayment)
        customer_history_recyler.layoutManager = LinearLayoutManager(requireContext())*/

        /*customer_order_history_recycler.adapter = CustomerOrderHistoryAdapter(pendingPayment)
        customer_order_history_recycler.layoutManager = LinearLayoutManager(requireContext())*/
    }

    private fun initData() {
        pendingPayment = ArrayList()
        for (i in 0..5) {
            pendingPayment.add(SellerDataModel("", "", "", "", ""))
        }
    }

    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {
                    requireActivity().supportFragmentManager.beginTransaction()
                        .replace(
                            R.id.cust_home_frame,
                            CustomerHomeFrag()
                        ).commit()
                }
            })
    }


    private fun fetchOrderHistory() {
//        loadingDialogPB.startLoadingDialog()
        val apiService = retrofit.create(OrderHistoryApiService::class.java)
        val tkn = session.token
        printLogs("fetchOrderHistory", "fetchOrderHistory", "Function ran")

        try {
            val call = apiService.getOrderHistory("Bearer $tkn")
            call.enqueue(object : Callback<OrderHistoryResponse> {
                override fun onResponse(
                    call: Call<OrderHistoryResponse>,
                    response: Response<OrderHistoryResponse>
                ) {
                    if (response.isSuccessful) {
                        val orderHistoryResponse = response.body()
                        if (orderHistoryResponse != null) {
                            loadingDialogPB.dismissDialog()
                            // Handle the response here
                            val status = orderHistoryResponse.status
                            val orderHistory = orderHistoryResponse.history
                            val imageEndpoint = orderHistoryResponse.image_endpoint
                            printLogs(
                                "orderHistoryResponse",
                                "empty",
                                orderHistoryResponse.toString()
                            )
                            // Update the UI with order history data
//                        updateOrderHistoryUI(orders)
//                        if (status.equals("true")) {

                            try {
                                orders = orderHistory.orders
                                if (orders.isEmpty()) {
                                    printLogs(
                                        "getOrderHistory",
                                        "empty",
                                        "No orders found in history"
                                    )
                                    loadingDialogPB.showErrorBottomSheetDialog("No orders found in history")
                                } else {
                                    customer_order_history_recycler.adapter =
                                        CustomerOrderHistoryAdapter(
                                            orders as ArrayList<Order>, imageEndpoint
                                        )
                                    customer_order_history_recycler.layoutManager =
                                        LinearLayoutManager(requireContext())
                                }
                            } catch (_: Exception) {

                            }

//                        }
                        }
                    } else {
                        // Handle error case
                        loadingDialogPB.dismissDialog()
                        val errorBody = response.errorBody().toString()
                        // Handle the error body if needed
                        printLogs("fetchOrderHistory", "error", errorBody)
                    }
                }

                override fun onFailure(call: Call<OrderHistoryResponse>, t: Throwable) {
                }
            })
        } catch (e: Exception) {
            // Handle exception
            loadingDialogPB.dismissDialog()
            e.printStackTrace()
        }
    }


    private fun fetchSellHistory() {
        val apiService = retrofit.create(OrderHistoryApiService::class.java)
        val tkn = session.token

        val call = apiService.getSellHistory("Bearer $tkn")
        call.enqueue(object : Callback<SellHistoryResponse> {
            override fun onResponse(call: Call<SellHistoryResponse>, response: Response<SellHistoryResponse>) {
                if (response.isSuccessful) {
                    val sellHistoryResponse = response.body()
                    if (sellHistoryResponse != null) {
                        // Handle the response here
                        val status = sellHistoryResponse.status
                        val sellHistory = sellHistoryResponse.history
                        val imageEndpoint = sellHistoryResponse.image_endpoint


                        // Update the UI with sell history data
                        try {
                            sold = sellHistory.sold as ArrayList<SoldItem>
                            if (sold.isEmpty()) {
                                printLogs(
                                    "getOrderHistory",
                                    "empty",
                                    "No orders found in history"
                                )
                                loadingDialogPB.showErrorBottomSheetDialog("No orders found in history")
                            }else{
                                customer_history_recyler.adapter = CustomerSaleHistoryAdapter(sold,imageEndpoint)
                                customer_history_recyler.layoutManager = LinearLayoutManager(requireContext())
                            }
                        } catch (_: Exception) {

                        }
                    }
                } else {
                    // Handle error case
                    val errorBody = response.errorBody().toString()
                    // Handle the error body if needed
                    printLogs("fetchSellHistory", "error", errorBody)
                }
            }

            override fun onFailure(call: Call<SellHistoryResponse>, t: Throwable) {
                // Handle failure case
                t.printStackTrace()
            }
        })
    }


    fun printLogs(tag: String, funcs: String, msg: String) {
        Log.i("OSG-" + tag + "__" + funcs, msg)
    }

}