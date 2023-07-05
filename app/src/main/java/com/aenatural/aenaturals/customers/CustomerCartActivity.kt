package com.aenatural.aenaturals.customers

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.CartItemApiService
import com.aenatural.aenaturals.apiservices.CheckoutApiService
import com.aenatural.aenaturals.apiservices.datamodels.CartItem
import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.common.RetrofitClient
import com.aenatural.aenaturals.common.RetrofitClient.mainScope
import com.aenatural.aenaturals.common.RetrofitClient.retrofit
import com.aenatural.aenaturals.customers.adapters.CustomerCartListAdapter

import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class CustomerCartActivity : BaseClass() {

    lateinit var customer_cart_recyclerview:RecyclerView
    lateinit var itemList: ArrayList<CartItem>
    lateinit var retailerList: ArrayList<CartItem>
    lateinit var session: Session
    lateinit var customer_chekout_button:TextView
    lateinit var loadingDialogPB: DialogPB
    private val selectedItemsList = mutableListOf<JSONObject>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
        initializeInputs()
        loadCartList()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_customer_cart)
        getMidGreentheme()
        session = Session(this)
        loadingDialogPB = DialogPB(this)
    }

    override fun initializeViews() {
        customer_cart_recyclerview = findViewById(R.id.customer_cart_recyclerview)
        customer_chekout_button = findViewById(R.id.customer_chekout_button)
    }

    override fun initializeClickListners() {
        customer_chekout_button.setOnClickListener {
        logHandler("ItemList",selectedItemsList.toString())
            hitcheckoutApi()
//            recreate()
        }
    }

    private fun hitcheckoutApi() {
        loadingDialogPB.startLoadingDialog()
        val apiService = retrofit.create(CheckoutApiService::class.java)


        val mediaType = "application/json".toMediaType()
        val requestBody = selectedItemsList.toString().toRequestBody(mediaType)

        val call:Call<NormalDataModel> = apiService.checkout("Bearer "+session.token.toString(),requestBody)

        call.enqueue(object:Callback<NormalDataModel>{
            override fun onResponse(
                call: Call<NormalDataModel>,
                response: Response<NormalDataModel>
            ) {
                if(response.isSuccessful){
                    loadingDialogPB.dismissDialog()
                    val data = response.body()
                    logHandler("CheckoutRes",data.toString())

                }else{
                    loadingDialogPB.dismissDialog()
                    val statusCode = response.code()
                    val errorBody = response.errorBody()?.string()
                    logHandler("CheckoutRes", "Response not successful. Status code: $statusCode, Error body: $errorBody")
                }
            }
            override fun onFailure(call: Call<NormalDataModel>, t: Throwable) {
                logHandler("CheckoutRes",t.toString())
            }
        })

    }

    override fun initializeInputs() {
    }

    override fun initializeLabels() {
        /*customer_cart_recyclerview.adapter = CustomerCartListAdapter(retailerList,"")

        customer_cart_recyclerview.layoutManager= LinearLayoutManager(this)*/
    }

    /*private fun initData(){
        retailerList= ArrayList()
        for(i in 0..5)
            retailerList.add(RetailerDataModel("Rajesh K","rajeshisamazing@gmail.com","RR Nagar Banglore","+9182384898"))
    }*/

    private fun loadCartList() {
//        loadingDialog.startLoadingDialog()
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

                        /*           for (cartItem in carts) {
                            val prodId = cartItem.prod_id
                            val prodName = cartItem.prod_name
                            val prodPrice = cartItem.pro_price
                            val prodDescription = cartItem.prod_description
                            // ... and so on for other properties
                        }*/
                            if (status.equals("true")) {
                                retailerList = carts
                                try {
                                    customer_cart_recyclerview.adapter = CustomerCartListAdapter(
                                        retailerList, imageEndpoint,selectedItemsList
                                    )
                                    customer_cart_recyclerview.layoutManager =
                                        LinearLayoutManager(this@CustomerCartActivity)
                                } catch (_: Exception) {
                                }
                            }
                    }
                } else {
                    val errorBody = response.errorBody().toString()
                    val error = response.body()?.message
                    printLogs("loadCartList","error",errorBody + "__" + response.message() + "__" + response.body()?.status + "__" + error)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }
}