package com.aenatural.aenaturals.myspalon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.*
import androidx.recyclerview.widget.LinearLayoutManager
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.BeauticianProfileApiService
import com.aenatural.aenaturals.apiservices.MSAppointmentCreateApiService
import com.aenatural.aenaturals.apiservices.MSCustomerListApiService
import com.aenatural.aenaturals.apiservices.datamodels.BeauticianProfileResponse
import com.aenatural.aenaturals.apiservices.datamodels.Customer
import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import com.aenatural.aenaturals.apiservices.datamodels.Staff
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.RetrofitClient
import com.aenatural.aenaturals.common.RetrofitClient.details
import com.aenatural.aenaturals.common.RetrofitClient.mainScope
import com.aenatural.aenaturals.common.RetrofitClient.retrofit
import com.aenatural.aenaturals.myspalon.Adapter.BeauticianProfileAdapter
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.util.*

class MSAddAppointmentActivity : BaseClass() {
    private val myCalendar: Calendar = Calendar.getInstance()
    private lateinit var appointment_input_dob: EditText
    private lateinit var appointment_input_fullname: EditText
    private lateinit var appointment_time: EditText
    private lateinit var appointment_tv_submit: TextView
    private lateinit var beauticianSpinner: Spinner
    private lateinit var session: Session
    private lateinit var loadingDialogPB: DialogPB
    var dobtxt = ""
    var fullNametxt = ""
    var timetxt = ""
    private val custList = ArrayList<Customer>()
    private val staff1 = ArrayList<Staff>()
    private val beauticianNames: MutableList<String> = mutableListOf()
    lateinit var beauticianAdapter: ArrayAdapter<String>
    var selectedBeautician = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()

        getBeauticianDetails()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msadd_appointment)
        birdTheme()
        session = Session(this)
        loadingDialogPB = DialogPB(this)
    }

    override fun initializeViews() {
        appointment_input_dob = findViewById(R.id.appointment_input_dob)
        appointment_time = findViewById(R.id.appointment_time)
        appointment_input_fullname = findViewById(R.id.appointment_input_fullname)
        appointment_tv_submit = findViewById(R.id.appointment_tv_submit)
        beauticianSpinner = findViewById(R.id.appointment_input_beauticianET)

        beauticianAdapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, beauticianNames)

// Set the dropdown layout style
        beauticianAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)

