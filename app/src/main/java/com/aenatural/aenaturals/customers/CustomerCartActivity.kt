package com.aenatural.aenaturals.customers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.CartItemApiService
import com.aenatural.aenaturals.apiservices.datamodels.CartItem
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.common.RetrofitClient.mainScope
import com.aenatural.aenaturals.common.RetrofitClient.retrofit
import com.aenatural.aenaturals.customers.adapters.CustomerCartListAdapter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class CustomerCartActivity : BaseClass() {

    lateinit var customer_cart_recyclerview:RecyclerView
    lateinit var itemList: ArrayList<CartItem>
    lateinit var retailerList: ArrayList<CartItem>
    lateinit var session: Session
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
//        initData()
        initializeInputs()
//        initializeLabels()

        loadCartList()

    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_customer_cart)
        getMidGreentheme()
        session = Session(this)
    }

    override fun initializeViews() {
        customer_cart_recyclerview = findViewById(R.id.customer_cart_recyclerview)
    }

    override fun initializeClickListners() {
    }

    override fun initializeInputs() {
    }

    override fun initializeLabels() {
        customer_cart_recyclerview.adapter = CustomerCartListAdapter(retailerList,"")

        customer_cart_recyclerview.layoutManager= LinearLayoutManager(this)
    }

    /*private fun initData(){
        retailerList= ArrayList()
        for(i in 0..5)
            retailerList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))
    }*/

    private fun loadCartList() {
        val apiService = retrofit.create(CartItemApiService::class.java)

        val tkn = session.token

        mainScope.launch {
            try {
                  val response = withContext(Dispatchers.IO){
                 apiService.getViewCartItems("Bearer $tkn")
             }
                if (response.isSuccessful) {
                    val viewCartItemResponse = response.body()
                    if (viewCartItemResponse != null) {
                        // Handle the response here
                        val status = viewCartItemResponse.status
                        val carts = viewCartItemResponse.carts
                        val imageEndpoint = viewCartItemResponse.image_endpoint
                        printLogs("loadCartList","isSuccessful",status +"  "+ viewCartItemResponse.message)
                        // Process the cart items
                        for (cartItem in carts) {
                            val prodId = cartItem.prod_id
                            val prodName = cartItem.prod_name
                            // ... and so on for other properties
                        }
                    }
                } else {
                    // Handle error case
                    val errorBody = response.errorBody().toString()
                    // Handle the error body if needed
                    printLogs("loadCartList","error",errorBody)
                }
            } catch (e: Exception) {
                // Handle exception
                e.printStackTrace()
            }
        }
    }
}