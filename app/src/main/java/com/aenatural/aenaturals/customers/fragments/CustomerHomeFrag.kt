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
import com.aenatural.aenaturals.apiservices.MSGetProfileApiService
import com.aenatural.aenaturals.apiservices.datamodels.MSProfileResponseDM
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.Login
import com.aenatural.aenaturals.common.Models.RetailerDataModel
import com.aenatural.aenaturals.common.RetrofitClient
import com.aenatural.aenaturals.customers.CustomerTrendingAdapter
import com.aenatural.aenaturals.customers.adapters.CustomerAllItemAdapter
import com.aenatural.aenaturals.customers.adapters.SkincareAdapter
import com.aenatural.aenaturals.myspalon.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
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
    lateinit var session: Session

    lateinit var ms_myprofile: LinearLayout
    lateinit var ms_home_beauticians: LinearLayout
    lateinit var ms_home_customers: LinearLayout
    lateinit var ms_home_invoice: LinearLayout
    lateinit var ms_home_services: LinearLayout
    lateinit var ms_home_calendar: LinearLayout
    lateinit var loadingDialog: DialogPB

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
        session = Session(requireContext())
        initializeViews(view)
        initializeClickListners()
        initializeLabels()
        initializeInputs()
        getProfileResponse()
    }

    public fun initializeViews(view: View) {
        mshomeInitViews(view)
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

    }

    public fun initializeInputs() {

    }

    public fun initializeLabels() {
        initData()



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

    private fun getProfileResponse() {

        val apiService = RetrofitClient.retrofit.create(MSGetProfileApiService::class.java)
        val tokn = session.token
        val coroutineScope = CoroutineScope(Dispatchers.Main)
        coroutineScope.launch {
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
                                    try{
                                    val builder = android.app.AlertDialog.Builder(requireContext())
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
                                }catch (_:Exception){
                                }
                                }

                            } else {

                            }
                        }
                    }

                    override fun onFailure(call: Call<MSProfileResponseDM>, t: Throwable) {
                        loadingDialog.dismissDialog()
                        logHandler("FailureResponse",
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

    public fun trendingSection(){
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
    }
}