package com.aenatural.aenaturals.customers.fragments

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import androidx.activity.OnBackPressedCallback
import androidx.appcompat.app.AlertDialog
import androidx.core.view.GravityCompat
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.CustomerAddCartService
import com.aenatural.aenaturals.apiservices.GetProductCategoriesService
import com.aenatural.aenaturals.apiservices.MSGetProfileApiService
import com.aenatural.aenaturals.apiservices.ProductApiService
import com.aenatural.aenaturals.apiservices.datamodels.*
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.Login
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.common.RetrofitClient
import com.aenatural.aenaturals.common.RetrofitClient.mainScope
import com.aenatural.aenaturals.common.RetrofitClient.retrofit
import com.aenatural.aenaturals.customers.CustomerTrendingAdapter
import com.aenatural.aenaturals.customers.adapters.CustomerAllItemAdapter
import com.aenatural.aenaturals.customers.adapters.ProductCategoryAdapter
import com.aenatural.aenaturals.customers.adapters.SkincareAdapter
import com.aenatural.aenaturals.myspalon.*
import kotlinx.coroutines.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.create
import java.util.*
import kotlin.collections.ArrayList

class CustomerHomeFrag : Fragment(), ProductCategoryAdapter.AdapterCallback,CustomerAllItemAdapter.CustomerAdapterCallBack {
    lateinit var customerTrendingRecyclerView: RecyclerView
    lateinit var customerallItemsRecycler: RecyclerView
    lateinit var itemList: java.util.ArrayList<RetailerDataModel>

    private val job = Job()
    private val coroutineScope = CoroutineScope(Dispatchers.Main + job)

//    lateinit var skincare: LinearLayout
//    lateinit var haircare: LinearLayout
//    lateinit var herbalPowder: LinearLayout
//    lateinit var nutritional: LinearLayout
//    lateinit var personalCare: LinearLayout
//    private lateinit var aromaPowders: LinearLayout
//    lateinit var essentialOils: LinearLayout

    lateinit var customerSkincareRV: RecyclerView
    lateinit var customerHaircareRV: RecyclerView
    lateinit var customerHerbalPowderRV: RecyclerView
    lateinit var customerNutritionalRV: RecyclerView
    lateinit var customerPersonalCareRV: RecyclerView
    lateinit var customerAromaPowdersRV: RecyclerView
    lateinit var customerEssentialOilsRV: RecyclerView

    lateinit var product_category_recycler_view: RecyclerView
    lateinit var searchEditText: EditText
    lateinit var trendingLayout: LinearLayout
    lateinit var shopNowLL: LinearLayout
    lateinit var shopNowLayout: LinearLayout
    private lateinit var searchButton: ImageView
    lateinit var session: Session

