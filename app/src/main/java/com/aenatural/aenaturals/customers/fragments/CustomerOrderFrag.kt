package com.aenatural.aenaturals.customers.fragments

import android.app.Dialog
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.CheckoutApiService
import com.aenatural.aenaturals.apiservices.OrderHistoryApiService
import com.aenatural.aenaturals.apiservices.datamodels.CustomerProductListDM
import com.aenatural.aenaturals.apiservices.datamodels.CustomerProductListService
import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.common.RetrofitClient
import com.aenatural.aenaturals.common.RetrofitClient.mainScope
import com.aenatural.aenaturals.common.RetrofitClient.retrofit
import com.aenatural.aenaturals.salesmans.Adapters.CartListAdapter
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONArray
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class CustomerOrderFrag : Fragment() {

    lateinit var cust_addproducts_recycler: RecyclerView
    lateinit var orderListingTopBN: BottomNavigationView
    lateinit var addLayout: LinearLayout
    lateinit var listingLayout: ConstraintLayout
    lateinit var retailerList: ArrayList<RetailerDataModel>
    lateinit var cust_new_order_button: CardView
    lateinit var dialog: Dialog
    lateinit var session: Session
    var customerName =""
    var customerMobile =""
    private val selectedItemsList = mutableListOf<JSONObject>()
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

        backPress()
        requireActivity().findViewById<LinearLayout>(R.id.include).visibility = View.GONE
        initViews(view)
        initClickListener(view)
        initRecyclerViews(view)
        session = Session(requireContext())
        listproductapi()
    }

    private fun listproductapi() {
var apiservice = RetrofitClient.retrofit.create(CustomerProductListService::class.java)
        val call: Call<CustomerProductListDM> = apiservice.productList("Bearer ${session.token}")
        call.enqueue(object:Callback<CustomerProductListDM>{
            override fun onResponse(
                call: Call<CustomerProductListDM>,
                response: Response<CustomerProductListDM>
            ) {
                if(response.isSuccessful){
                    var data = response.body()
                    if (data != null) {
                        if(data.status.equals("true"))
                            cust_addproducts_recycler.adapter = CartListAdapter(data.products,selectedItemsList)
                    }
                    cust_addproducts_recycler.layoutManager = LinearLayoutManager(requireContext())
                }
            }

            override fun onFailure(call: Call<CustomerProductListDM>, t: Throwable) {

            }

        })
    }

    private fun initRecyclerViews(view: View) {

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
            customerName = dialog.findViewById<EditText>(R.id.dialog_cust_name).text.toString()
            customerMobile = dialog.findViewById<EditText>(R.id.dialog_cust_number).text.toString()
            callSubmitApi()
            dialog.dismiss()
        }
        dialog.findViewById<CardView>(R.id.add_customer_dialog_add).setOnClickListener {
            dialog.dismiss()
        }

    }

    private fun callSubmitApi() {
        val apiService = RetrofitClient.retrofit.create(CheckoutApiService::class.java)



        val jsonList = JSONObject()
        jsonList.put("name", customerName)
        jsonList.put("contact", customerMobile)
        jsonList.put("products", JSONArray(selectedItemsList))

        val requestBody = jsonList.toString().toRequestBody("application/json".toMediaType())


        val call:Call<NormalDataModel> = apiService.checkout("Bearer "+session.token.toString(),requestBody)

        call.enqueue(object:Callback<NormalDataModel> {
            override fun onResponse(
                call: Call<NormalDataModel>,
                response: Response<NormalDataModel>
            ) {
                if(response.isSuccessful){
                    try {
                        Log.d("SellItemResp",response.body().toString())
                        Toast.makeText(requireContext(),"Sucessfully Added",Toast.LENGTH_SHORT)

                    }catch (_:Exception) {
                    }

                }
                else{
                    Log.d("SellItemError",response.errorBody().toString())
                }
            }

            override fun onFailure(call: Call<NormalDataModel>, t: Throwable) {

            }
        })
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

    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(viewLifecycleOwner, object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.cust_home_frame,
                        CustomerHomeFrag()
                    ).commit()
            }
        })
    }

}