// Set the adapter on the spinner
        beauticianSpinner.adapter = beauticianAdapter
    }

    override fun initializeClickListners() {
        appointment_input_dob.setOnClickListener {
            showDatePicker(myCalendar, appointment_input_dob)
        }
        appointment_time.setOnClickListener {
            openTimePicker(appointment_time, myCalendar)
        }
        appointment_tv_submit.setOnClickListener {
            dobtxt = appointment_input_dob.text.toString()
            fullNametxt = appointment_input_fullname.text.toString()
            timetxt = appointment_time.text.toString()

            /*if (dobtxt.isNotEmpty() && timetxt.isNotEmpty() && fullNametxt.isNotEmpty()) {

            }else{
                loadingDialogPB.showErrorBottomSheetDialog(details)
            }*/
            createAppointment()
        }


    }


    override fun initializeInputs() {
        // Set a listener to handle spinner item selection
        beauticianSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                // Handle the selected item

                val selectedBeautician = beauticianNames[position]
                // Do something with the selected beautician
            }

            override fun onNothingSelected(parent: AdapterView<*>?) {
                // Handle the case when no item is selected
            }

        }
    }

    override fun initializeLabels() {

    }

    private fun createAppointment() {
        val apiService = retrofit.create(MSAppointmentCreateApiService::class.java)
        val tkn = session.token // Replace YOUR_BEARER_TOKEN with the actual bearer token

        val appUser = fullNametxt
        val appDate = dobtxt
        val appTime = timetxt
        val appDuration = "60"
        val appReason = "Checkup"

        val call = apiService.createAppointment("Bearer $tkn",
            appUser, appDate, appTime, appDuration, appReason
        )
        try {
            call.enqueue(object : Callback<NormalDataModel> {
                override fun onResponse(
                    call: Call<NormalDataModel>,
                    response: Response<NormalDataModel>
                ) {
                    if (response.isSuccessful) {
                        val appointmentResponse = response.body()
                        val status = appointmentResponse?.status
                        val message = appointmentResponse?.message

                        // Handle the successful response
                        loadingDialogPB.dismissDialog()
                        if (status.equals("false")) {
                        loadingDialogPB.showErrorBottomSheetDialog("$message")
                        }else {
                            loadingDialogPB.startSucessDialog(
                                "$message",
                                this@MSAddAppointmentActivity,
                                MSCalendarSectionActivity::class.java
                            )
                        }
                    } else {
                        // Handle the error case
                        loadingDialogPB.dismissDialog()
                        val errorMessage = response.errorBody()?.string()
                        loadingDialogPB.showErrorBottomSheetDialog(errorMessage ?: "Unknown error")
                    }
                }

                override fun onFailure(call: Call<NormalDataModel>, t: Throwable) {
                    // Handle the failure case
                    loadingDialogPB.dismissDialog()
                    val errorMessage = t.message ?: "Unknown error"
                    loadingDialogPB.showErrorBottomSheetDialog(errorMessage)
                }
            })
        } catch (e: Exception) {

        }
    }

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

                    /*customerRV.layoutManager = LinearLayoutManager(this@MSAddAppointmentActivity)
                    customerRV.adapter = customerProfileAdapter*/

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

    /*private fun getBeauticianDetails() {
        val apiService = RetrofitClient.retrofit.create(BeauticianProfileApiService::class.java)

        val tkn = session.token

        try {
            val call = apiService.getProfile("Bearer $tkn")
            call.enqueue(object : Callback<BeauticianProfileResponse> {
                override fun onResponse(call: Call<BeauticianProfileResponse>, response: Response<BeauticianProfileResponse>) {
                    if (response.isSuccessful) {
                        loadingDialogPB.dismissDialog()
                        val profileResponse = response.body()
                        val status = profileResponse?.status
                        val staffList = profileResponse?.staff


                        if (staffList != null) {

                            for (staff in staffList) {
                                val fullName = staff.fullName.toString()
                                beauticianNames.add(fullName)
                            }
                        }
                        logHandler("successBea " , response.message())


                    } else {
                        // Handle the error case
                        logHandler("error1 " , response.message())
                        loadingDialogPB.showErrorBottomSheetDialog(response.message() + response.body())
                    }
                }

                override fun onFailure(call: Call<BeauticianProfileResponse>, t: Throwable) {
                    // Handle the failure case
                    loadingDialogPB.dismissDialog()
                    logHandler("FailureResponse",
                        t.message.toString() + " \n" + t.localizedMessage + " \n" + t.cause + " \n" + t.stackTraceToString()
                    )
                    logHandler("CallResponse", call.toString())
                    loadingDialogPB.showErrorBottomSheetDialog(t.message.toString())

                }
            })
        } catch (e: Exception) {
            logHandler("ExceptionResponse", e.message.toString())
            loadingDialogPB.showErrorBottomSheetDialog(e.message.toString())
        }
    }*/

    private fun getBeauticianDetails() {
        val apiService = RetrofitClient.retrofit.create(BeauticianProfileApiService::class.java)
        val tkn = session.token

        try {
            val call = apiService.getProfile("Bearer $tkn")
            call.enqueue(object : Callback<BeauticianProfileResponse> {
                override fun onResponse(
                    call: Call<BeauticianProfileResponse>,
                    response: Response<BeauticianProfileResponse>
                ) {
                    if (response.isSuccessful) {
                        loadingDialogPB.dismissDialog()
                        val profileResponse = response.body()
                        val status = profileResponse?.status
                        val staffList = profileResponse?.staff

                        beauticianNames.clear() // Clear the existing list before adding new items

                        if (staffList != null) {
                            for (staff in staffList) {
                                val fullName = staff.salutation + " " + staff.fullName.toString()
                                beauticianNames.add(fullName)
                            }
                        }

                        // Notify the adapter that the data has changed
                        beauticianAdapter.notifyDataSetChanged()

                        logHandler("successBea ", response.message())
                    } else {
                        // Handle the error case
                        logHandler("error1 ", response.message())
                        loadingDialogPB.showErrorBottomSheetDialog(response.message() + response.body())
                    }
                }

                override fun onFailure(call: Call<BeauticianProfileResponse>, t: Throwable) {
                    // Handle the failure case
                    loadingDialogPB.dismissDialog()
                    logHandler(
                        "FailureResponse",
                        t.message.toString() + " \n" + t.localizedMessage + " \n" + t.cause + " \n" + t.stackTraceToString()
                    )
                    logHandler("CallResponse", call.toString())
                    loadingDialogPB.showErrorBottomSheetDialog(t.message.toString())
                }
            })
        } catch (e: Exception) {
            logHandler("ExceptionResponse", e.message.toString())
            loadingDialogPB.showErrorBottomSheetDialog(e.message.toString())
        }
    }

}