    lateinit var ms_myprofile: LinearLayout
    lateinit var ms_home_beauticians: LinearLayout
    lateinit var ms_home_customers: LinearLayout
    lateinit var ms_home_invoice: LinearLayout
    lateinit var ms_home_services: LinearLayout
    lateinit var ms_home_calendar: LinearLayout
    lateinit var loadingDialog: DialogPB
    lateinit var categories: List<Category>
    lateinit var categoryProduct: List<CategoriesProduct>


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
        requireActivity().findViewById<LinearLayout>(R.id.include).visibility = View.VISIBLE
        session = Session(requireContext())
        initializeViews(view)
        initializeClickListners()
        initializeLabels()
        initializeInputs()
        getProfileResponse()
        hitgetcategoryApi()
        getProductCategory()
    }

    private fun hitgetcategoryApi() {
        loadingDialog.startLoadingDialog()
        var categoryapiService = retrofit.create(GetProductCategoriesService::class.java)
        var call: Call<GetCategoriesDM> =
            categoryapiService.getCategories("Bearer " + session.token)
        call.enqueue(object : Callback<GetCategoriesDM> {
            override fun onResponse(
                call: Call<GetCategoriesDM>,
                response: Response<GetCategoriesDM>
            ) {
                loadingDialog.dismissDialog()
                var data = response.body()
                if (response.isSuccessful) {
                    logHandler("CategoryRes", response.body().toString())
                    if (data != null) {
                        if (data.status.equals("true")) {
                            try{
                                categories = data.categories
                                product_category_recycler_view.adapter = ProductCategoryAdapter(
                                    requireContext(),
                                    categories,
                                    data.image_endpoint,
                                    this@CustomerHomeFrag
                                )

                            }
                            catch (_:Exception){

                            }

                            try {
                                product_category_recycler_view.layoutManager = LinearLayoutManager(
                                    requireContext(),
                                    LinearLayoutManager.HORIZONTAL,
                                    false
                                )

                            } catch (_: Exception) {

                            }
                        } else {

                        }
                    }
                }
            }

            override fun onFailure(call: Call<GetCategoriesDM>, t: Throwable) {
                loadingDialog.dismissDialog()
                logHandler("CategoryFailureRes", t.message.toString())
            }

        })
    }

    public fun initializeViews(view: View) {
        mshomeInitViews(view)
        customerallItemsRecycler = view.findViewById(R.id.customerallItemsRecycler)

/*      customerSkincareRV = view.findViewById(R.id.customerSkincareRV)
        customerHaircareRV = view.findViewById(R.id.customerHaircareRV)
        customerHerbalPowderRV = view.findViewById(R.id.customerHerbalPowderRV)
        customerNutritionalRV = view.findViewById(R.id.customerNutritionalRV)
        customerPersonalCareRV = view.findViewById(R.id.customerPersonalCareRV)
        customerAromaPowdersRV = view.findViewById(R.id.customerAromaPowdersRV)*/

        product_category_recycler_view = view.findViewById(R.id.product_category_recycler_view)

//        customerEssentialOilsRV = view.findViewById(R.id.customerEssentialOilsRV)
        trendingLayout = view.findViewById(R.id.trendingLayout)
        searchEditText = view.findViewById(R.id.searchEditText)
        shopNowLL = view.findViewById(R.id.shopNowLL)
    }

    private fun mshomeInitViews(view: View) {
        loadingDialog = DialogPB(requireActivity())
        ms_myprofile = view.findViewById(R.id.ms_myprofile)
        ms_home_beauticians = view.findViewById(R.id.ms_home_beauticians)
        ms_home_invoice = view.findViewById(R.id.ms_home_invoice)
        ms_home_services = view.findViewById(R.id.ms_home_services)
        ms_home_customers = view.findViewById(R.id.ms_home_customers)
        ms_home_calendar = view.findViewById(R.id.ms_home_calendar)
    }

    public fun initializeClickListners() {
        ms_myprofile.setOnClickListener {
            startActivity(Intent(requireContext(), MSEditProfileActivit::class.java))
        }
        ms_home_beauticians.setOnClickListener {
            startActivity(Intent(requireContext(), MSBeauticians::class.java))
        }
        ms_home_customers.setOnClickListener {
            startActivity(Intent(requireContext(), MSCustomersActivity::class.java))
        }
/*        btn_drawer_menu.setOnClickListener {
            drawerLayout.openDrawer(GravityCompat.START);
        }*/
        ms_home_invoice.setOnClickListener {
            startActivity(Intent(requireContext(), MSInvoiceActivity::class.java))
        }
        ms_home_calendar.setOnClickListener {
            startActivity(Intent(requireContext(), MSCalendarSectionActivity::class.java))
        }
        ms_home_services.setOnClickListener {
            startActivity(Intent(requireContext(), MSServiceActivity::class.java))
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

    }

    public fun initializeInputs() {
    }

    public fun initializeLabels() {
       /* categories = ArrayList()
        initData()



        customerallItemsRecycler.adapter = CustomerAllItemAdapter(itemList)
        customerallItemsRecycler.layoutManager = LinearLayoutManager(requireContext())*/

        /*customerSkincareRV.adapter = SkincareAdapter(itemList)
        customerSkincareRV.layoutManager = LinearLayoutManager(requireContext())*/
    }


    private fun initData() {
        itemList = java.util.ArrayList()
        for (i in 0..5)
            itemList.add(
                RetailerDataModel(
                    "Rajesh K",
                    "rajeshisamazing@gmail.com",
                    "RR Nagar Banglore",
                    "+9182384898"
                )
            )
    }

    private fun backPress() {
        requireActivity().onBackPressedDispatcher.addCallback(
            viewLifecycleOwner,
            object : OnBackPressedCallback(true) {
                override fun handleOnBackPressed() {

                    showExitConfirmationDialog()
                }
            })
    }

   /* private fun getProductCategory() {
        val apiService = retrofit.create(ProductApiService::class.java)
        val tkn = session.token
        val categoryId = session.getcategoryId()

            try {
                val call = apiService.getProduct("Bearer $tkn", categoryId)
                call.enqueue(object :Callback<CategoriesProductResponse>{
                    override fun onResponse(
                        call: Call<CategoriesProductResponse>,
                        response: Response<CategoriesProductResponse>
                    ) {
                        if (response.isSuccessful) {
                            val productResponse = response.body()
                            val status = productResponse?.status

                            // Process the product and image endpoint as needed
                            if (productResponse != null) {
                                val imageEndpoint = productResponse.image_endpoint ?: ""
                                Log.d("imageEndpoint",imageEndpoint)
                                if (status.equals("true")) {
                                    categoryProduct = productResponse.categories ?: emptyList()
                                    try {
                                        customerallItemsRecycler.layoutManager = LinearLayoutManager(requireContext())
                                        customerallItemsRecycler.adapter = CustomerAllItemAdapter(categoryProduct, imageEndpoint,this@CustomerHomeFrag)
                                        Log.d("successItem", status.toString() + "   " + categoryProduct.toString())
                                    } catch (e: Exception) {
                                        logHandler("catchContext", e.message + "   " + e.stackTraceToString())
                                    }
                                } else {

                                    customerallItemsRecycler.adapter = null // Clear the adapter
                                    Log.d("successItem2", status.toString() + "     "+ productResponse)
                                }
                            }
                        } else {
                            val errorMessage = response.message()
                            logHandler("elseBlock",errorMessage)
                        }
                    }

                    override fun onFailure(call: Call<CategoriesProductResponse>, t: Throwable) {
                    }

                })
            } catch (e: Exception) {
                e.printStackTrace()
            }
    }*/

    private fun getProductCategory() {
        val apiService = retrofit.create(ProductApiService::class.java)
        val tkn = session.token
        val categoryId = session.getcategoryId()

        coroutineScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getProduct("Bearer $tkn", categoryId)
                }

                if (response.isSuccessful) {
                    val productResponse = response.body()
                    val status = productResponse?.status

                    // Process the product and image endpoint as needed
                    if (productResponse != null) {
                        val imageEndpoint = productResponse.image_endpoint ?: ""
                        Log.d("imageEndpoint", imageEndpoint)

                        if (status.equals("true")) {
                            categoryProduct = productResponse.categories ?: emptyList()
                            try {
                                customerallItemsRecycler.layoutManager = LinearLayoutManager(requireContext())
                                customerallItemsRecycler.adapter = CustomerAllItemAdapter(categoryProduct, imageEndpoint,this@CustomerHomeFrag)
                                Log.d("successItem", status.toString() + "   " + categoryProduct.toString())
                            } catch (e: Exception) {
                                logHandler("catchContext", e.message + "   " + e.stackTraceToString())
                            }
                        } else {
                            customerallItemsRecycler.adapter = null // Clear the adapter
                            Log.d("successItem2", status.toString() + "     "+ productResponse)
                        }
                    }
                } else {
                    val errorMessage = response.message()
                    logHandler("elseBlock", errorMessage)
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        job.cancel()
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

    private fun getProfileResponse() {

        val apiService = RetrofitClient.retrofit.create(MSGetProfileApiService::class.java)
        val tokn = session.token
        mainScope.launch {
            try {
                val call: Call<MSProfileResponseDM> = apiService.getProfile("Bearer $tokn")
                call.enqueue(object : Callback<MSProfileResponseDM> {
                    override fun onResponse(
                        call: Call<MSProfileResponseDM>,
                        response: Response<MSProfileResponseDM>
                    ) {
                        loadingDialog.dismissDialog()
                        if (response.isSuccessful) {
                            val data = response.body()
                            var profilestatus = "false"
                            val profile = data?.profile
                            val email = profile?.email
                            val status = data?.status
                            val username = profile?.username
                            val fullName = profile?.fullName
                            val image = profile?.image
                            val gender = profile?.gender
                            val mobile = profile?.mobile
                            val qualification = profile?.qualification
                            val profession = profile?.profession
                            val experience = profile?.experience
                            val appointmentInterval = profile?.appointmentInterval
                            val salutation = profile?.salutation
                            if (data != null) {
                                if (data.status.equals("true")) {
                                    logHandler("ProfileResponse ", data.profile.email.toString())
                                    logHandler("Profile ", profile.toString())
                                    try {
                                        profilestatus = data.profileStatus.toString()
                                        logHandler("PResponse ", data.profileStatus.toString())
                                    } catch (_: Exception) {
                                        profilestatus = "true"
                                        logHandler("PResponse ", "true")
                                    }
                                } else {
                                    Toast.makeText(
                                        requireContext(),
                                        "Your Session has expired please login again",
                                        Toast.LENGTH_SHORT
                                    ).show()
                                    startActivity(
                                        Intent(
                                            requireContext(),
                                            Login::class.java
                                        )
                                    )
                                }

                                if (email == null || fullName == null || image == "" || gender == null || mobile == null || qualification == null || profession == null ||
                                    experience == null || appointmentInterval == null || salutation == null
                                ) {
                                    try {
                                        val builder =
                                            android.app.AlertDialog.Builder(requireContext())
                                        builder.setTitle("Profile Details")
                                            .setMessage("Some fields are missing, please fill all the details")
                                            .setPositiveButton("ok") { dialog, _ ->
                                                dialog.dismiss()
                                                startActivity(
                                                    Intent(
                                                        requireContext(),
                                                        MSEditProfileActivit::class.java
                                                    )
                                                )
                                            }
                                        val dialog = builder.create()
                                        dialog.show()
                                    } catch (_: Exception) {
                                    }
                                }

                            } else {

                            }
                        }
                    }

                    override fun onFailure(call: Call<MSProfileResponseDM>, t: Throwable) {
                        loadingDialog.dismissDialog()
                        logHandler(
                            "FailureResponse",
                            t.message.toString() + " \n" + t.localizedMessage + " \n" + t.cause + " \n" + t.stackTraceToString()
                        )
                        logHandler("CallResponse", call.toString())
                    }
                })

            } catch (e: Exception) {
                logHandler("ExceptionResponse", e.message.toString())
            }
        }
    }

    fun logHandler(name: String?, msg: String?) {
        Log.d(name, msg!!)
    }

    public fun trendingSection() {
        customerTrendingRecyclerView.adapter = CustomerTrendingAdapter(itemList)
        customerTrendingRecyclerView.layoutManager = LinearLayoutManager(
            requireContext(),
            LinearLayoutManager.HORIZONTAL, false
        )
        Timer().schedule(object : TimerTask() {
            override fun run() {
                if ((customerTrendingRecyclerView.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < itemList.size - 1) {
                    customerTrendingRecyclerView.layoutManager!!.smoothScrollToPosition(
                        customerTrendingRecyclerView,
                        RecyclerView.State(),
                        (customerTrendingRecyclerView.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() + 1
                    )
                } else if ((customerTrendingRecyclerView.layoutManager as LinearLayoutManager)!!.findFirstCompletelyVisibleItemPosition() < itemList.size - 1) {
                    customerTrendingRecyclerView.layoutManager!!.smoothScrollToPosition(
                        customerTrendingRecyclerView,
                        RecyclerView.State(),
                        0
                    )
                } else {
                    customerTrendingRecyclerView.smoothScrollToPosition(0);
                }
            }
        }, 0, 1500)
    }

    override fun onItemClicked(categoryID: String) {
        logHandler("itmeClicked",categoryID)

        getProductCategory()
    }


    override fun onCartIconClicked(categoryID: String) {
        loadingDialog.startLoadingDialog()
        val apiService = RetrofitClient.retrofit.create(CustomerAddCartService::class.java)
        val token =session.token
        mainScope.launch {
            try{
                val call:Call<NormalDataModel> = apiService.addtoCart("Bearer $token",categoryID)
                call.enqueue(object:Callback<NormalDataModel>{
                    override fun onResponse(
                        call: Call<NormalDataModel>,
                        response: Response<NormalDataModel>
                    ) {
                        loadingDialog.dismissDialog()
                        if(response.isSuccessful){
                            var data = response.body()
                            try {
                                Toast.makeText(requireContext(),"Item added to cart",Toast.LENGTH_SHORT).show()
                            }
                            catch (_:Exception){

                            }
                            logHandler("OnCartIconClicked",response.body().toString())
                            if(data?.status.equals("true")){

                            }else{

                            }

                        }
                    }

                    override fun onFailure(call: Call<NormalDataModel>, t: Throwable) {
                        loadingDialog.dismissDialog()
                        logHandler("OnCartFailureRes",t.message)
                    }

                })
            }
            catch (_:Exception){

            }
        }
    }

}