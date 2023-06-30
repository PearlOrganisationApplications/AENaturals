package com.aenatural.aenaturals.myspalon

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.MSAppointmentCreateApiService
import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.DialogPB
import com.aenatural.aenaturals.common.RetrofitClient.details
import com.aenatural.aenaturals.common.RetrofitClient.retrofit
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
    private lateinit var session: Session
    private lateinit var loadingDialogPB: DialogPB
    var dobtxt = ""
    var fullNametxt = ""
    var timetxt = ""
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
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

}