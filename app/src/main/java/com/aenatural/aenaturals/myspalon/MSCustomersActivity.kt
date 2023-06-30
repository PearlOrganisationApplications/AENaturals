package com.aenatural.aenaturals.myspalon

import android.content.Intent
import android.os.Bundle
import android.widget.Button
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.MSCustomerListApiService
import com.aenatural.aenaturals.apiservices.datamodels.Customer
import com.aenatural.aenaturals.apiservices.datamodels.CustomerListResponse
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.RetrofitClient
import com.aenatural.aenaturals.common.RetrofitClient.retrofit
import com.aenatural.aenaturals.myspalon.Adapter.CustomerParlourListAdapter
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class MSCustomersActivity : BaseClass() {
    lateinit var add_customers_btn:Button
    lateinit var session: Session
    lateinit var loadingDialogPB: DialogPB
    lateinit var customerRV: RecyclerView
    private val custList = ArrayList<Customer>()
    val customerProfileAdapter = CustomerParlourListAdapter(custList)
    private val mainScope = CoroutineScope(Dispatchers.Main)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
        loadingDialogPB.startLoadingDialog()
//        getCustomerList()
        getCustomers()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_mscustomers)
        birdTheme()
        session = Session(this)
        loadingDialogPB = DialogPB(this)
    }

    override fun initializeViews() {
        add_customers_btn = findViewById(R.id.add_customers_btn)
        customerRV = findViewById(R.id.customerRV)
    }

    override fun initializeClickListners() {
        add_customers_btn.setOnClickListener {
            startActivity(Intent(this,MSAddCustomers::class.java))
        }
    }

    override fun initializeInputs() {
    }

    override fun initializeLabels() {
    }
    /*private fun getCustomerList() {

        val apiService = retrofit.create(MSCustomerListApiService::class.java)
        val token = session.token // Replace YOUR_BEARER_TOKEN with the actual bearer token

        GlobalScope.launch(Dispatchers.Main) {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getCustomerList("Bearer $token")
                }

                if (response.isSuccessful) {
                    val customerResponse = response.body()
                    val customers = customerResponse?.customers
                    loadingDialogPB.dismissDialog()

                    // Handle the list of customers as needed
                    customers?.forEach { customer ->
                        val id = customer.id
                        val fullName = customer.full_name
                        val mobile = customer.mobile
                        val gender = customer.gender
                        val email = customer.email
                        val dob = customer.dob

                        // Perform desired operations with the customer data
                        // For example, you can update your UI or populate a list
                        val cItem = Customer("", "", "", fullName, mobile, "", "", "", "", "")
                        custList.add(cItem)
                    }
//                    runOnUiThread {
                        customerRV.layoutManager = LinearLayoutManager(this@MSCustomersActivity)
                        customerRV.adapter = customerProfileAdapter
//                    }

                } else {
                    // Handle the error case
                    val errorMessage = response.errorBody()?.string()
                    // Handle the error message appropriately
                    loadingDialogPB.dismissDialog()
                    printLogs("API Error", "failure", errorMessage)
                }
            } catch (e: Exception) {
                // Handle any exceptions that occur during the API call
                // Log the error or show an error message to the user
                loadingDialogPB.dismissDialog()
                printLogs("API Error", "failure", e.message ?: "Unknown error")
            }
        }
    }*/

    private fun getCustomers() {
        val apiService = retrofit.create(MSCustomerListApiService::class.java)
        val token = session.token // Replace YOUR_BEARER_TOKEN with the actual bearer token

        mainScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getCustomerList("Bearer $token")
                }

                if (response.isSuccessful) {
                    val customerResponse = response.body()
                    val customers = customerResponse?.customers
                    loadingDialogPB.dismissDialog()

                    // Handle the list of customers as needed
                    customers?.forEach { customer ->
                        val id = customer.id
                        val fullName = customer.full_name
                        val mobile = customer.mobile
                        val gender = customer.gender
                        val email = customer.email
                        val dob = customer.dob

                        // Perform desired operations with the customer data
                        // For example, you can update your UI or populate a list
                        val cItem = Customer("", "", "", fullName, mobile, "", "", "", "", "")
                        custList.add(cItem)
                    }

                    customerRV.layoutManager = LinearLayoutManager(this@MSCustomersActivity)
                    customerRV.adapter = customerProfileAdapter

                } else {
                    // Handle the error case
                    val errorMessage = response.errorBody()?.string()
                    // Handle the error message appropriately
                    loadingDialogPB.dismissDialog()
                    printLogs("API Error", "failure", errorMessage)
                }
            } catch (e: Exception) {
                // Handle any exceptions that occur during the API call
                // Log the error or show an error message to the user
                loadingDialogPB.dismissDialog()
                printLogs("API Error", "failure", e.message ?: "Unknown error")
            }
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

}