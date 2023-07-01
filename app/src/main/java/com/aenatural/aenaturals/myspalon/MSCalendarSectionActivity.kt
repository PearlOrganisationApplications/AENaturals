package com.aenatural.aenaturals.myspalon

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.DatePicker
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.MSAppointmentListApiService
import com.aenatural.aenaturals.apiservices.datamodels.Appointment
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.RetrofitClient.mainScope
import com.aenatural.aenaturals.common.RetrofitClient.retrofit
import com.aenatural.aenaturals.myspalon.Adapter.AppointmentAdapter
import kotlinx.coroutines.*

class MSCalendarSectionActivity : BaseClass() {
    lateinit var ms_add_appointment:Button
    private lateinit var session: Session
    private lateinit var loadingDialogPB: DialogPB
    private lateinit var appointmentAdapter: AppointmentAdapter
    private lateinit var appointmentRV: RecyclerView
    private lateinit var datePicker: DatePicker
    private val appintmentList = ArrayList<Appointment>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
//        fetchAppointmentList("2023-06-30")
        setupDatePicker()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_mscalendar_section)
        birdTheme()
        session = Session(this)
        loadingDialogPB = DialogPB(this)
        appointmentAdapter = AppointmentAdapter(appintmentList)
    }

    override fun initializeViews() {
        ms_add_appointment =findViewById(R.id.ms_add_appointment)
        appointmentRV =findViewById(R.id.appointmentRV)
        datePicker =findViewById(R.id.datePicker)
    }

    override fun initializeClickListners() {
        ms_add_appointment.setOnClickListener {
            startActivity(Intent(this,MSAddAppointmentActivity::class.java))
        }
    }

    override fun initializeInputs() {
    }

    override fun initializeLabels() {
    }
    private fun fetchAppointmentList(selectedDate: String) {
        val apiService = retrofit.create(MSAppointmentListApiService::class.java)
        val bearerToken = session.token // Replace YOUR_BEARER_TOKEN with the actual bearer token

        mainScope.launch {
            try {
                val response = withContext(Dispatchers.IO) {
                    apiService.getAppointmentList("Bearer $bearerToken", selectedDate)
                }

                if (response.isSuccessful) {
                    val appointmentResponse = response.body()
                    val appointments = appointmentResponse?.appointments
                    loadingDialogPB.dismissDialog()

                    // Filter appointments based on the selected date
                    val filteredAppointments = appointments?.filter { it.app_date == selectedDate }

                    if (filteredAppointments.isNullOrEmpty()) {
                        // No appointments for the selected date
                        // Handle the empty list case (e.g., show a message)



                        printLogs("success123", "onSuccess", appointmentResponse.toString())
                        loadingDialogPB.showErrorBottomSheetDialog("No appointment ")
                    } else {
                        // Update the appointment list and notify the adapter
                        appointmentAdapter.setData(filteredAppointments)

                        Log.d("elseApponm",filteredAppointments.toString())
                    }

                    printLogs("success123", "onSuccess", appointmentResponse.toString())
                } else {
                    // Handle the error case
                    val errorMessage = response.errorBody()?.string()
                    loadingDialogPB.dismissDialog()
                    printLogs("API Error", "failure", errorMessage)
                    loadingDialogPB.showErrorBottomSheetDialog("$errorMessage")
                }
            } catch (e: Exception) {
                // Handle any exceptions that occur during the API call
                // Log the error or show an error message to the user
                loadingDialogPB.dismissDialog()
                printLogs("API Error", "failure", e.message ?: "Unknown error")
                loadingDialogPB.showErrorBottomSheetDialog(e.message.toString())
            }
        }
        setupRecyclerView()
    }

    private fun setupRecyclerView() {
        appointmentRV.layoutManager = LinearLayoutManager(this@MSCalendarSectionActivity)
        appointmentRV.adapter = appointmentAdapter
    }

    private fun setupDatePicker() {
        var selectedDate = ""
        datePicker.init(
            datePicker.year,
            datePicker.month,
            datePicker.dayOfMonth
        ) { _, year, monthOfYear, dayOfMonth ->
            if(monthOfYear<9) {
//                 monthOfYear2 = ("0$monthOfYear")
                selectedDate = "$year-0${monthOfYear + 1}-${dayOfMonth}"
            }else {
               selectedDate  = "$year-${monthOfYear + 1}-${dayOfMonth}"
            }
            loadingDialogPB.startLoadingDialog()
            logHandler("Date ",selectedDate.toString())
            appintmentList.clear()
            fetchAppointmentList(selectedDate)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        mainScope.cancel()
    }

}