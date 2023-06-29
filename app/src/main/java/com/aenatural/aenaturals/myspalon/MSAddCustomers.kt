package com.aenatural.aenaturals.myspalon

import android.app.DatePickerDialog
import android.app.DatePickerDialog.OnDateSetListener
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.CheckBox
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.RadioButton
import android.widget.TextView
import com.aenatural.aenaturals.R
import com.aenatural.aenaturals.apiservices.MSCreateCustomerApiService
import com.aenatural.aenaturals.apiservices.datamodels.NormalDataModel
import com.aenatural.aenaturals.baseframework.BaseClass
import com.aenatural.aenaturals.baseframework.Session
import com.aenatural.aenaturals.common.RetrofitClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.text.SimpleDateFormat
import java.util.*

class MSAddCustomers : BaseClass() {
    private lateinit var cust_input_medicineET: EditText
    private lateinit var cust_input_mobile: EditText
    private lateinit var cust_input_email: EditText
    private lateinit var cust_input_dob: EditText
    private lateinit var cust_input_app_date: EditText
    private lateinit var cust_input_profession: EditText
    private lateinit var cust_input_experience: EditText
    private lateinit var cust_input_appointment: EditText
    private lateinit var cust_maleRB: RadioButton
    private lateinit var cust_femaleRB: RadioButton
    private lateinit var cust_appointmentCB: CheckBox
    private lateinit var cust_appointLL: LinearLayout
    private lateinit var cust_tv_submit: TextView
    private lateinit var session: Session

    private val myCalendar: Calendar = Calendar.getInstance()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setLayoutXml()
        initializeViews()
        initializeClickListners()
    }

    override fun setLayoutXml() {
        setContentView(R.layout.activity_msadd_customers)
        birdTheme()
        session = Session(this)
    }

    override fun initializeViews() {
        cust_input_medicineET = findViewById(R.id.cust_input_medicineET)
        cust_input_mobile = findViewById(R.id.cust_input_mobile)
        cust_input_email = findViewById(R.id.cust_input_email)
        cust_maleRB = findViewById(R.id.cust_maleRB)
        cust_femaleRB = findViewById(R.id.cust_femaleRB)
        cust_input_dob = findViewById(R.id.cust_input_dob)
        cust_appointmentCB = findViewById(R.id.cust_appointmentCB)
        cust_input_app_date = findViewById(R.id.cust_input_app_date)
        cust_input_profession = findViewById(R.id.cust_input_profession)
        cust_input_experience = findViewById(R.id.cust_input_experience)
        cust_input_appointment = findViewById(R.id.cust_input_appointment)
        cust_appointLL = findViewById(R.id.cust_appointLL)
        cust_tv_submit = findViewById(R.id.cust_tv_submit)
    }

    override fun initializeClickListners() {
        cust_appointLL.visibility = View.GONE

        cust_input_dob.setOnClickListener {
            showDatePicker()
        }
        cust_appointmentCB.setOnCheckedChangeListener { buttonView, isChecked ->
            if (isChecked) {
                // Show the desired views
                cust_appointLL.visibility = View.VISIBLE
                // ...
            } else {
                // Hide the desired views
                cust_appointLL.visibility = View.GONE
                // ...
            }
        }

        cust_tv_submit.setOnClickListener {
            updateCustomer()
        }

    }

    override fun initializeInputs() {
    }

    override fun initializeLabels() {
    }

    private fun showDatePicker() {
        val date = DatePickerDialog.OnDateSetListener { view, year, month, day ->
            myCalendar.set(Calendar.YEAR, year)
            myCalendar.set(Calendar.MONTH, month)
            myCalendar.set(Calendar.DAY_OF_MONTH, day)
            updateLabel()
        }

        val datePickerDialog = DatePickerDialog(
            this@MSAddCustomers,
            R.style.MyDatePickerDialogTheme, // use your custom theme here
            date,
            myCalendar.get(Calendar.YEAR),
            myCalendar.get(Calendar.MONTH),
            myCalendar.get(Calendar.DAY_OF_MONTH)
        )

        // Set the maximum and minimum date for the DatePickerDialog
        datePickerDialog.datePicker.maxDate = System.currentTimeMillis()
        datePickerDialog.datePicker.minDate = minimumDate

        datePickerDialog.show()
    }

    private fun updateLabel() {
        val myFormat = "yyyy/dd/MM"
        val formattedDate = myFormat.replace("/", "-")
        val dateFormat = SimpleDateFormat(formattedDate, Locale.US)
        cust_input_dob.setText(dateFormat.format(myCalendar.time))
    }

    private fun updateCustomer() {
        val apiService = RetrofitClient.retrofit.create(MSCreateCustomerApiService::class.java)

        val token = session.token
        val fullName = "John Doe"
        val mobile = "1234567890"
        val gender = "Male"
        val email = "johndoe@example.com"
        val dob = "1990-01-01"
        val appointmentDate = "2023-06-28"
        val appointmentTime = "10:00 AM"
        val appointmentDuration = "60"
        val appointmentReason = "Regular check-up"

        try {
            val call = apiService.createCustomer(
                "Bearer $token",
                fullName,
                mobile,
                gender,
                email,
                dob,
                appointmentDate,
                appointmentTime,
                appointmentDuration,
                appointmentReason
            )

            call.enqueue(object : Callback<NormalDataModel> {
                override fun onResponse(call: Call<NormalDataModel>, response: Response<NormalDataModel>) {
                    if (response.isSuccessful) {
                        val customerCreateResponse = response.body()
                        val status = customerCreateResponse?.status
                        val message = customerCreateResponse?.message

                        // Handle the response status and message as needed
                        printLogs("success","onResponse",message)
                    } else {
                        // Handle the error case
                        val errorBody = response.errorBody()?.string()
                        if (errorBody != null) {
                            Log.d("Error", errorBody)
                        }
                    }
                }

                override fun onFailure(call: Call<NormalDataModel>, t: Throwable) {
                    // Handle the failure case
                    Log.e("API Error", t.message ?: "Unknown error")
                }
            })

        } catch (_: Exception) {

        }
    }

}